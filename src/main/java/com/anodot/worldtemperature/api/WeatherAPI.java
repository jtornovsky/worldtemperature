package com.anodot.worldtemperature.api;

import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.List;
import java.util.Set;

public interface WeatherAPI {
    Set<City> getAllCitiesByIds(Set<String> cityIds);
    List<DailyTemp> getLastYearTemperature(String cityId);
}
