package com.example.v1;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SettingsReader {
    private final Gson gson = new Gson();
    private final String settingsFilePath = "data/settings.json";

    public Settings readSettings() throws IOException {

        try (FileReader reader = new FileReader(settingsFilePath)) {
            Settings settings = gson.fromJson(reader, Settings.class);
            return settings;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
