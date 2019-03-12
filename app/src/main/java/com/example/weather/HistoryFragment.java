package com.example.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    ArrayList<String> temperatureData;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_history, container, false);

//        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);
//
//        // эта установка служит для повышения производительности системы.
//        recyclerView.setHasFixedSize(true);
//
//        // будем работать со встроенным менеджером
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
        return fragmentView;
    }
}
