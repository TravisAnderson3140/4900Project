package com.example.v1;

import java.util.List;
public class Forecast {
    public List<ForecastDay> forecastday;
    public List<ForecastDay> getForecastday() {
        return forecastday;
    }
    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}