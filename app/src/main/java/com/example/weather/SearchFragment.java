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
import android.widget.CompoundButton;


public class SearchFragment extends Fragment {

    String result = " ";
    CheckBox temperature, precipitation, wind, humidity, air_pressure;
    EditText city;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        temperature = fragmentView.findViewById(R.id.temperature);
        temperature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = (temperature.getText() + "\n");
                }
            }
        });

        precipitation = fragmentView.findViewById(R.id.precipitation);
        precipitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (precipitation.getText() + "\n");
                }
            }
        });

        wind = fragmentView.findViewById(R.id.wind);
        wind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (wind.getText() + "\n");
                }
            }
        });

        humidity = fragmentView.findViewById(R.id.humidity);
        humidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (humidity.getText() + "\n");
                }
            }
        });

        air_pressure = fragmentView.findViewById(R.id.air_pressure);
        air_pressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (air_pressure.getText() + "\n");
                }
            }
        });

        city = fragmentView.findViewById(R.id.editText);
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


