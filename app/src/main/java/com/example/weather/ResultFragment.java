package com.example.weather;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ResultFragment extends Fragment {

    private static final String FONT_FILENAME = "fonts/weather.ttf";
    private final Handler handler = new Handler();
    private Typeface weatherFont;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView requiredCity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(Objects.requireNonNull(getActivity()).getAssets(),FONT_FILENAME);
        updateWeatherData(new CityPreference(getActivity()).getCity());
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_result, container, false);

        requiredCity = fragmentView.findViewById(R.id.cityName);
        TextView lastUpdate = fragmentView.findViewById(R.id.lastUpdate);
        TextView cityTemperature = fragmentView.findViewById(R.id.cityTemperature);
        TextView cityDetails = fragmentView.findViewById(R.id.details);
        TextView weatherIcon = fragmentView.findViewById(R.id.weatherIcon);
        weatherIcon.setTypeface(weatherFont);

       // if (savedInstanceState != null) {
         //   city = savedInstanceState.getString("City", "M");
      //  }

       // requiredCity.setText(city);
       // cityTemperature.setText(MessageFormat.format("{0} {1}", getString(R.string.text2), temperature));

        Button buttonBack = fragmentView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        });

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
       // saveInstanceState.putString("City", city);
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }


    private void updateWeatherData(final String city) {
        new Thread() {
            @Override
            public void run() {
                final JSONObject jsonObject = WeatherDataLoader.getJSONData(city);
                if(jsonObject == null) {
                    handler.post(() -> Toast.makeText(getActivity(), R.string.place_not_found, Toast.LENGTH_LONG).show());
                } else {
                    handler.post(() -> renderWeather(jsonObject));
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject jsonObject) {
        Log.d(LOG_TAG, "json: " + jsonObject.toString());
        try {
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");

            setPlaceName(jsonObject);
           // setDetails(details, main);
           // setCurrentTemp(main);
           // setUpdatedText(jsonObject);
           // setWeatherIcon(details.getInt("id"),
           //         jsonObject.getJSONObject("sys").getLong("sunrise") * 1000,
            //        jsonObject.getJSONObject("sys").getLong("sunset") * 1000);
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e(LOG_TAG, "One or more fields not found in the JSON data");
        }
    }

    private void setPlaceName(JSONObject jsonObject) throws JSONException {
        String cityText = jsonObject.getString("name").toUpperCase() + ", "
                + jsonObject.getJSONObject("sys").getString("country");
        requiredCity.setText(cityText);
    }
}
