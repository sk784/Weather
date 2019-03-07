package com.example.weather;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class SearchFragment extends Fragment {

    final StringBuilder result = new StringBuilder();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        CheckBox temperature = fragmentView.findViewById(R.id.temperature);
        CheckBox precipitation = fragmentView.findViewById(R.id.precipitation);
        CheckBox wind = fragmentView.findViewById(R.id.wind);
        CheckBox humidity = fragmentView.findViewById(R.id.humidity);
        CheckBox air_pressure = fragmentView.findViewById(R.id.air_pressure);

        if (temperature.isChecked())
            result.append(temperature.getText() + "\n");
        if (precipitation.isChecked())
            result.append(precipitation.getText() + "\n");
        if (wind.isChecked())
            result.append(wind.getText() + "\n");
        if (humidity.isChecked())
            result.append(humidity.getText() + "\n");
        if (air_pressure.isChecked())
            result.append(air_pressure.getText());

        final EditText city = fragmentView.findViewById(R.id.editText);
        Button button = fragmentView.findViewById(R.id.button);     // Кнопка
        button.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                MainNavigator mainNavigator = (MainNavigator) getActivity();
                mainNavigator.startSecondFragment(city, result);
            }
        });
        return fragmentView;
    }
}


