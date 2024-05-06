package com.anodot.worldtemperature;

import com.anodot.worldtemperature.examples.bigdata.BigDataWeatherAnalyzerExample;
import com.anodot.worldtemperature.examples.simple.SimpleDataWeatherAnalyzerExample;
import com.anodot.worldtemperature.examples.smallcity.SmallCityWeatherAnalyzerExample;


public class Main {

    public static void main(String[] args) {

        final SimpleDataWeatherAnalyzerExample simpleWeatherApiCalculator = new SimpleDataWeatherAnalyzerExample();
        final SmallCityWeatherAnalyzerExample smallCityWeatherApiCalculator = new SmallCityWeatherAnalyzerExample();
        final BigDataWeatherAnalyzerExample bigDataWeatherApiCalculator = new BigDataWeatherAnalyzerExample();

        simpleWeatherApiCalculator.simpleDataWeatherApiAnalyzer();
        smallCityWeatherApiCalculator.smallCityWeatherApiAnalyzer();
        bigDataWeatherApiCalculator.bigDataWeatherAnalyzer();
    }
}