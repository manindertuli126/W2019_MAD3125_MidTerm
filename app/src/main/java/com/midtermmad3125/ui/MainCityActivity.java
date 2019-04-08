package com.midtermmad3125.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainCityActivity extends AppCompatActivity
{

    private TextView cityname;
    private TextView citynlat;
    private TextView citylon;
    private TextView citycode;
    private TextView citypopulation;

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

    public void getJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject cityData = new JSONObject(JsonData);
            JSONObject cityObject= cityData.getJSONObject("city");
            cityname.setText(cityObject.getString("name"));

            JSONObject cityCoordObject= cityObject.getJSONObject("coord");
            citylon.setText("Longitude: "+cityCoordObject.getString("lon"));
            citynlat.setText("Latitude: "+cityCoordObject.getString("lat"));

            citycode.setText("Country: "+cityObject.getString("country"));
            citypopulation.setText("population: "+cityObject.getString("population"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
