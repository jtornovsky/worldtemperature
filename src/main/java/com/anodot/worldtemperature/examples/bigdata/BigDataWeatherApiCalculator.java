package com.anodot.worldtemperature.examples.bigdata;

import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.examples.BaseWeatherApiCalculator;

import java.util.Set;

public class BigDataWeatherApiCalculator extends BaseWeatherApiCalculator {

    public BigDataWeatherApiCalculator() {
        super(new BigDataWeatherAPI());
    }

    public void bigdataWeatherApiAllTempMetricsCalculator() {
        bigdataWeatherApiWithAverageTempCalculator();
        bigdataWeatherApiWithMaxTempCalculator();
        bigdataWeatherApiWithMedianTempCalculator();
        closeApplication();
    }

    private void bigdataWeatherApiWithAverageTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.AVERAGE);
    }

    private void bigdataWeatherApiWithMaxTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MAX);
    }

    private void bigdataWeatherApiWithMedianTempCalculator() {
        weatherApiCalculator(TemperatureAggregatorType.MEDIAN);
    }

    @Override
    protected Set<String> getCityIds() {
        return ((BigDataWeatherAPI)weatherAPI).getCityIds();
    }
}
