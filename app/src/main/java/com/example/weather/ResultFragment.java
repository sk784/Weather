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

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class ResultFragment extends Fragment {

    private static final String FONT_FILENAME = "fonts/weather.ttf";
    private final Handler handler = new Handler();
    private Typeface weatherFont;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView requiredCity;
    private TextView lastUpdate;
    private TextView cityTemperature;
    private TextView weatherIcon;
    private TextView cityDetails;

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
        lastUpdate = fragmentView.findViewById(R.id.lastUpdate);
        cityTemperature = fragmentView.findViewById(R.id.cityTemperature);
        cityDetails = fragmentView.findViewById(R.id.details);
        weatherIcon = fragmentView.findViewById(R.id.weatherIcon);
        weatherIcon.setTypeface(weatherFont);

        Button buttonBack = fragmentView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        });

        return fragmentView;
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }

    public void changeCityFromCoordinate(String lat,String lon) {
        updateWeatherDataFromCoordinate(lat,lon);
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

    private void updateWeatherDataFromCoordinate(final String lat,final String lon) {
        new Thread() {
            @Override
            public void run() {
                final JSONObject jsonObject = WeatherDataLoader.getJSONDataFromCoordinate(lat,lon);
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
            JSONObject wind = jsonObject.getJSONObject("wind");

            setPlaceName(jsonObject);
            setUpdatedText(jsonObject);
            setCurrentTemp(main);
            setDetails(details, main,wind);
            setWeatherIcon(details.getInt("id"),
                    jsonObject.getJSONObject("sys").getLong("sunrise") * 1000,
                    jsonObject.getJSONObject("sys").getLong("sunset") * 1000);
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

    private void setUpdatedText(JSONObject jsonObject) throws JSONException {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(jsonObject.getLong("dt") * 1000));
        String updatedText = "Last update: " + updateOn;
        lastUpdate.setText(updatedText);
    }

    private void setCurrentTemp(JSONObject main) throws JSONException {
        String currentTextText = String.format("%.2f", main.getDouble("temp")) + "\u2103";
        cityTemperature.setText(currentTextText);
    }

    private void setDetails(JSONObject details, JSONObject main, JSONObject wind) throws JSONException {
        String detailsText = details.getString("description").toUpperCase() + "\n"+ "\n"
                + "Wind: " + wind.getString("speed") + " m/s" + "\n"
                + "Humidity: " + main.getString("humidity") + "%" + "\n"
                + "Pressure: " + main.getString("pressure") + "hPa";
        cityDetails.setText(detailsText);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = getString(R.string.weather_cloudy);
                    break;
                }
            }
        }
        weatherIcon.setText(icon);
    }
}
