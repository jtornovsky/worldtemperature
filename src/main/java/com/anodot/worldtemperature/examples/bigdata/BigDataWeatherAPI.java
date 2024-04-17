package com.anodot.worldtemperature.examples.bigdata;

import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.examples.BaseWeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

import static com.anodot.worldtemperature.util.StringUtils.generateRandomString;

/**
 *
 */
class BigDataWeatherAPI extends BaseWeatherAPI implements WeatherAPI {

    private final Random random = new Random();

    BigDataWeatherAPI() {
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

        // Define the number of cities to generate
        int numCities = 10000;
        int numOfTemperatures = 365;

        // Generate random city data
        for (int i = 0; i < numCities; i++) {
            String cityCode = generateRandomString(3); // Generate 3-letter code
            String cityName = generateRandomString(10); // Generate random city name
            int population = random.nextInt(100000000) + 100000; // Generate random population

            cityData.put(cityCode, new City(cityCode, cityName, population));
        }

        // Generate random temperature data for each city for 'numOfTemperatures' days
        for (String cityCode : cityData.keySet()) {
            List<DailyTemp> dailyTemps = new ArrayList<>();
            for (int i = 0; i < numOfTemperatures; i++) {
                double minTemp = -10; // Adjust minimum temperature as needed
                double maxTemp = 40;  // Adjust maximum temperature as needed
                double randomTemp = minTemp + (maxTemp - minTemp) * random.nextDouble();
                dailyTemps.add(new DailyTemp(new Date(), randomTemp));
            }
            temperatureData.put(cityCode, dailyTemps);
        }

        cityIds.addAll(cityData.keySet());
    }
}

