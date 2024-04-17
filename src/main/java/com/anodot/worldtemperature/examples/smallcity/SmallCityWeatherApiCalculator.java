package com.anodot.worldtemperature.examples.smallcity;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;

import java.util.Set;

public class SmallCityWeatherApiCalculator extends BaseWeatherApiCalculator {

    public SmallCityWeatherApiCalculator() {
        super(new SmallCityWeatherAPI());
    }

    public void smallCityWeatherApiAllTempMetricsCalculator() {
        smallCityWeatherApiWithAverageTempCalculator();
        smallCityWeatherApiWithMaxTempCalculator();
        smallCityWeatherApiWithMedianTempCalculator();
        closeApplication();
    }

    private void smallCityWeatherApiWithAverageTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    private void smallCityWeatherApiWithMaxTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    private void smallCityWeatherApiWithMedianTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((SmallCityWeatherAPI)weatherAPI).getCityIds();
    }
}
