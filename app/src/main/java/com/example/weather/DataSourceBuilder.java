package com.example.weather;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class DataSourceBuilder {
    private final List<City> dataSource;
    private final Resources resources;

    public DataSourceBuilder(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public List<City> build() {
        String[] cities = resources.getStringArray(R.array.cities);
        String[] temperatures = resources.getStringArray(R.array.temperatures);
        String[] wind = resources.getStringArray(R.array.wind);
        String[] humidity = resources.getStringArray(R.array.humidity);

        for (int i = 0; i < cities.length; i++)
            dataSource.add(new City(cities[i],temperatures[i],wind[i],humidity[i]));
        return dataSource;
    }
}
