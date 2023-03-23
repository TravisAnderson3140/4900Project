package com.example.v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class FavoritesSceneController {
    @FXML
    private Label labelCity1Temp;
    @FXML
    private Label labelCity1;
    @FXML
    private Label labelRegion1;
    @FXML
    private Label labelCity2Temp;
    @FXML
    private Label labelCity2;
    @FXML
    private Label labelRegion2;
    @FXML
    private Label labelCity3;
    @FXML
    private Label labelRegion3;
    @FXML
    private Label labelCity4;
    @FXML
    private Label labelRegion4;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label labelCity3Temp;
    @FXML
    private Label labelCity4Temp;
    public void initialize() throws IOException, InterruptedException {
        this.setFavoritesPage();
    }
    @FXML
    private void switchToCurrentWeatherScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(WetherApplication.class.getResource("SceneCurrentWeather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void setFavoritesPage() throws IOException, InterruptedException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("favorites.txt").getFile());
        BufferedReader reader = new BufferedReader(new FileReader(file));

        WeatherData [] weatherDataArray = new WeatherData[4];
        String keyword;

        for(int i = 0; i < 4; i++){
            keyword = reader.readLine();
            if(keyword != null){
                weatherDataArray[i] = WeatherAPI.getData(keyword);
            }
        }
        reader.close();

        if (weatherDataArray[0] != null) {
            this.labelCity1.setText(weatherDataArray[0].getLocation().getName());
            this.labelRegion1.setText(weatherDataArray[0].getLocation().getRegion());
            this.labelCity1Temp.setText(weatherDataArray[0].getCurrent().getTemp_f().toString() + "°F");
        }

        if (weatherDataArray[1] != null) {
            this.labelCity2.setText(weatherDataArray[1].getLocation().getName());
            this.labelRegion2.setText(weatherDataArray[1].getLocation().getRegion());
            this.labelCity2Temp.setText(weatherDataArray[1].getCurrent().getTemp_f().toString() + "°F");
        }

        if (weatherDataArray[2] != null) {
            this.labelCity3.setText(weatherDataArray[2].getLocation().getName());
            this.labelRegion3.setText(weatherDataArray[2].getLocation().getRegion());
            this.labelCity3Temp.setText(weatherDataArray[2].getCurrent().getTemp_f().toString() + "°F");
        }

        if (weatherDataArray[3] != null) {
            this.labelCity4.setText(weatherDataArray[3].getLocation().getName());
            this.labelRegion4.setText(weatherDataArray[3].getLocation().getRegion());
            this.labelCity4Temp.setText(weatherDataArray[3].getCurrent().getTemp_f().toString() + "°F");
        }
    }
    @FXML
    private void expandWeather(MouseEvent event) throws IOException, InterruptedException {
        VBox vbox = (VBox) event.getSource();
        Label label = (Label) vbox.getChildren().get(1);
        Label label2 = (Label) vbox.getChildren().get(2);

        String city = label.getText();
        String region = label2.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneCurrentWeather.fxml"));
        root = loader.load();

        CurrentWeatherSceneController currentWeatherSceneController = loader.getController();
        currentWeatherSceneController.getCurrentData(city + " " + region);
        currentWeatherSceneController.getForecastData(city + " " + region);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
