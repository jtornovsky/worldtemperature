package com.anodot.worldtemperature;

import com.anodot.worldtemperature.examples.SimpleWeatherAPI;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.simpleWeatherApiRunner();
    }

    private void simpleWeatherApiRunner() {

        WeatherAPI weatherAPI = new SimpleWeatherAPI();

        // Example: Retrieve city data by IDs
        Set<String> cityIds = new HashSet<>(Arrays.asList("TLV", "NY"));
        Set<City> cities = weatherAPI.getAllCitiesByIds(cityIds);
        log.info("Cities:");
        for (City city : cities) {
            log.info("{}: {} (Population: {})", city.getId(), city.getName(), city.getPopulation());
        }

        // Example: Retrieve temperature data for a city
        String cityId = "TLV";
        List<DailyTemp> temperatures = weatherAPI.getLastYearTemperature(cityId);
        log.info("\nTemperature data for {}:", cityId);
        for (DailyTemp temp : temperatures) {
            log.info("Date: {}, Temperature: {}", temp.getDate(), temp.getTemperature());
        }
    }
}