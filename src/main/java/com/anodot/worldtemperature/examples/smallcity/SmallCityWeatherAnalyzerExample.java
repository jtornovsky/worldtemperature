package com.anodot.worldtemperature.examples.smallcity;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.analysis.WeatherAnalyzer;

public class SmallCityWeatherAnalyzerExample {

    private final SmallCityWeatherApiImplExample smallCityWeatherApiImplExample;
    private final WeatherAnalyzer weatherAnalyzer;

    public SmallCityWeatherAnalyzerExample() {
        smallCityWeatherApiImplExample = new SmallCityWeatherApiImplExample();
        weatherAnalyzer = new WeatherAnalyzer(smallCityWeatherApiImplExample.getWeatherApiImpl());
    }

    public void smallCityWeatherApiAnalyzer() {
        smallCityWeatherApiAverageTempAnalyzer();
        smallCityWeatherApiMaxTempAnalyzer();
        smallCityWeatherApiMedianTempAnalyzer();
        weatherAnalyzer.closeApplication();
    }

    private void smallCityWeatherApiAverageTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(smallCityWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.AVERAGE);
    }

    private void smallCityWeatherApiMaxTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(smallCityWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MAX);
    }

    private void smallCityWeatherApiMedianTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(smallCityWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MEDIAN);
    }
}
