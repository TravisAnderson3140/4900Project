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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExploreSceneController {
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
    @FXML
    private ImageView iconCity1;
    @FXML
    private ImageView iconCity2;
    @FXML
    private ImageView iconCity3;
    @FXML
    private ImageView iconCity4;
    public void initialize() throws IOException, InterruptedException {
        this.setExplorePage();
    }
    @FXML
    private void switchToCurrentWeatherScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(WetherApplication.class.getResource("SceneCurrentWeather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void setExplorePage() throws IOException, InterruptedException {
        List<String> listOfKeywords = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ExploreData.txt"));

        String keyword;
        while((keyword = reader.readLine()) != null){
            listOfKeywords.add(keyword);
        }
        reader.close();

        Collections.shuffle(listOfKeywords);
        String[] arrayOfKeywords = listOfKeywords.toArray(new String[0]);

        WeatherData weatherData1 = WeatherAPI.getData(arrayOfKeywords[0]);
        WeatherData weatherData2 = WeatherAPI.getData(arrayOfKeywords[1]);
        WeatherData weatherData3 = WeatherAPI.getData(arrayOfKeywords[2]);
        WeatherData weatherData4 = WeatherAPI.getData(arrayOfKeywords[3]);

        this.labelCity1.setText(weatherData1.getLocation().getName());
        this.labelCity2.setText(weatherData2.getLocation().getName());
        this.labelCity3.setText(weatherData3.getLocation().getName());
        this.labelCity4.setText(weatherData4.getLocation().getName());

        this.labelRegion1.setText(weatherData1.getLocation().getRegion());
        this.labelRegion2.setText(weatherData2.getLocation().getRegion());
        this.labelRegion3.setText(weatherData3.getLocation().getRegion());
        this.labelRegion4.setText(weatherData4.getLocation().getRegion());

        this.labelCity1Temp.setText(weatherData1.getCurrent().getTemp_f().toString() + "°F");
        this.labelCity2Temp.setText(weatherData2.getCurrent().getTemp_f().toString() + "°F");
        this.labelCity3Temp.setText(weatherData3.getCurrent().getTemp_f().toString() + "°F");
        this.labelCity4Temp.setText(weatherData4.getCurrent().getTemp_f().toString() + "°F");

        this.iconCity1.setImage((new Image("http:" + weatherData1.getCurrent().getCondition().getIcon())));
        this.iconCity2.setImage((new Image("http:" + weatherData2.getCurrent().getCondition().getIcon())));
        this.iconCity3.setImage((new Image("http:" + weatherData3.getCurrent().getCondition().getIcon())));
        this.iconCity4.setImage((new Image("http:" + weatherData4.getCurrent().getCondition().getIcon())));
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