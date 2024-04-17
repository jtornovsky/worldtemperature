package com.anodot.worldtemperature.analysis;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import com.anodot.worldtemperature.util.LRUCache;
import com.anodot.worldtemperature.util.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.Set;
import java.util.List;

import java.util.*;
import java.util.concurrent.*;

/**
 * Analyzes temperature data for cities.
 */
@Slf4j
public class CityTemperatureAnalyzer {

    private static final int THREAD_POOL_SIZE = 5;
    private static final int CACHE_SIZE = 10000;
    private static final int CACHE_TTL_IN_SECONDS = 3600;   // keep entry 1 hour

    private final WeatherAPI weatherAPI;
    private final ExecutorService executor;
    private final LRUCache<String, List<DailyTemp>> temperatureCache;

    private int populationThreshold;
    private int numberOfTopCities;

    private final DecimalFormat df = new DecimalFormat("#.###");

    public CityTemperatureAnalyzer(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
        this.executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // Use a fixed-size thread pool
        this.temperatureCache = new LRUCache<>(CACHE_SIZE, CACHE_TTL_IN_SECONDS);
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

    public void close() {
        shutdown();
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
            } else {
                log.info("the '{}' city's temperatures won't be calculated due to its population {} is under the threshold of {}.", city.getName(), city.getPopulation(), populationThreshold);
            }
        }

        // Return the list of filtered cities
        return filteredCities;
    }

    /**
     * Fetches temperature data for the given cities asynchronously. It first attempts to retrieve
     * cached temperature data from the LRUCache for each city. If the data is not found in the cache,
     * asynchronous tasks are submitted to fetch the temperature data from the WeatherAPI for each city.
     *
     * @param cities The list of cities for which temperature data will be fetched.
     * @return A map containing the fetched temperature data for each city.
     */
    private Map<City, List<DailyTemp>> fetchTemperatures(List<City> cities) {

        // map to store the temperature data for each city
        Map<City, List<DailyTemp>> temperatureData = new HashMap<>();

        // list to store the Future objects representing asynchronous temperature fetching tasks
        List<Future<Map.Entry<City, List<DailyTemp>>>> futures = new ArrayList<>();

        for (City city : cities) {

            String cityId = city.getId();
            List<DailyTemp> cachedTemperatures = temperatureCache.get(cityId);

            if (cachedTemperatures != null) {
                log.debug("The temperature data for city {} found in cache, so not fetching it.", city.getName());
                temperatureData.put(city, cachedTemperatures);
            } else {
                // Submit asynchronous task to fetch temperatures from the API
                try {
                    Future<Map.Entry<City, List<DailyTemp>>> future = executor.submit(() -> {
                        // Inside the asynchronous task, fetch the temperature data for the city from the WeatherAPI
                        List<DailyTemp> temperatures = weatherAPI.getLastYearTemperature(city.getId());
                        // Cache the fetched temperature data
                        temperatureCache.put(cityId, temperatures);
                        log.debug("The temperature data for city {} isn't in cache, fetching it.", city.getName());
                        // Create a map entry containing the city and its fetched temperature data
                        return Map.entry(city, temperatures);
                    });
                    // Add the Future object representing the asynchronous task to the list
                    futures.add(future);
                } catch (Exception e) {
                    shutdown();
                    log.error("Failed to fetch temperature data for city {}: {}", city.getName(), e.getMessage());
                    throw new RuntimeException(e);
                }
            }
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
                shutdown();
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
            log.info("Calculated for {} -> temperature {}", city.getName(), df.format(aggregatedTemperature));
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
        log.debug("loaded populationThreshold {}", populationThreshold);
        numberOfTopCities = Integer.parseInt(properties.getProperty("number.of.top.cities", "3"));
        log.debug("loaded numberOfTopCities {}", numberOfTopCities);
    }

    /**
     * shutdowns executor gracefully
     */
    private void shutdown() {
        // Attempt to gracefully shut down the executor service
        executor.shutdown(); // Initiates an orderly shutdown, preventing new tasks from being submitted
        try {
            // Wait a finite time for ongoing tasks to terminate
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                // Force shutdown if tasks are still running after the waiting time
                executor.shutdownNow();
                log.warn("executor service did not terminate gracefully");
            }
        } catch (InterruptedException e) {
            log.error("failed to wait for executor service termination", e);
            executor.shutdownNow(); // Force shutdown again
        }
    }
}
