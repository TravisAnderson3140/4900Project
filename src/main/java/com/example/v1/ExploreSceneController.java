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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    private Settings settings;
    private final SettingsReader settingsReader;
    private final WeatherAPI weatherAPI;

    public void initialize() throws IOException, InterruptedException {
        this.settings = settingsReader.readSettings();
        this.setExplorePage();
    }
    public ExploreSceneController() {
        this.weatherAPI = new WeatherAPI();
        this.settingsReader = new SettingsReader();
    }
    @FXML
    private void switchToCurrentWeatherScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(WetherApplication.class.getResource("SceneCurrentWeather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
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

        CompletableFuture<WeatherData> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return WeatherAPI.getData(listOfKeywords.get(0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<WeatherData> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return WeatherAPI.getData(listOfKeywords.get(1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<WeatherData> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                return WeatherAPI.getData(listOfKeywords.get(2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<WeatherData> future4 = CompletableFuture.supplyAsync(() -> {
            try {
                return WeatherAPI.getData(listOfKeywords.get(3));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        WeatherData weatherData1 = null;
        try {
            weatherData1 = future1.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }

        WeatherData weatherData2 = null;
        try {
            weatherData2 = future2.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }

        WeatherData weatherData3 = null;
        try {
            weatherData3 = future3.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }

        WeatherData weatherData4 = null;
        try {
            weatherData4 = future4.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }

        this.labelCity1.setText(weatherData1.getLocation().getName());
        this.labelCity2.setText(weatherData2.getLocation().getName());
        this.labelCity3.setText(weatherData3.getLocation().getName());
        this.labelCity4.setText(weatherData4.getLocation().getName());

        this.labelRegion1.setText(weatherData1.getLocation().getRegion());
        this.labelRegion2.setText(weatherData2.getLocation().getRegion());
        this.labelRegion3.setText(weatherData3.getLocation().getRegion());
        this.labelRegion4.setText(weatherData4.getLocation().getRegion());

        if (this.settings.getTempUnit().equals("Fahrenheit")) {
            this.labelCity1Temp.setText(weatherData1.getCurrent().getTemp_f().toString() + "°F");
            this.labelCity2Temp.setText(weatherData2.getCurrent().getTemp_f().toString() + "°F");
            this.labelCity3Temp.setText(weatherData3.getCurrent().getTemp_f().toString() + "°F");
            this.labelCity4Temp.setText(weatherData4.getCurrent().getTemp_f().toString() + "°F");
        }else{
            this.labelCity1Temp.setText(weatherData1.getCurrent().getTemp_c().toString() + "°C");
            this.labelCity2Temp.setText(weatherData2.getCurrent().getTemp_c().toString() + "°C");
            this.labelCity3Temp.setText(weatherData3.getCurrent().getTemp_c().toString() + "°C");
            this.labelCity4Temp.setText(weatherData4.getCurrent().getTemp_c().toString() + "°C");
        }

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
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
}