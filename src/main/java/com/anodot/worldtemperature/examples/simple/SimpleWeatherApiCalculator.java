package com.anodot.worldtemperature.examples.simple;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorFactory;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.analysis.CityTemperatureAnalyzer;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;
import com.anodot.worldtemperature.model.City;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
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
        simpleWeatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    public void simpleWeatherApiWithMaxTempCalculator() {
        simpleWeatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    public void simpleWeatherApiWithMedianTempCalculator() {
        simpleWeatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((SimpleWeatherAPI)weatherAPI).getCityIds();
    }
}
