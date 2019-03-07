package com.example.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    private StringBuilder result = new StringBuilder();
    private EditText city;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_second, container, false);

        TextView requiredCity = fragmentView.findViewById(R.id.textView);
        requiredCity.setText(city.getText());

        TextView selection = fragmentView.findViewById(R.id.selection);
        selection.setText(result);

        return fragmentView;
    }

    public void setCity(EditText city) {
        this.city = city;
    }

    public void setResult(StringBuilder result){
        this.result = result;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                onPause();
                break;
            default:
                break;
        }
    }
}
