package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AverageTemperatureAggregator implements TemperatureAggregator {

    @Override
    public double aggregate(List<DailyTemp> temperatures) {

        if (temperatures.isEmpty()) {
            log.info("Temperatures list is empty");
            return 0;
        }

        double sum = 0;
        for (DailyTemp temp : temperatures) {
            sum += temp.getTemperature();
        }
        double average = sum / temperatures.size();
        return average;
    }
}
