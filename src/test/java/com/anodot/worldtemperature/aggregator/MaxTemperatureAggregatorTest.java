package com.anodot.worldtemperature.aggregator;

import com.anodot.worldtemperature.model.DailyTemp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaxTemperatureAggregatorTest {

    private TemperatureAggregator aggregator;

    @BeforeEach
    public void setUp() {
        aggregator = new MaxTemperatureAggregator();
    }

    @Test
    public void testAggregate() {
        // Mock temperature data
        DailyTemp temp1 = mock(DailyTemp.class);
        when(temp1.getTemperature()).thenReturn(20.0);

        DailyTemp temp2 = mock(DailyTemp.class);
        when(temp2.getTemperature()).thenReturn(25.0);

        DailyTemp temp3 = mock(DailyTemp.class);
        when(temp3.getTemperature()).thenReturn(30.0);

        List<DailyTemp> temperatures = Arrays.asList(temp1, temp2, temp3);

        // Test the aggregation
        double result = aggregator.aggregate(temperatures);
        assertEquals(30.0, result); // The maximum temperature should be 27.5
    }

    @Test
    public void testAggregateEmptyList() {
        List<DailyTemp> temperatures = new ArrayList<>();

        double result = aggregator.aggregate(temperatures);
        assertEquals(0.0, result);
    }
}
