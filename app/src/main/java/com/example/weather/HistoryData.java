package com.example.weather;

import android.content.res.Resources;
import java.util.ArrayList;
import java.util.List;

// построитель источника данных
public class HistoryData {

    private final List<String> dataSource;   // строим этот источник данных
    private final Resources resources;    // ресурсы приложения

    public HistoryData(Resources resources) {
        dataSource = new ArrayList<>(6);
        this.resources = resources;
    }

    // строим данные
    public List<String> build() {
        // строки описаний из ресурсов
        String[] descriptions = resources.getStringArray(R.array.temperature_data);
        // заполнение источника данных
        for (int i = 0; i < descriptions.length; i++)
            dataSource.add(descriptions[i]);
        return dataSource;
    }


}
