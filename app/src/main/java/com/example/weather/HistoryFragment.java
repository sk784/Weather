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

import java.util.List;

public class HistoryFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);

        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);

        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // создаем источник данных
        HistoryData builder = new HistoryData(getResources());
        final List<String> dataSource = builder.build();
        // установим адаптер
        final HistoryAdapter adapter = new HistoryAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        Button buttonBack = fragmentView.findViewById(R.id.buttonBackToResult);
        buttonBack.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });

        return fragmentView;
    }
}
