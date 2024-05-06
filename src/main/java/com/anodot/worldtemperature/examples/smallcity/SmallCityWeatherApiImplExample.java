package com.anodot.worldtemperature.examples.smallcity;

import com.anodot.worldtemperature.examples.WeatherApiImplBaseExample;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * A mock implementation for demonstrating city temperature analysis
 * with a small city scenario.
 */
class SmallCityWeatherApiImplExample extends WeatherApiImplBaseExample {

    SmallCityWeatherApiImplExample() {
        generateData();
    }

    public Set<String> getCityIds() {
        return cityIds;
    }

    @Override
    protected void generateData() {
        // Add cities with names, populations, and temperature data
        getCityData().put("TLV", new City("TLV", "Tel Aviv", 1000000));
        getCityData().put("NY", new City("NY", "New York", 8000000));
        getCityData().put("LON", new City("LON", "London", 9000000));
        getCityData().put("BER", new City("BER", "Berlin", 3700000));
        getCityData().put("PAR", new City("PAR", "Paris", 2100000));
        getCityData().put("SMC", new City("SMC", "Small City", 40000)); // Small city with population less than 50,000
        getCityData().put("AMS", new City("AMS", "Amsterdam", 800000));
        getCityData().put("MOS", new City("MOS", "Moscow", 12000000));
        getCityData().put("IST", new City("IST", "Istanbul", 15000000));
        getCityData().put("BKK", new City("BKK", "Bangkok", 10000000));
        getCityData().put("CAI", new City("CAI", "Cairo", 20000000));
        getCityData().put("SYD", new City("SYD", "Sydney", 5300000));
        getCityData().put("RIO", new City("RIO", "Rio de Janeiro", 6700000));
        getCityData().put("MEX", new City("MEX", "Mexico City", 21000000));
        getCityData().put("JKT", new City("JKT", "Jakarta", 11000000));
        getCityData().put("DEL", new City("DEL", "Delhi", 31000000));

        // Set temperatures for each city
        getDailyTempData().put("TLV", Arrays.asList(
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0),
                new DailyTemp(new Date(), 26.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 25.0)
        ));
        getDailyTempData().put("NY", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 22.5)
        ));
        getDailyTempData().put("LON", Arrays.asList(
                new DailyTemp(new Date(), 18.0),
                new DailyTemp(new Date(), 17.5),
                new DailyTemp(new Date(), 18.5),
                new DailyTemp(new Date(), 17.0),
                new DailyTemp(new Date(), 18.0)
        ));
        getDailyTempData().put("BER", Arrays.asList(
                new DailyTemp(new Date(), 15.0),
                new DailyTemp(new Date(), 14.5),
                new DailyTemp(new Date(), 15.5),
                new DailyTemp(new Date(), 14.0),
                new DailyTemp(new Date(), 15.0)
        ));
        getDailyTempData().put("PAR", Arrays.asList(
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 21.0),
                new DailyTemp(new Date(), 22.0)
        ));
        // has second-highest temperature after Cairo, but won't be taken for calculation due to small population
        getDailyTempData().put("SMC", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        getDailyTempData().put("AMS", Arrays.asList(
                new DailyTemp(new Date(), 19.0),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 19.5),
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 20.5)
        ));
        getDailyTempData().put("MOS", Arrays.asList(
                new DailyTemp(new Date(), 10.0),
                new DailyTemp(new Date(), 12.0),
                new DailyTemp(new Date(), 11.5),
                new DailyTemp(new Date(), 10.5),
                new DailyTemp(new Date(), 12.5)
        ));
        getDailyTempData().put("IST", Arrays.asList(
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.5),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 24.5)
        ));
        getDailyTempData().put("BKK", Arrays.asList(
                new DailyTemp(new Date(), 28.0),
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 29.5),
                new DailyTemp(new Date(), 28.5),
                new DailyTemp(new Date(), 30.5)
        ));
        getDailyTempData().put("CAI", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        getDailyTempData().put("SYD", Arrays.asList(
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.0)
        ));
        getDailyTempData().put("RIO", Arrays.asList(
                new DailyTemp(new Date(), 28.0),
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 29.5),
                new DailyTemp(new Date(), 28.5),
                new DailyTemp(new Date(), 30.5)
        ));
        getDailyTempData().put("MEX", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 22.5)
        ));
        getDailyTempData().put("JKT", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        getDailyTempData().put("DEL", Arrays.asList(
                new DailyTemp(new Date(), 25.0),
                new DailyTemp(new Date(), 27.5),
                new DailyTemp(new Date(), 26.0),
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0)
        ));

        cityIds.addAll(getCityData().keySet());
    }
}

