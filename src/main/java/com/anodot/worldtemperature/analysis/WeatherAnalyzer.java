package com.anodot.worldtemperature.analysis;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorFactory;
import com.anodot.worldtemperature.aggregator.TemperatureAggregatorType;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class WeatherAnalyzer {

    private final CityTemperatureAnalyzer analyzer;
    private final DecimalFormat df = new DecimalFormat("#.###");

    public WeatherAnalyzer(WeatherAPI weatherAPI) {
        // Create CityTemperatureAnalyzer instance
        analyzer = new CityTemperatureAnalyzer(weatherAPI);
    }

    public void analyzeWeather(Set<String> cityIds, TemperatureAggregatorType aggregationType) {

        // Get appropriate temperature aggregator based on aggregation type
        TemperatureAggregator aggregator = TemperatureAggregatorFactory.createTemperatureAggregator(aggregationType);

        // Get top cities by aggregated temperature
        List<Map.Entry<City, Double>> topCities = analyzer.getTopCitiesByAggregatedTemperature(cityIds, aggregator);

        // Output top cities with aggregated temperature
        log.info("Top {} cities with aggregated temperature ({}):", topCities.size(), aggregationType);
        for (Map.Entry<City, Double> entry : topCities) {
            log.info("{} -> {}", entry.getKey().getName(), df.format(entry.getValue()));
        }
    }

    public void closeApplication() {
        log.info("Application ended.");
        analyzer.close();
    }
}
