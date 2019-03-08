package com.example.weather;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements MainNavigator{

    private SearchFragment searchFragment;
    private ResultFragment resultFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = new SearchFragment();
        resultFragment = new ResultFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагмент
        fragmentTransaction.add(R.id.search, searchFragment);
        // закрыть транзакцию
        fragmentTransaction.commit();
    }


    @Override
    public void startSecondFragment(EditText city, StringBuilder result) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(searchFragment);

        // добавить фрагмент
        fragmentTransaction.add(R.id.search, resultFragment);
        fragmentTransaction.addToBackStack("");

        // закрыть транзакцию
        fragmentTransaction.commit();

        resultFragment.setCity(city);
        resultFragment.setResult(result);
    }

}

