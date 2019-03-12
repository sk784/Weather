package com.example.weather;

import android.widget.EditText;

public interface MainNavigator {
    void startResultFragment(EditText city, StringBuilder result);
    void startHistoryFragment();
}
