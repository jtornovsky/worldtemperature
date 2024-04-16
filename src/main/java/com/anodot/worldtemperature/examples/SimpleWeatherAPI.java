package com.anodot.worldtemperature.examples;

import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

public class SimpleWeatherAPI implements WeatherAPI {

    private Map<String, City> cityData;
    private Map<String, List<DailyTemp>> temperatureData;

    public SimpleWeatherAPI() {
        // Initialize demo data
        cityData = new HashMap<>();
        cityData.put("TLV", new City("TLV", "Tel Aviv", 1000000));
        cityData.put("NY", new City("NY", "New York", 8000000));

        temperatureData = new HashMap<>();
        temperatureData.put("TLV", Arrays.asList(
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0),
                new DailyTemp(new Date(), 26.5)
        ));
        temperatureData.put("NY", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5)
        ));
    }

    @Override
    public Set<City> getAllCitiesByIds(Set<String> cityIds) {
        Set<City> cities = new HashSet<>();
        for (String cityId : cityIds) {
            if (cityData.containsKey(cityId)) {
                cities.add(cityData.get(cityId));
            }
        }
        return cities;
    }

    @Override
    public List<DailyTemp> getLastYearTemperature(String cityId) {
        return temperatureData.getOrDefault(cityId, Collections.emptyList());
    }
}

