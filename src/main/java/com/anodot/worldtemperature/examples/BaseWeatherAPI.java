package com.anodot.worldtemperature.examples;

import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;

import java.util.*;

abstract class BaseWeatherAPI {

    protected Map<String, City> cityData = new HashMap<>();
    protected Set<String> cityIds = new HashSet<>();
    protected Map<String, List<DailyTemp>> temperatureData = new HashMap<>();

    protected abstract void generateData();
}
