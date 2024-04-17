package com.anodot.worldtemperature.aggregator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemperatureAggregatorFactoryTest {

    @Test
    public void testCreateAggregatorWithAverageType() {
        TemperatureAggregator aggregator = TemperatureAggregatorFactory.createTemperatureAggregator(TemperatureAggregatorType.AVERAGE);
        assertTrue(aggregator instanceof AverageTemperatureAggregator);
    }

    @Test
    public void testCreateAggregatorWithMaxType() {
        TemperatureAggregator aggregator = TemperatureAggregatorFactory.createTemperatureAggregator(TemperatureAggregatorType.MAX);
        assertTrue(aggregator instanceof MaxTemperatureAggregator);
    }

    @Test
    public void testCreateAggregatorWithMedianType() {
        TemperatureAggregator aggregator = TemperatureAggregatorFactory.createTemperatureAggregator(TemperatureAggregatorType.MEDIAN);
        assertTrue(aggregator instanceof MedianTemperatureAggregator);
    }

    @Test
    public void testCreateAggregatorWithUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureAggregatorFactory.createTemperatureAggregator(TemperatureAggregatorType.valueOf("UNKNOWN"));
        });
    }
}