package com.anodot.worldtemperature.examples.smallcity;

import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.examples.BaseWeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

/**
 * A mock implementation of the WeatherAPI interface for demonstrating city temperature analysis
 * with a small city scenario.
 */
class SmallCityWeatherAPI extends BaseWeatherAPI implements WeatherAPI {

    SmallCityWeatherAPI() {
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
        // Add cities with names, populations, and temperature data
        cityData.put("TLV", new City("TLV", "Tel Aviv", 1000000));
        cityData.put("NY", new City("NY", "New York", 8000000));
        cityData.put("LON", new City("LON", "London", 9000000));
        cityData.put("BER", new City("BER", "Berlin", 3700000));
        cityData.put("PAR", new City("PAR", "Paris", 2100000));
        cityData.put("SMC", new City("SMC", "Small City", 40000)); // Small city with population less than 50,000
        cityData.put("AMS", new City("AMS", "Amsterdam", 800000));
        cityData.put("MOS", new City("MOS", "Moscow", 12000000));
        cityData.put("IST", new City("IST", "Istanbul", 15000000));
        cityData.put("BKK", new City("BKK", "Bangkok", 10000000));
        cityData.put("CAI", new City("CAI", "Cairo", 20000000));
        cityData.put("SYD", new City("SYD", "Sydney", 5300000));
        cityData.put("RIO", new City("RIO", "Rio de Janeiro", 6700000));
        cityData.put("MEX", new City("MEX", "Mexico City", 21000000));
        cityData.put("JKT", new City("JKT", "Jakarta", 11000000));
        cityData.put("DEL", new City("DEL", "Delhi", 31000000));

        // Set temperatures for each city
        temperatureData.put("TLV", Arrays.asList(
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0),
                new DailyTemp(new Date(), 26.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 25.0)
        ));
        temperatureData.put("NY", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 22.5)
        ));
        temperatureData.put("LON", Arrays.asList(
                new DailyTemp(new Date(), 18.0),
                new DailyTemp(new Date(), 17.5),
                new DailyTemp(new Date(), 18.5),
                new DailyTemp(new Date(), 17.0),
                new DailyTemp(new Date(), 18.0)
        ));
        temperatureData.put("BER", Arrays.asList(
                new DailyTemp(new Date(), 15.0),
                new DailyTemp(new Date(), 14.5),
                new DailyTemp(new Date(), 15.5),
                new DailyTemp(new Date(), 14.0),
                new DailyTemp(new Date(), 15.0)
        ));
        temperatureData.put("PAR", Arrays.asList(
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 21.0),
                new DailyTemp(new Date(), 22.0)
        ));
        // has second-highest temperature after Cairo, but won't be taken for calculation due to small population
        temperatureData.put("SMC", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        temperatureData.put("AMS", Arrays.asList(
                new DailyTemp(new Date(), 19.0),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 19.5),
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 20.5)
        ));
        temperatureData.put("MOS", Arrays.asList(
                new DailyTemp(new Date(), 10.0),
                new DailyTemp(new Date(), 12.0),
                new DailyTemp(new Date(), 11.5),
                new DailyTemp(new Date(), 10.5),
                new DailyTemp(new Date(), 12.5)
        ));
        temperatureData.put("IST", Arrays.asList(
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.5),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 24.5)
        ));
        temperatureData.put("BKK", Arrays.asList(
                new DailyTemp(new Date(), 28.0),
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 29.5),
                new DailyTemp(new Date(), 28.5),
                new DailyTemp(new Date(), 30.5)
        ));
        temperatureData.put("CAI", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        temperatureData.put("SYD", Arrays.asList(
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.0)
        ));
        temperatureData.put("RIO", Arrays.asList(
                new DailyTemp(new Date(), 28.0),
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 29.5),
                new DailyTemp(new Date(), 28.5),
                new DailyTemp(new Date(), 30.5)
        ));
        temperatureData.put("MEX", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 22.5)
        ));
        temperatureData.put("JKT", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        temperatureData.put("DEL", Arrays.asList(
                new DailyTemp(new Date(), 25.0),
                new DailyTemp(new Date(), 27.5),
                new DailyTemp(new Date(), 26.0),
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0)
        ));

        cityIds.addAll(cityData.keySet());
    }
}

