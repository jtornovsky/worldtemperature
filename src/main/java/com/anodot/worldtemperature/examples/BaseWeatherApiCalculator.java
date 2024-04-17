package com.anodot.worldtemperature.examples;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorFactory;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.analysis.CityTemperatureAnalyzer;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import com.anodot.worldtemperature.util.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;
import java.util.Set;

@Slf4j
public abstract class BaseWeatherApiCalculator {

    protected final WeatherAPI weatherAPI;
    private final CityTemperatureAnalyzer analyzer;

    protected int numberOfTopCities;

    protected BaseWeatherApiCalculator(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
        // Create CityTemperatureAnalyzer instance
        analyzer = new CityTemperatureAnalyzer(this.weatherAPI);
        loadProperties();
    }

    protected abstract Set<String> getCityIds();

    protected void weatherApiCalculator(TemperatureAggregatorType aggregationType) {

        // Example: Retrieve city data by IDs
        Set<String> cityIds = this.getCityIds();
//        printGeneratedData(weatherAPI, cityIds);  // uncomment to see seeded data

        // Get appropriate temperature aggregator based on aggregation type
        TemperatureAggregator aggregator = TemperatureAggregatorFactory.createTemperatureAggregator(aggregationType);

        // Get top cities by aggregated temperature
        List<City> topCities = analyzer.getTopCitiesByAggregatedTemperature(cityIds, aggregator);

        // Output top cities with aggregated temperature
        log.info("Top {} cities with aggregated temperature ({}):", numberOfTopCities, aggregationType);
        for (int i = 0; i < Math.min(numberOfTopCities, topCities.size()); i++) {
            City city = topCities.get(i);
            log.info(city.getName());
        }
    }

    protected void closeApplication() {
        log.info("Application ended.");
        analyzer.close();
    }

    private void printGeneratedData(WeatherAPI weatherAPI, Set<String> cityIds) {
        Set<City> cities = weatherAPI.getAllCitiesByIds(cityIds);
        log.debug("\nCities:");
        for (City city : cities) {
            log.debug("{}: {} (Population: {})", city.getId(), city.getName(), city.getPopulation());
        }

        // print temperature data for each city
        for (String cityId : cityIds) {
            List<DailyTemp> temperatures = weatherAPI.getLastYearTemperature(cityId);
            log.debug("\nTemperature data for {}:", cityId);
            for (DailyTemp temp : temperatures) {
                log.debug("Date: {}, Temperature: {}", temp.getDate(), temp.getTemperature());
            }
        }
    }

    /**
     * Loads properties from the configuration file.
     */
    private void loadProperties() {
        Properties properties = PropertiesLoader.loadProperties();
        numberOfTopCities = Integer.parseInt(properties.getProperty("number.of.top.cities", "3"));
    }
}
