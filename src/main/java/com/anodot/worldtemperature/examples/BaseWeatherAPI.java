package com.anodot.worldtemperature.examples;

import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

public abstract class BaseWeatherAPI {

    protected Map<String, City> cityData = new HashMap<>();
    protected Set<String> cityIds = new HashSet<>();
    protected Map<String, List<DailyTemp>> temperatureData = new HashMap<>();

    /**
     * Generates mock data for cities and their corresponding temperature records.
     * This method populates the {@code cityData} and {@code temperatureData} maps with mock data.
     */
    protected abstract void generateData();

    protected Set<City> getAllCitiesByIds(Set<String> cityIds) {
        Set<City> cities = new HashSet<>();
        for (String cityId : cityIds) {
            if (cityData.containsKey(cityId)) {
                cities.add(cityData.get(cityId));
            }
        }
        return cities;
    }
}
