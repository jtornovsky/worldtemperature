package com.anodot.worldtemperature.analysis;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.*;

import com.anodot.worldtemperature.aggregator.AverageTemperatureAggregator;
import com.anodot.worldtemperature.aggregator.MaxTemperatureAggregator;
import com.anodot.worldtemperature.aggregator.MedianTemperatureAggregator;
import com.anodot.worldtemperature.api.WeatherAPI;
import com.anodot.worldtemperature.model.City;
import com.anodot.worldtemperature.model.DailyTemp;
import org.junit.jupiter.api.*;
import org.mockito.*;

class CityTemperatureAnalyzerTest {

    private final Map<String, City> cityData = new HashMap<>();
    private final Set<String> cityIds = new HashSet<>();
    private final Map<String, List<DailyTemp>> temperatureData = new HashMap<>();

    private final int NUMBER_OF_TOP_CITIES = 3;

    @Mock
    private WeatherAPI weatherAPI;

    @BeforeEach
    void setUp() {
        weatherAPI = mock(WeatherAPI.class);
        generateDataAndMockBehavior();
    }

    @Test
    void testGetTopCitiesByAggregatedAverageTemperature() {
        CityTemperatureAnalyzer analyzer = new CityTemperatureAnalyzer(weatherAPI);
        List<City> result = analyzer.getTopCitiesByAggregatedTemperature(cityIds, new AverageTemperatureAggregator());
        assertEquals(NUMBER_OF_TOP_CITIES, result.size());
        assertEquals("CAI", result.get(0).getId()); // Highest average temperature
        assertEquals("JKT", result.get(1).getId()); // Second-highest average temperature
        assertEquals("BKK", result.get(2).getId()); // Third average temperature
    }

    @Test
    void testGetTopCitiesByAggregatedMaximalTemperature() {
        CityTemperatureAnalyzer analyzer = new CityTemperatureAnalyzer(weatherAPI);
        List<City> result = analyzer.getTopCitiesByAggregatedTemperature(cityIds, new MaxTemperatureAggregator());
        assertEquals(NUMBER_OF_TOP_CITIES, result.size());
        assertEquals("CAI", result.get(0).getId()); // Highest max temperature
        assertEquals("JKT", result.get(1).getId()); // Second-highest max temperature
        assertEquals("BKK", result.get(2).getId()); // Third max temperature
    }

    @Test
    void testGetTopCitiesByAggregatedMedianTemperature() {
        CityTemperatureAnalyzer analyzer = new CityTemperatureAnalyzer(weatherAPI);
        List<City> result = analyzer.getTopCitiesByAggregatedTemperature(cityIds, new MedianTemperatureAggregator());
        assertEquals(NUMBER_OF_TOP_CITIES, result.size());
        assertEquals("CAI", result.get(0).getId()); // Highest median temperature
        assertEquals("JKT", result.get(1).getId()); // Second-highest median temperature
        assertEquals("BKK", result.get(2).getId()); // Third median temperature
    }

    private void generateDataAndMockBehavior() {
        cityData.put("TLV", new City("TLV", "Tel Aviv", 1000000));
        cityData.put("NY", new City("NY", "New York", 8000000));
        cityData.put("LON", new City("LON", "London", 9000000));
        cityData.put("BER", new City("BER", "Berlin", 3700000));
        cityData.put("PAR", new City("PAR", "Paris", 2100000));
        cityData.put("SMC", new City("SMC", "Small City", 40000)); // Small city with population less than 50,000
        cityData.put("AMS", new City("AMS", "Amsterdam", 800000));
        cityData.put("MOS", new City("MOS", "Moscow", 12000000));
        cityData.put("IST", new City("IST", "Istanbul", 15000000));
        cityData.put("BKK", new City("BKK", "Bangkok", 10000000));
        cityData.put("CAI", new City("CAI", "Cairo", 20000000));
        cityData.put("SYD", new City("SYD", "Sydney", 5300000));
        cityData.put("RIO", new City("RIO", "Rio de Janeiro", 6700000));
        cityData.put("MEX", new City("MEX", "Mexico City", 21000000));
        cityData.put("JKT", new City("JKT", "Jakarta", 11000000));
        cityData.put("DEL", new City("DEL", "Delhi", 31000000));

        // Set temperatures for each city
        temperatureData.put("TLV", Arrays.asList(
                new DailyTemp(new Date(), 25.566),
                new DailyTemp(new Date(), 27.0),
                new DailyTemp(new Date(), 26.5222),
                new DailyTemp(new Date(), 24.5599),
                new DailyTemp(new Date(), 25.333)
        ));
        temperatureData.put("NY", Arrays.asList(
                new DailyTemp(new Date(), 20.1679),
                new DailyTemp(new Date(), 22.7899),
                new DailyTemp(new Date(), 21.5435),
                new DailyTemp(new Date(), 20.5233),
                new DailyTemp(new Date(), 22.5896)
        ));
        temperatureData.put("LON", Arrays.asList(
                new DailyTemp(new Date(), 18.0),
                new DailyTemp(new Date(), 17.5),
                new DailyTemp(new Date(), 18.5),
                new DailyTemp(new Date(), 17.0),
                new DailyTemp(new Date(), 18.0)
        ));
        // has third-highest temperature after Jakarta, but won't be taken for calculation due to small population
        temperatureData.put("SMC", Arrays.asList(
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5),
                new DailyTemp(new Date(), 30.5),
                new DailyTemp(new Date(), 32.5)
        ));
        temperatureData.put("BER", Arrays.asList(
                new DailyTemp(new Date(), 15.0),
                new DailyTemp(new Date(), 14.5),
                new DailyTemp(new Date(), 15.5),
                new DailyTemp(new Date(), 14.0),
                new DailyTemp(new Date(), 15.0)
        ));
        temperatureData.put("PAR", Arrays.asList(
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 21.0),
                new DailyTemp(new Date(), 22.0)
        ));
        temperatureData.put("AMS", Arrays.asList(
                new DailyTemp(new Date(), 19.0),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 19.5),
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 20.5)
        ));
        temperatureData.put("MOS", Arrays.asList(
                new DailyTemp(new Date(), 10.0),
                new DailyTemp(new Date(), 12.0),
                new DailyTemp(new Date(), 11.5),
                new DailyTemp(new Date(), 10.5),
                new DailyTemp(new Date(), 12.5)
        ));
        temperatureData.put("IST", Arrays.asList(
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.5),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 24.5)
        ));
        temperatureData.put("BKK", Arrays.asList(
                new DailyTemp(new Date(), 28.1236),
                new DailyTemp(new Date(), 30.2689),
                new DailyTemp(new Date(), 29.5593),
                new DailyTemp(new Date(), 28.5841),
                new DailyTemp(new Date(), 30.5896)
        ));
        temperatureData.put("CAI", Arrays.asList(
                new DailyTemp(new Date(), 30.5668),
                new DailyTemp(new Date(), 32.2385),
                new DailyTemp(new Date(), 31.5638),
                new DailyTemp(new Date(), 33.9523),
                new DailyTemp(new Date(), 32.8531)
        ));
        temperatureData.put("SYD", Arrays.asList(
                new DailyTemp(new Date(), 22.5),
                new DailyTemp(new Date(), 24.0),
                new DailyTemp(new Date(), 23.5),
                new DailyTemp(new Date(), 23.0),
                new DailyTemp(new Date(), 24.0)
        ));
        temperatureData.put("RIO", Arrays.asList(
                new DailyTemp(new Date(), 28.0),
                new DailyTemp(new Date(), 30.0),
                new DailyTemp(new Date(), 29.5),
                new DailyTemp(new Date(), 28.5),
                new DailyTemp(new Date(), 30.5)
        ));
        temperatureData.put("MEX", Arrays.asList(
                new DailyTemp(new Date(), 20.0),
                new DailyTemp(new Date(), 22.0),
                new DailyTemp(new Date(), 21.5),
                new DailyTemp(new Date(), 20.5),
                new DailyTemp(new Date(), 22.5)
        ));
        temperatureData.put("JKT", Arrays.asList(
                new DailyTemp(new Date(), 30.1533),
                new DailyTemp(new Date(), 32.0),
                new DailyTemp(new Date(), 31.5981),
                new DailyTemp(new Date(), 30.3845),
                new DailyTemp(new Date(), 32.9412)
        ));
        temperatureData.put("DEL", Arrays.asList(
                new DailyTemp(new Date(), 25.0),
                new DailyTemp(new Date(), 27.5),
                new DailyTemp(new Date(), 26.0),
                new DailyTemp(new Date(), 25.5),
                new DailyTemp(new Date(), 27.0)
        ));

        cityIds.addAll(cityData.keySet());

        // Mock behavior
        when(weatherAPI.getAllCitiesByIds(cityIds)).thenReturn(getAllCitiesByIds(cityIds));
        for (String cityId : cityIds) {
            when(weatherAPI.getLastYearTemperature(cityId)).thenReturn(temperatureData.get(cityId));
        }
    }

    private Set<City> getAllCitiesByIds(Set<String> cityIds) {
        Set<City> cities = new HashSet<>();
        for (String cityId : cityIds) {
            if (cityData.containsKey(cityId)) {
                cities.add(cityData.get(cityId));
            }
        }
        return cities;
    }
}
