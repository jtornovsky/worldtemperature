package com.anodot.worldtemperature.analysis;

import com.anodot.worldtemperature.aggregator.TemperatureAggregator;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CityTemperatureAnalyzer {

    private WeatherAPI weatherAPI;

    /**
     * Implementation to retrieve city data, fetch temperature data, and aggregate temperatures
     *
     * @param cityIds
     * @param aggregator
     * @return the top cities based on the aggregated temperature
     */
    public List<City> getTopCitiesByAggregatedTemperature(Set<String> cityIds, TemperatureAggregator aggregator) {

        return null;
    }
}

