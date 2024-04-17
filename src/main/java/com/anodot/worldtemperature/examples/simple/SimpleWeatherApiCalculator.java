package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;

import java.util.Set;

public class SimpleWeatherApiCalculator extends BaseWeatherApiCalculator {

    public SimpleWeatherApiCalculator() {
        super(new SimpleWeatherAPI());
    }

    public void simpleWeatherApiAllTempMetricsCalculator() {
        simpleWeatherApiWithAverageTempCalculator();
        simpleWeatherApiWithMaxTempCalculator();
        simpleWeatherApiWithMedianTempCalculator();
        closeApplication();
    }

    private void simpleWeatherApiWithAverageTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    private void simpleWeatherApiWithMaxTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    private void simpleWeatherApiWithMedianTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((SimpleWeatherAPI)weatherAPI).getCityIds();
    }
}
