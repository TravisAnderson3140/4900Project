package com.example.v1;

public class Location {
    public String name;
    public String region;
    public String country;
    public Double lat;
    public Double lon;
    public String tz_id;
    public Integer local_time_epoch;
    public String local_time;

    public String getName() {
        return name;
    }
    public String getRegion() {
        return region;
    }
    public String getCountry() {
        return country;
    }
    public Double getLat() {
        return lat;
    }
    public Double getLon() {
        return lon;
    }
    public String getTz_id() {
        return tz_id;
    }
    public Integer getLocal_time_epoch() {
        return local_time_epoch;
    }
    public String getLocal_time() {
        return local_time;
    }
}