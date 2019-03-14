package com.example.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// адаптер
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final List<String> dataSource;                         // Наш источник данных

    // этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на один элемент списка
    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView data;

        ViewHolder(View view) {
            super(view);
            data = view.findViewById(R.id.data);
        }
    }

    // интерфейс для обработки нажатий (как в ListView)
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        // Слушатель, будет устанавливаться извне
        OnItemClickListener itemClickListener1 = itemClickListener;
    }

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    HistoryAdapter(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Создаём новый элемент пользовательского интерфейса через Inflater
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_history, parent, false);
        // Здесь можно установить параметры
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Заменить данные в пользовательском интерфейсе
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Получить элемент из источника данных (БД, интернет…)
         String item = dataSource.get(position);
        // Вывести на экран, используя ViewHolder
        holder.data.setText(item);
    }

    // Вернуть размер данных
    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
