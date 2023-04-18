package com.example.v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class WetherApplication extends Application {

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneCurrentWeather.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        launch();
    }
}