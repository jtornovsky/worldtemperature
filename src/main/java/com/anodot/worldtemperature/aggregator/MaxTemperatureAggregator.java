package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;

import java.util.List;

public class MaxTemperatureAggregator implements TemperatureAggregator {

    @Override
    public double aggregate(List<DailyTemp> temperatures) {
        return 0;
    }
}
