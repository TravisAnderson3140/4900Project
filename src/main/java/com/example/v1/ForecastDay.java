package com.example.v1;

import java.util.List;

public class ForecastDay {
    public String date;
    public Integer date_epoch;
    public Day day;
    public Astro astro;
    public List<Hour> hour;

    public String getDate() {
        return date;
    }
    public Integer getDate_epoch() {
        return date_epoch;
    }
    public Day getDay() {
        return day;
    }
    public Astro getAstro() {
        return astro;
    }
    public List<Hour> getHour() {
        return hour;
    }
}
