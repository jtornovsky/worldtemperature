package com.anodot.worldtemperature.examples.bigdata;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.analysis.WeatherAnalyzer;

public class BigDataWeatherAnalyzerExample {

    private final BigDataWeatherApiImplExample bigDataWeatherApiImplExample;
    private final WeatherAnalyzer weatherAnalyzer;

    public BigDataWeatherAnalyzerExample() {
        bigDataWeatherApiImplExample = new BigDataWeatherApiImplExample();
        weatherAnalyzer = new WeatherAnalyzer(bigDataWeatherApiImplExample.getWeatherApiImpl());
    }

    public void bigDataWeatherAnalyzer() {
        bigDataWeatherAverageTempAnalyzer();
        bigDataWeatherMaxTempAnalyzer();
        bigDataWeatherMedianTempAnalyzer();
        weatherAnalyzer.closeApplication();
    }

    private void bigDataWeatherAverageTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(bigDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.AVERAGE);
    }

    private void bigDataWeatherMaxTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(bigDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MAX);
    }

    private void bigDataWeatherMedianTempAnalyzer() {
        weatherAnalyzer.analyzeWeather(bigDataWeatherApiImplExample.getCityIds(), TemperatureAggregatorType.MEDIAN);
    }
}
