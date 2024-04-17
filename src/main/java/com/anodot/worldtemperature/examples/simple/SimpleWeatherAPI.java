package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.examples.BaseWeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

/**
 * A simple implementation of the {@link WeatherAPI} interface, providing mock weather data for demonstration purposes.
 * This implementation generates data for a set of predefined cities and their corresponding temperature records.
 */
class SimpleWeatherAPI extends BaseWeatherAPI implements WeatherAPI {

    SimpleWeatherAPI() {
        generateData();
    }

    @Override
    public Set<City> getAllCitiesByIds(Set<String> cityIds) {
        return super.getAllCitiesByIds(cityIds);
    }

    @Override
    public List<DailyTemp> getLastYearTemperature(String cityId) {
        return temperatureData.getOrDefault(cityId, Collections.emptyList());
    }

    public Set<String> getCityIds() {
        return cityIds;
    }

    @Override
    protected void generateData() {
        // Add 20 cities with names and populations
        cityData.put("TLV", new City("TLV", "Tel Aviv", 1000000));
        cityData.put("NY", new City("NY", "New York", 8000000));
        cityData.put("LON", new City("LON", "London", 9000000));
        cityData.put("PAR", new City("PAR", "Paris", 2100000));
        cityData.put("BER", new City("BER", "Berlin", 3700000));
        cityData.put("ROM", new City("ROM", "Rome", 2800000));
        cityData.put("MAD", new City("MAD", "Madrid", 3200000));
        cityData.put("ATH", new City("ATH", "Athens", 3400000));
        cityData.put("TOK", new City("TOK", "Tokyo", 37000000));
        cityData.put("SHA", new City("SHA", "Shanghai", 27000000));
        cityData.put("DEL", new City("DEL", "Delhi", 31000000));
        cityData.put("SIN", new City("SIN", "Singapore", 5700000));
        cityData.put("SYD", new City("SYD", "Sydney", 5300000));
        cityData.put("MEL", new City("MEL", "Melbourne", 5100000));
        cityData.put("LA", new City("LA", "Los Angeles", 3900000));
        cityData.put("RIO", new City("RIO", "Rio de Janeiro", 6700000));
        cityData.put("MEX", new  City("MEX", "Mexico City", 21000000));
        cityData.put("CAI", new City("CAI", "Cairo", 20000000));
        cityData.put("SAO", new City("SAO", "Sao Paulo", 12000000));
        cityData.put("LIM", new City("LIM", "Lima", 10000000));

        // Loop through each city and add 50 DailyTemp entries
        for (String cityCode : cityData.keySet()) {
            List<DailyTemp> dailyTemps = new ArrayList<>();
            // Generate random temperatures for 50 days
            for (int i = 0; i < 50; i++) {
                double randomTemp = Math.random() * 40 + 1; // Generate random temp between 1 and 41
                dailyTemps.add(new DailyTemp(new Date(), randomTemp));
            }
            temperatureData.put(cityCode, dailyTemps);
        }

        cityIds.addAll(cityData.keySet());
    }
}

