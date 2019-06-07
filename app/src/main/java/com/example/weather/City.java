package com.example.weather;

public class City {

    private final String name;
    private final String temperature;
    private final String wind;
    private final String humidity;

    City(String name, String temperature, String wind, String humidity) {
        this.name = name;
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getHumidity() {
        return humidity;
    }


}

