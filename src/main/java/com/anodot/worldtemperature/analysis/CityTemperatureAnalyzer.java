package com.anodot.worldtemperature.analysis;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CityTemperatureAnalyzer {

    private WeatherAPI weatherAPI;

    public CityTemperatureAnalyzer(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public List<City> getTopCitiesByAggregatedTemperature(Set<String> cityIds, TemperatureAggregator aggregator) {
        // Implementation to retrieve city data, fetch temperature data, and aggregate temperatures
        // Returns the top cities based on the aggregated temperature

        return null;
    }
}

