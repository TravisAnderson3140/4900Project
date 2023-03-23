package com.example.v1;

public class Current {

    public Condition condition;
    public Integer last_updated_epoch;
    public String last_updated;
    public Double temp_c;
    public Double temp_f;
    public Integer is_day;
    public Double wind_mph;
    public Double wind_kph;
    public Integer wind_degree;
    public String wind_dir;
    public Double pressure_mb;
    public Double pressure_in;
    public Double precip_mm;
    public Double precip_in;
    public Integer humidity;
    public Integer cloud;
    public Double feelslike_c;
    public Double feelslike_f;
    public Double vis_km;
    public Double vis_miles;
    public Double uv;
    public Double gust_mph;
    public Double gust_kph;

    public Integer getLast_updated_epoch() {
        return last_updated_epoch;
    }
    public String getLast_updated() {
        return last_updated;
    }
    public Double getTemp_c() {
        return temp_c;
    }
    public Double getTemp_f() {
        return temp_f;
    }
    public Integer getIs_day() {
        return is_day;
    }
    public Condition getCondition() {
        return condition;
    }
    public Double getWind_mph() {
        return wind_mph;
    }
    public Double getWindKph() {
        return wind_kph;
    }
    public Integer getWind_degree() {
        return wind_degree;
    }
    public String getWind_dir() {
        return wind_dir;
    }
    public Double getPressure_mb() {
        return pressure_mb;
    }
    public Double getPressure_in() {
        return pressure_in;
    }
    public Double getPrecipMm() {
        return precip_mm;
    }
    public Double getPrecip_in() {
        return precip_in;
    }
    public Integer getHumidity() {
        return humidity;
    }
    public Integer getCloud() {
        return cloud;
    }
    public Double getFeelslike_c() {
        return feelslike_c;
    }
    public Double getFeelslikeF() {
        return feelslike_f;
    }
    public Double getVis_km() {
        return vis_km;
    }
    public Double getVis_miles() {
        return vis_miles;
    }
    public Double getUv() {
        return uv;
    }
    public Double getGust_mph() {
        return gust_mph;
    }
    public Double getGust_kph() {
        return gust_kph;
    }
}