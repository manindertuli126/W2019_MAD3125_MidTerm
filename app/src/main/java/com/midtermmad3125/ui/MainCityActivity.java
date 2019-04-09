package com.midtermmad3125.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.midtermmad3125.Modal.Temperature;
import com.midtermmad3125.Modal.Weather;
import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainCityActivity extends AppCompatActivity
{

    private TextView cityname;
    private TextView citynlat;
    private TextView citylon;
    private TextView citycode;
    private TextView citypopulation;
    private ArrayList<weatherList> weatherclassArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityname = findViewById(R.id.citynamelbl);
        citynlat = findViewById(R.id.citylatlbl);
        citylon = findViewById(R.id.citylonlbl);
        citycode = findViewById(R.id.citycodelbl);
        citypopulation = findViewById(R.id.citypoplbl);

        getJsonData();



    }

    public void weatherDetailsclick(View v){
        Intent iIntend = new Intent(MainCityActivity.this,WeatherListActivity.class);
        startActivity(iIntend);
    }

    public void getJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject Data = new JSONObject(JsonData);
            JSONObject cityObject= Data.getJSONObject("city");
            cityname.setText(cityObject.getString("name"));

            JSONObject cityCoordObject= cityObject.getJSONObject("coord");
            citylon.setText("Longitude: "+cityCoordObject.getString("lon"));
            citynlat.setText("Latitude: "+cityCoordObject.getString("lat"));

            citycode.setText("Country: "+cityObject.getString("country"));
            citypopulation.setText("Population: "+cityObject.getString("population"));

            JSONArray wList = Data.getJSONArray("list");
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

                JSONObject weatherdet = weatherDetail.getJSONObject("weather");
                String weatherid = weatherdet.getString("id");
                String weathermain = weatherdet.getString("main");
                String weatherdescription = weatherdet.getString("description");
                String weathericon = weatherdet.getString("icon");
                Weather weather = new Weather(weatherid,weathermain,weatherdescription,weathericon);

                weatherclassArray.add(new weatherList(weatherDate,temp,weatherpressure,weatherhumidity,weather,weatherspeed,weatherdeg,weatherclouds,weatherrain));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
