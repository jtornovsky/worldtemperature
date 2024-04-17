package com.anodot.worldtemperature;

import com.anodot.worldtemperature.examples.simple.SimpleWeatherApiCalculator;
import com.anodot.worldtemperature.examples.smallcity.SmallCityWeatherApiCalculator;


public class Main {

    private final SimpleWeatherApiCalculator simpleWeatherApiCalculator = new SimpleWeatherApiCalculator();
    private final SmallCityWeatherApiCalculator smallCityWeatherApiCalculator = new SmallCityWeatherApiCalculator();

    public static void main(String[] args) {
        Main main = new Main();
//        main.simpleWeatherApiCalculator.simpleWeatherApiAllTempMetricsCalculator();
        main.smallCityWeatherApiCalculator.smallCityWeatherApiAllTempMetricsCalculator();
    }
}