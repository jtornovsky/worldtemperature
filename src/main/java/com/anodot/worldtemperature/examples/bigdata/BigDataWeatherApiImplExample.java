package com.anodot.worldtemperature.examples.bigdata;

import com.anodot.worldtemperature.examples.WeatherApiImplBaseExample;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

import static com.anodot.worldtemperature.util.StringUtils.generateRandomString;

/**
 * A big data implementation, providing mock of big weather data.
 * This implementation generates data for a set of predefined cities and their corresponding temperature records.
 */
class BigDataWeatherApiImplExample extends WeatherApiImplBaseExample {

    private final Random random = new Random();

    BigDataWeatherApiImplExample() {
        generateData();
    }

    public Set<String> getCityIds() {
        return cityIds;
    }

    @Override
    protected void generateData() {

        // Define the number of cities to generate
        int numCities = 100000;
        int numOfTemperatures = 365;

        // Generate random city data
        for (int i = 0; i < numCities; i++) {
            String cityCode = generateRandomString(3); // Generate 3-letter code
            String cityName = generateRandomString(10); // Generate random city name
            int population = random.nextInt(100000000) + 100000; // Generate random population

            getCityData().put(cityCode, new City(cityCode, cityName, population));
        }

        // Generate random temperature data for each city for 'numOfTemperatures' days
        for (String cityCode : getCityData().keySet()) {
            List<DailyTemp> dailyTemps = new ArrayList<>();
            for (int i = 0; i < numOfTemperatures; i++) {
                double minTemp = -10; // Adjust minimum temperature as needed
                double maxTemp = 40;  // Adjust maximum temperature as needed
                double randomTemp = minTemp + (maxTemp - minTemp) * random.nextDouble();
                dailyTemps.add(new DailyTemp(new Date(), randomTemp));
            }
            getDailyTempData().put(cityCode, dailyTemps);
        }

        cityIds.addAll(getCityData().keySet());
    }
}

