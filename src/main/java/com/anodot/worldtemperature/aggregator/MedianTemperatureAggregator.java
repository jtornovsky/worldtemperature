package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class MedianTemperatureAggregator implements TemperatureAggregator {

    @Override
    public double aggregate(List<DailyTemp> temperatures) {

        if (temperatures.isEmpty()) {
            log.info("Temperatures list is empty");
            return 0;
        }

        // Sort temperatures
        Collections.sort(temperatures, Comparator.comparingDouble(DailyTemp::getTemperature));

        int size = temperatures.size();
        double result;
        if (size % 2 == 0) {
            log.debug("Even number of elements, average of middle two");
            int middleIndex = size / 2;

            result = (temperatures.get(middleIndex - 1).getTemperature()
                    + temperatures.get(middleIndex).getTemperature()) / 2.0;
        } else {
            log.debug("Odd number of elements, middle element");
            result = temperatures.get(size/2).getTemperature();
        }

        log.info("Temperatures' median is {}", result);
        return result;
    }
}
