package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MaxTemperatureAggregator implements TemperatureAggregator {

    @Override
    public double aggregate(List<DailyTemp> temperatures) {

        if (temperatures.isEmpty()) {
            log.info("Temperatures list is empty");
            return 0;
        }

        double maxTemp = Double.MIN_VALUE;
        for (DailyTemp temp : temperatures) {
            maxTemp = Math.max(maxTemp, temp.getTemperature());
        }
        return maxTemp;
    }
}
