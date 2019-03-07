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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        final CheckBox temperature = fragmentView.findViewById(R.id.temperature);
        temperature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = (temperature.getText() + "\n");
                }
            }
        });

        final CheckBox precipitation = fragmentView.findViewById(R.id.precipitation);
        precipitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (precipitation.getText() + "\n");
                }
            }
        });

        final CheckBox wind = fragmentView.findViewById(R.id.wind);
        wind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (wind.getText() + "\n");
                }
            }
        });

        final CheckBox humidity = fragmentView.findViewById(R.id.humidity);
        humidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (humidity.getText() + "\n");
                }
            }
        });

        final CheckBox air_pressure = fragmentView.findViewById(R.id.air_pressure);
        air_pressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    result = result + (air_pressure.getText() + "\n");
                }
            }
        });

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


