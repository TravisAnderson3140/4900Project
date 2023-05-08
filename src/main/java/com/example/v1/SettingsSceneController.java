package com.example.v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class SettingsSceneController {
            @FXML
            private ChoiceBox<String> locationChoiceBox;
            @FXML
            private ChoiceBox<String> tempUnitChoiceBox;
            @FXML
            private ChoiceBox<String> distanceChoiceBox;
            @FXML
            private ChoiceBox<String> windSpeedUnitChoiceBox;
            @FXML
            private ChoiceBox<String> showForecastChoiceBox;
            @FXML
            private ChoiceBox<String> rainfallUnitChoiceBox;
            private Stage stage;
            private Scene scene;
            private Parent root;
            @FXML
            private Text errorText;
            @FXML
            private Button buttonSave;
            private Settings settings;
            @FXML
            public void initialize() {
                settings = Settings.loadSettings();
                if (settings == null) {
                    settings = new Settings("80108", "Celsius", "Miles", "MPH", "Yes", "in.");
                }
                locationChoiceBox.setValue(settings.getDefaultLocation());
                tempUnitChoiceBox.setValue(settings.getTempUnit());
                distanceChoiceBox.setValue(settings.getDistanceUnit());
                windSpeedUnitChoiceBox.setValue(settings.getWindSpeedUnit());
                showForecastChoiceBox.setValue(settings.getShowForecast());
                rainfallUnitChoiceBox.setValue(settings.getRainfallUnit());

                ObservableList<String> locationChoices = FXCollections.observableArrayList();

                try (BufferedReader br = new BufferedReader(new FileReader("data/favorites.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        locationChoices.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                locationChoiceBox.setItems(locationChoices);

                ObservableList<String> unitChoices = FXCollections.observableArrayList("Celsius", "Fahrenheit");
                tempUnitChoiceBox.setItems(unitChoices);

                ObservableList<String> distanceChoices = FXCollections.observableArrayList("Miles", "Kilometers");
                distanceChoiceBox.setItems(distanceChoices);

                ObservableList<String> windSpeedUnitChoices = FXCollections.observableArrayList("MPH", "KPH", "m/s");
                windSpeedUnitChoiceBox.setItems(windSpeedUnitChoices);

                ObservableList<String> showForecastChoices = FXCollections.observableArrayList("Yes", "No");
                showForecastChoiceBox.setItems(showForecastChoices);

                ObservableList<String> rainfallUnitChoices = FXCollections.observableArrayList("in.", "mm.");
                rainfallUnitChoiceBox.setItems(rainfallUnitChoices);
            }
            @FXML
            public void saveSettings() {
                settings.setDefaultLocation(locationChoiceBox.getValue());
                settings.setTempUnit(tempUnitChoiceBox.getValue());
                settings.setDistanceUnit(distanceChoiceBox.getValue());
                settings.setWindSpeedUnit(windSpeedUnitChoiceBox.getValue());
                settings.setShowForecast(showForecastChoiceBox.getValue());
                settings.setRainfallUnit(rainfallUnitChoiceBox.getValue());
                settings.saveSettings();
                errorText.setText("Settings saved!");
            }

    @FXML
    private void switchToCurrentWeatherScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(WeatherApplication.class.getResource("SceneCurrentWeather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
}