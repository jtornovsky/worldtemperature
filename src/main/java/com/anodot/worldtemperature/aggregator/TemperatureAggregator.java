package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;

import java.util.List;

public interface TemperatureAggregator {
    double aggregate(List<DailyTemp> temperatures);
}
