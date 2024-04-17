package com.anodot.worldtemperature;

import com.anodot.worldtemperature.examples.bigdata.BigDataWeatherApiCalculator;
import com.anodot.worldtemperature.examples.simple.SimpleWeatherApiCalculator;
import com.anodot.worldtemperature.examples.smallcity.SmallCityWeatherApiCalculator;


public class Main {

    public static void main(String[] args) {

        final SimpleWeatherApiCalculator simpleWeatherApiCalculator = new SimpleWeatherApiCalculator();
        final SmallCityWeatherApiCalculator smallCityWeatherApiCalculator = new SmallCityWeatherApiCalculator();
        final BigDataWeatherApiCalculator bigDataWeatherApiCalculator = new BigDataWeatherApiCalculator();

        simpleWeatherApiCalculator.simpleWeatherApiAllTempMetricsCalculator();
        smallCityWeatherApiCalculator.smallCityWeatherApiAllTempMetricsCalculator();
        bigDataWeatherApiCalculator.bigdataWeatherApiAllTempMetricsCalculator();
    }
}