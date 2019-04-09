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
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
//        iIntend.putExtra(weatherclassArray,"weatherDetails");
        startActivity(iIntend);
    }

//    public static String getDateFromTimeStamp(long time)
//    {
//        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(time * 1000L);
//        String date = DateFormat.format("EEEE", cal).toString();
//        date += "\n" + DateFormat.format("dd MMM yyyy", cal).toString();
//        date += "\n" + DateFormat.format("hh:mm a", cal).toString();
//        return date;
//    }

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
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
