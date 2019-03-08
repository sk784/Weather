package com.example.weather;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.CompoundButton;


public class SearchFragment extends Fragment {

    private StringBuilder result = new StringBuilder();
    private CheckBox temperature, precipitation, wind, humidity, air_pressure;
    private EditText city;
    private static final String TAG = "myLogs";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        temperature = fragmentView.findViewById(R.id.temperature);
        temperature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    AddData(result, temperature);
                    Log.d(TAG, "onCheckedChanged:temperature "+result);
                }
            }
        });

        precipitation = fragmentView.findViewById(R.id.precipitation);
        precipitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    AddData(result, precipitation);
                    Log.d(TAG, "onCheckedChanged:precipitation "+result);
                }
            }
        });

        wind = fragmentView.findViewById(R.id.wind);
        wind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    AddData(result, wind);
                    Log.d(TAG, "onCheckedChanged:wind"+result);
                }
            }
        });

        humidity = fragmentView.findViewById(R.id.humidity);
        humidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    AddData(result, humidity);
                    Log.d(TAG, "onCheckedChanged:humidity "+result);
                }
            }
        });

        air_pressure = fragmentView.findViewById(R.id.air_pressure);
        air_pressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    AddData(result, air_pressure);
                    Log.d(TAG, "onCheckedChanged:air_pressure "+result);
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

    public void AddData(StringBuilder result, CheckBox setting){
        result.append(setting.getText() +"\n" );
    }

    public void onResume() {
        super.onResume();
        result.setLength(0);
    }
}


