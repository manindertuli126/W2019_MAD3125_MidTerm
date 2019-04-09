package com.midtermmad3125.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.midtermmad3125.Modal.Temperature;
import com.midtermmad3125.Modal.Weather;
import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity
{
    private ArrayList<weatherList> weatherDataCollection;
    private ListView displayWeather;
    private ArrayList<String> iname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        Intent i = getIntent();
        getJsonWeatherData();

        for(weatherList l: weatherDataCollection){
            iname.add(l.getSpeed());
        }

        ArrayAdapter<String> weatList = new ArrayAdapter<String>(this, R.layout.activity_weather_list,iname);
        displayWeather.setAdapter(weatList);
    }

    public void getJsonWeatherData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject Data = new JSONObject(JsonData);
            JSONArray wList = Data.getJSONArray("list");
            weatherDataCollection = new ArrayList<>();
            for(int y2 = 0; y2 < wList.length(); y2++){
                JSONObject weatherDetail = wList.getJSONObject(y2);
                String weatherDate = weatherDetail.getString("dt");

                String weatherpressure = weatherDetail.getString("pressure");
                String weatherhumidity = weatherDetail.getString("humidity");
                String weatherspeed = weatherDetail.getString("speed");
                String weatherdeg = weatherDetail.getString("deg");
                String weatherclouds = weatherDetail.getString("clouds");
                String weatherrain = weatherDetail.getString("rain");

                JSONObject weatherTemp = weatherDetail.getJSONObject("temp");
                String weatherday = weatherTemp.getString("day");
                String weathermin = weatherTemp.getString("min");
                String weathermax = weatherTemp.getString("max");
                String weathernight = weatherTemp.getString("night");
                String weathereve = weatherTemp.getString("eve");
                String weathermorn = weatherTemp.getString("morn");
                Temperature temp = new Temperature(weatherday,weathermin,weathermax,weathernight,weathereve,weathermorn);

                JSONArray weatherdet = weatherDetail.getJSONArray("weather");
                JSONObject weatherDes = weatherdet.getJSONObject(0);
                String weatherid = weatherDes.getString("id");
                String weathermain = weatherDes.getString("main");
                String weatherdescription = weatherDes.getString("description");
                String weathericon = weatherDes.getString("icon");
                Weather weather = new Weather(weatherid,weathermain,weatherdescription,weathericon);

                weatherDataCollection.add(new weatherList(weatherDate,temp,weatherpressure,weatherhumidity,weather,weatherspeed,weatherdeg,weatherclouds,weatherrain));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
}}
