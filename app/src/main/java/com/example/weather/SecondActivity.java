package com.example.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        try {
            String text = getIntent().getExtras().getString(StartSecondActivity.TEXT); // получить данные из Intent
            TextView textView = findViewById(R.id.textView);
            textView.setText(text); // Сохранить их в TextView
        } catch (NullPointerException e) {
            Toast.makeText(this, "Нет данных!", Toast.LENGTH_LONG).show();
        }
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                finish();
                break;
            default:
                break;
        }
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

