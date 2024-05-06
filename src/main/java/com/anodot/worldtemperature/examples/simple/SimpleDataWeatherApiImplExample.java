package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.examples.WeatherApiImplBaseExample;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * A simple implementation, providing mock weather data for demonstration purposes.
 * This implementation generates data for a set of predefined cities and their corresponding temperature records.
 */
class SimpleDataWeatherApiImplExample extends WeatherApiImplBaseExample {

    SimpleDataWeatherApiImplExample() {
        generateData();
    }

    public Set<String> getCityIds() {
        return cityIds;
    }

    @Override
    protected void generateData() {

        final int numOfTemperatures = 50;

        // Add 20 cities with names and populations
        getCityData().put("TLV", new City("TLV", "Tel Aviv", 1000000));
        getCityData().put("NY", new City("NY", "New York", 8000000));
        getCityData().put("LON", new City("LON", "London", 9000000));
        getCityData().put("PAR", new City("PAR", "Paris", 2100000));
        getCityData().put("BER", new City("BER", "Berlin", 3700000));
        getCityData().put("ROM", new City("ROM", "Rome", 2800000));
        getCityData().put("MAD", new City("MAD", "Madrid", 3200000));
        getCityData().put("ATH", new City("ATH", "Athens", 3400000));
        getCityData().put("TOK", new City("TOK", "Tokyo", 37000000));
        getCityData().put("SHA", new City("SHA", "Shanghai", 27000000));
        getCityData().put("DEL", new City("DEL", "Delhi", 31000000));
        getCityData().put("SIN", new City("SIN", "Singapore", 5700000));
        getCityData().put("SYD", new City("SYD", "Sydney", 5300000));
        getCityData().put("MEL", new City("MEL", "Melbourne", 5100000));
        getCityData().put("LA", new City("LA", "Los Angeles", 3900000));
        getCityData().put("RIO", new City("RIO", "Rio de Janeiro", 6700000));
        getCityData().put("MEX", new  City("MEX", "Mexico City", 21000000));
        getCityData().put("CAI", new City("CAI", "Cairo", 20000000));
        getCityData().put("SAO", new City("SAO", "Sao Paulo", 12000000));
        getCityData().put("LIM", new City("LIM", "Lima", 10000000));

        // Loop through each city and add 50 DailyTemp entries
        for (String cityCode : getCityData().keySet()) {
            List<DailyTemp> dailyTemps = new ArrayList<>();
            // Generate random temperatures for 50 days
            for (int i = 0; i < numOfTemperatures; i++) {
                double randomTemp = Math.random() * 40 + 1; // Generate random temp between 1 and 41
                dailyTemps.add(new DailyTemp(new Date(), randomTemp));
            }
            getDailyTempData().put(cityCode, dailyTemps);
        }

        cityIds.addAll(getCityData().keySet());
    }
}

