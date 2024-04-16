package com.anodot.worldtemperature.analysis;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import com.anodot.worldtemperature.util.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.List;

import java.util.*;
import java.util.concurrent.*;

/**
 * Analyzes temperature data for cities.
 */
@Slf4j
public class CityTemperatureAnalyzer {

    private final WeatherAPI weatherAPI;
    private final ExecutorService executor;

    private int populationThreshold;
    private int numberOfTopCities;

    public CityTemperatureAnalyzer(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
        this.executor = Executors.newFixedThreadPool(5); // Use a fixed-size thread pool
        loadProperties();
    }

    /**
     * Retrieves the top cities by aggregated temperature.
     *
     * @param cityIds    The set of city IDs for which temperature data will be analyzed.
     * @param aggregator The temperature aggregator.
     * @return The list of top cities.
     */
    public List<City> getTopCitiesByAggregatedTemperature(Set<String> cityIds, TemperatureAggregator aggregator) {
        List<City> filteredCities = filterCitiesByPopulation(cityIds);
        Map<City, List<DailyTemp>> temperatureData = fetchTemperatures(filteredCities);
        Map<City, Double> aggregatedTemperatures = aggregateTemperatures(temperatureData, aggregator);
        return getTopCities(aggregatedTemperatures);
    }

    /**
     * Filters cities based on their population using the specified population threshold.
     *
     * @param cityIds The set of city IDs to filter.
     * @return A list containing the filtered cities.
     */
    private List<City> filterCitiesByPopulation(Set<String> cityIds) {

        // Retrieve the set of cities corresponding to the given city IDs from the WeatherAPI
        Set<City> cities = weatherAPI.getAllCitiesByIds(cityIds);

        // Initialize a list to store the filtered cities
        List<City> filteredCities = new ArrayList<>();

        // Iterate through each city
        for (City city : cities) {
            // Check if the city's population meets or exceeds the specified threshold
            if (city.getPopulation() >= populationThreshold) {
                // If the population meets the threshold, add the city to the filtered list
                filteredCities.add(city);
            }
        }

        // Return the list of filtered cities
        return filteredCities;
    }

    /**
     * Fetches temperature data for the given cities asynchronously.
     *
     * @param cities The list of cities for which temperature data will be fetched.
     * @return A map containing the fetched temperature data for each city.
     */
    private Map<City, List<DailyTemp>> fetchTemperatures(List<City> cities) {
        // map to store the temperature data for each city
        Map<City, List<DailyTemp>> temperatureData = new HashMap<>();

        // list to store the Future objects representing asynchronous temperature fetching tasks
        List<Future<Map.Entry<City, List<DailyTemp>>>> futures = new ArrayList<>();

        // Submit asynchronous tasks for fetching temperature data for each city
        for (City city : cities) {
            Future<Map.Entry<City, List<DailyTemp>>> future = executor.submit(() -> {
                // Inside the asynchronous task, fetch the temperature data for the city from the WeatherAPI
                List<DailyTemp> temperatures = weatherAPI.getLastYearTemperature(city.getId());
                // Create a map entry containing the city and its fetched temperature data
                return Map.entry(city, temperatures);
            });
            // Add the Future object representing the asynchronous task to the list
            futures.add(future);
        }

        // Wait for all asynchronous tasks to complete and collect their results
        for (Future<Map.Entry<City, List<DailyTemp>>> future : futures) {
            try {
                // Get the result of the asynchronous task
                Map.Entry<City, List<DailyTemp>> result = future.get();
                // Add the fetched temperature data to the temperatureData map
                temperatureData.put(result.getKey(), result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                // If an exception occurs while executing the asynchronous task, log an error and throw a RuntimeException
                log.error("Failed to execute fetch task {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        // Return the map containing the fetched temperature data for each city
        return temperatureData;
    }

    /**
     * Aggregates temperature data for each city using the specified temperature aggregator.
     *
     * @param temperatureData The map of cities to their temperature data.
     * @param aggregator      The temperature aggregator used for aggregation.
     * @return A map containing the aggregated temperatures for each city.
     */
    private Map<City, Double> aggregateTemperatures(Map<City, List<DailyTemp>> temperatureData, TemperatureAggregator aggregator) {

        // a map to store the aggregated temperatures for each city
        Map<City, Double> aggregatedTemperatures = new HashMap<>();

        // Iterate through each city's temperature data
        for (Map.Entry<City, List<DailyTemp>> entry : temperatureData.entrySet()) {
            // Get the city and its corresponding temperature data
            City city = entry.getKey();
            List<DailyTemp> temperatures = entry.getValue();

            // Aggregate the temperatures for the city using the specified aggregator
            double aggregatedTemperature = aggregator.aggregate(temperatures);

            // Store the aggregated temperature for the city in the map
            aggregatedTemperatures.put(city, aggregatedTemperature);
        }

        // Return the map containing the aggregated temperatures for each city
        return aggregatedTemperatures;
    }

    /**
     * Retrieves the top cities by aggregated temperature.
     *
     * @param aggregatedTemperatures The map of cities to their aggregated temperatures.
     * @return The list of top cities.
     */
    private List<City> getTopCities(Map<City, Double> aggregatedTemperatures) {

        // Convert the map entries to a list for sorting
        List<Map.Entry<City, Double>> sortedCities = new ArrayList<>(aggregatedTemperatures.entrySet());

        // Sort the cities based on their aggregated temperatures in descending order
        sortedCities.sort((c1, c2) -> Double.compare(c2.getValue(), c1.getValue()));

        // list to store the top cities
        List<City> topCities = new ArrayList<>();

        // Determine the number of cities to include in the top list
        int numCitiesToAdd = Math.min(numberOfTopCities, sortedCities.size());

        // Add the top cities to the list
        for (int i = 0; i < numCitiesToAdd; i++) {
            topCities.add(sortedCities.get(i).getKey());
        }

        // Return the list of top cities
        return topCities;
    }

    /**
     * Loads properties from the configuration file.
     */
    private void loadProperties() {
        Properties properties = PropertiesLoader.loadProperties();
        populationThreshold = Integer.parseInt(properties.getProperty("population.threshold", "50000"));
        log.info("loaded populationThreshold {}", populationThreshold);
        numberOfTopCities = Integer.parseInt(properties.getProperty("number.of.top.cities", "3"));
        log.info("loaded numberOfTopCities {}", numberOfTopCities);
    }
}
