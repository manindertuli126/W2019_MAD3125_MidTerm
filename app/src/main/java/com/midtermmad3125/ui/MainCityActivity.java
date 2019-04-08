package com.midtermmad3125.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainCityActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJsonData();
    }

    public void getJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject cityData = new JSONObject(JsonData);
            JSONObject cityObject= cityData.getJSONObject("city");
            String cityid = cityObject.getString("name");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
