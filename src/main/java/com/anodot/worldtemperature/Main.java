package com.anodot.worldtemperature;

import com.anodot.worldtemperature.examples.simple.SimpleWeatherApiCalculator;


public class Main {

    private SimpleWeatherApiCalculator simpleWeatherApiCalculator = new SimpleWeatherApiCalculator();

    public static void main(String[] args) {
        Main main = new Main();
        main.simpleWeatherApiCalculator.simpleWeatherApiWithAverageTempCalculator();
    }
}