package com.example.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);     // Кнопка
        button.setOnClickListener(new StartSecondActivity(this));   // Обработка нажатий
    }

    public void onCheckboxClicked(View view) {
        // Получаем флажки
        CheckBox temperature = findViewById(R.id.temperature);
        CheckBox precipitation = findViewById(R.id.precipitation);
        CheckBox wind = findViewById(R.id.wind);
        CheckBox humidity = findViewById(R.id.humidity);
        CheckBox air_pressure = findViewById(R.id.air_pressure);

        String selectedItems = "";
        if(temperature.isChecked())
            selectedItems +=temperature.getText() + "\n";
        if(precipitation.isChecked())
            selectedItems +=precipitation.getText() + "\n";
        if(wind.isChecked())
            selectedItems +=wind.getText() + "\n";
        if(humidity.isChecked())
            selectedItems +=humidity.getText() + "\n";
        if(air_pressure.isChecked())
            selectedItems +=air_pressure.getText();

        TextView selection = findViewById(R.id.selection);
        selection.setText(selectedItems);
    }
}
