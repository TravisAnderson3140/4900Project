package com.example.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

    public class Settings {
        public String defaultLocation;
        public String tempUnit;
        public String distanceUnit;
        public String windSpeedUnit;
        public String showForecast;
        public String rainfallUnit;
        public Settings(String defaultLocation, String unit, String distanceUnit, String windSpeedUnit, String showForecast, String rainfallUnit) {
            this.defaultLocation = defaultLocation;
            this.tempUnit = unit;
            this.distanceUnit = distanceUnit;
            this.windSpeedUnit = windSpeedUnit;
            this.showForecast = showForecast;
            this.rainfallUnit = rainfallUnit;
        }

        public String getRainfallUnit() {return rainfallUnit;}
        public void setRainfallUnit(String rainfallUnit) {this.rainfallUnit = rainfallUnit;}
        public String getShowForecast() {
            return showForecast;
        }
        public void setShowForecast(String showForecast) {
            this.showForecast = showForecast;
        }
        public String getWindSpeedUnit() {
            return windSpeedUnit;
        }
        public void setWindSpeedUnit(String windSpeedUnit) {
            this.windSpeedUnit = windSpeedUnit;
        }
        public String getDefaultLocation() {
            return defaultLocation;
        }
        public String getTempUnit() {
            return tempUnit;
        }
        public String getDistanceUnit() { return distanceUnit;}
        public void setDistanceUnit(String distanceUnit) { this.distanceUnit = distanceUnit; }
        public void setDefaultLocation(String defaultLocation) {
            this.defaultLocation = defaultLocation;
        }
        public void setTempUnit(String tempUnit) {
            this.tempUnit = tempUnit;
        }




        public static Settings loadSettings() {
            try {
                File file = new File("data/settings.json");
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new FileReader(file));
                Settings settings = gson.fromJson(br, Settings.class);
                return settings;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void saveSettings() {
            try {
                File file = new File("data/settings.json");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(this);
                FileWriter writer = new FileWriter(file);
                writer.write(json);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
