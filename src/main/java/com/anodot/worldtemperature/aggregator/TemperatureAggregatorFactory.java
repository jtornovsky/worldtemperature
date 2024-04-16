package com.anodot.worldtemperature.aggregator;

/**
 * Factory class to create instances of TemperatureAggregator based on the specified type
 */
public class TemperatureAggregatorFactory {

    // Private constructor to prevent instantiation of the factory class
    private TemperatureAggregatorFactory() {
    }

    /**
     * Method to create a TemperatureAggregator based on the specified type
     * @param type
     * @return
     */
    public static TemperatureAggregator createTemperatureAggregator(TemperatureAggregatorType type) {
        switch (type) {
            case AVERAGE:
                return new AverageTemperatureAggregator();
            case MAX:
                return new MaxTemperatureAggregator();
            case MEDIAN:
                return new MedianTemperatureAggregator();
            default:
                throw new IllegalArgumentException("Unsupported temperature aggregator type: " + type);
        }
    }
}
