package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.analysis.WeatherAnalyzer;

public class SimpleDataWeatherAnalyzerExample {

    private final SimpleDataWeatherApiImplExample simpleDataWeatherApiImplExample;
    private final WeatherAnalyzer weatherAnalyzer;

    public SimpleDataWeatherAnalyzerExample() {
        simpleDataWeatherApiImplExample = new SimpleDataWeatherApiImplExample();
        weatherAnalyzer = new WeatherAnalyzer(simpleDataWeatherApiImplExample.getWeatherApiImpl());
    }

    public void simpleDataWeatherApiAnalyzer() {
        simpleDataWeatherAverageTempAnalyzer();
        simpleDataWeatherApiMaxTempAnalyzer();
        simpleDataWeatherApiMedianTempAnalyzer();
        weatherAnalyzer.closeApplication();
    }

    private void simpleDataWeatherAverageTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(simpleDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.AVERAGE);
    }

    private void simpleDataWeatherApiMaxTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(simpleDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MAX);
    }

    private void simpleDataWeatherApiMedianTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(simpleDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MEDIAN);
    }
}
