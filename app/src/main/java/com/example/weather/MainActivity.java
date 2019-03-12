package com.example.weather;

import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainNavigator {

    private SearchFragment searchFragment;
    private ResultFragment resultFragment;
    private HistoryFragment historyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);

        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // создаем источник данных
        HistoryData builder = new HistoryData(getResources());
        final List<String> dataSource = builder.build();
        // установим адаптер
        final HistoryAdapter adapter = new HistoryAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        searchFragment = new SearchFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагмент
        fragmentTransaction.add(R.id.main, searchFragment);
        // закрыть транзакцию
        fragmentTransaction.commit();
    }


    @Override
    public void startResultFragment(EditText city, StringBuilder result) {
        resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(searchFragment);

        // добавить фрагмент
        fragmentTransaction.add(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");

        // закрыть транзакцию
        fragmentTransaction.commit();

        resultFragment.setCity(city);
        resultFragment.setResult(result);
    }

    @Override
    public void startHistoryFragment() {
        historyFragment = new HistoryFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(resultFragment);

        // добавить фрагмент
        fragmentTransaction.add(R.id.main, historyFragment);
        fragmentTransaction.addToBackStack("");

        // закрыть транзакцию
        fragmentTransaction.commit();
    }
}

