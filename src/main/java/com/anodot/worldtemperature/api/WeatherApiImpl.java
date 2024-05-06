package com.anodot.worldtemperature.api;

import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
@Setter
@Slf4j
public class WeatherApiImpl implements WeatherAPI {

    private Map<String, City> cityData = new HashMap<>();
    private Map<String, List<DailyTemp>> temperatureData = new HashMap<>();

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
