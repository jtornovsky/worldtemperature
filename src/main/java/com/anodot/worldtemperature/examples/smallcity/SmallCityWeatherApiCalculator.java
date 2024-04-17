package com.anodot.worldtemperature.examples.smallcity;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class SmallCityWeatherApiCalculator extends BaseWeatherApiCalculator {

    public SmallCityWeatherApiCalculator() {
        super(new SmallCityWeatherAPI());
    }

    public void smallCityWeatherApiAllTempMetricsCalculator() {
        smallCityWeatherApiWithAverageTempCalculator();
        smallCityWeatherApiWithMaxTempCalculator();
        smallCityWeatherApiWithMedianTempCalculator();
    }

    public void smallCityWeatherApiWithAverageTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    public void smallCityWeatherApiWithMaxTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    public void smallCityWeatherApiWithMedianTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((SmallCityWeatherAPI)weatherAPI).getCityIds();
    }
}
