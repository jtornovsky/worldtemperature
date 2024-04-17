package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class SimpleWeatherApiCalculator extends BaseWeatherApiCalculator {

    public SimpleWeatherApiCalculator() {
        super(new SimpleWeatherAPI());
    }

    public void simpleWeatherApiAllTempMetricsCalculator() {
        simpleWeatherApiWithAverageTempCalculator();
        simpleWeatherApiWithMaxTempCalculator();
        simpleWeatherApiWithMedianTempCalculator();
    }

    public void simpleWeatherApiWithAverageTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    public void simpleWeatherApiWithMaxTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    public void simpleWeatherApiWithMedianTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((SimpleWeatherAPI)weatherAPI).getCityIds();
    }
}
