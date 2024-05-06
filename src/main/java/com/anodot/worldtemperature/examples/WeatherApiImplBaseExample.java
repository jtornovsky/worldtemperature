package com.anodot.worldtemperature.examples;

import com.anodot.worldtemperature.api.WeatherApiImpl;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Slf4j
public abstract class WeatherApiImplBaseExample {

    private final WeatherApiImpl weatherApiImpl = new WeatherApiImpl();
    protected Set<String> cityIds = new HashSet<>();

    /**
     * Generates mock data for cities and their corresponding temperature records.
     * This method populates the {@code cityData} and {@code temperatureData} maps with mock data.
     */
    protected abstract void generateData();

    protected Map<String, City> getCityData() {
        return weatherApiImpl.getCityData();
    }

    protected Map<String, List<DailyTemp>> getDailyTempData() {
        return weatherApiImpl.getTemperatureData();
    }

    protected void printGeneratedData(Set<String> cityIds) {
        Set<City> cities = weatherApiImpl.getAllCitiesByIds(cityIds);
        log.debug("\nCities:");
        for (City city : cities) {
            log.debug("{}: {} (Population: {})", city.getId(), city.getName(), city.getPopulation());
        }

        // print temperature data for each city
        for (String cityId : cityIds) {
            List<DailyTemp> temperatures = weatherApiImpl.getLastYearTemperature(cityId);
            log.debug("\nTemperature data for {}:", cityId);
            for (DailyTemp temp : temperatures) {
                log.debug("Date: {}, Temperature: {}", temp.getDate(), temp.getTemperature());
            }
        }
    }
}
