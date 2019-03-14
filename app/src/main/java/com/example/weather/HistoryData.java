package com.example.weather;

import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// построитель источника данных
class HistoryData {

    private final List<String> dataSource;   // строим этот источник данных
    private final Resources resources;    // ресурсы приложения

    HistoryData(Resources resources) {
        dataSource = new ArrayList<>(6);
        this.resources = resources;
    }

    // строим данные
    List<String> build() {
        // строки описаний из ресурсов
        String[] descriptions = resources.getStringArray(R.array.temperature_data);
        // заполнение источника данных
        Collections.addAll(dataSource, descriptions);
        return dataSource;
    }


}
