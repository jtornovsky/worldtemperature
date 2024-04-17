package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianTemperatureAggregatorTest {

    private TemperatureAggregator aggregator;

    @BeforeEach
    public void setUp() {
        aggregator = new MedianTemperatureAggregator();
    }

    @Test
    public void testAggregateOddNumberOfTemperatures() {
        List<DailyTemp> temperatures = new ArrayList<>();
        temperatures.add(new DailyTemp(null, 25.0));
        temperatures.add(new DailyTemp(null, 27.5));
        temperatures.add(new DailyTemp(null, 24.5));
        temperatures.add(new DailyTemp(null, 26.0));
        temperatures.add(new DailyTemp(null, 22.0));

        double result = aggregator.aggregate(temperatures);
        assertEquals(25.0, result); // Median of {22.0, 24.5, 25.0, 26.0, 27.5} is 25.0
    }

    @Test
    public void testAggregateEvenNumberOfTemperatures() {
        List<DailyTemp> temperatures = new ArrayList<>();
        temperatures.add(new DailyTemp(null, 25.0));
        temperatures.add(new DailyTemp(null, 27.5));
        temperatures.add(new DailyTemp(null, 24.5));
        temperatures.add(new DailyTemp(null, 26.0));

        double result = aggregator.aggregate(temperatures);
        assertEquals(25.5, result); // Median of {24.5, 25.0, 26.0, 27.5} is (25.0 + 26.0) / 2 = 25.5
    }

    @Test
    public void testAggregateSingleTemperature() {
        List<DailyTemp> temperatures = new ArrayList<>();
        temperatures.add(new DailyTemp(null, 25.0));

        double result = aggregator.aggregate(temperatures);
        assertEquals(25.0, result); // Median of {25.0} is 25.0
    }

    @Test
    public void testAggregateEmptyList() {
        List<DailyTemp> temperatures = new ArrayList<>();

        double result = aggregator.aggregate(temperatures);
        assertEquals(0.0, result); // Median of an empty list should be 0.0
    }
}