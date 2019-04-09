package com.midtermmad3125.Modal;

import java.io.Serializable;

public class weatherList implements Serializable {

    private String dt;
    private Temperature cityTemp;
    private String pressure;
    private String humidity;
    private Weather cityWeather;
    private String speed;
    private String deg;
    private String clouds;
    private String rain;

    public weatherList(String dt, Temperature cityTemp, String pressure, String humidity, Weather cityWeather, String speed, String deg, String clouds, String rain) {
        this.dt = dt;
        this.cityTemp = cityTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.cityWeather = cityWeather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Temperature getCityTemp() {
        return cityTemp;
    }

    public void setCityTemp(Temperature cityTemp) {
        this.cityTemp = cityTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Weather getCityWeather() {
        return cityWeather;
    }

    public void setCityWeather(Weather cityWeather) {
        this.cityWeather = cityWeather;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }
}
