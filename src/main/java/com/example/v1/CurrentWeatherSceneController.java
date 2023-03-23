package com.example.v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class CurrentWeatherSceneController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label labelF;
    @FXML
    private Label labelCity;
    @FXML
    private Label labelRegion;
    @FXML
    private TextField searchKeyword;
    @FXML
    private Label labelConditon;
    @FXML
    private Label labelFeelsLikeF;
    @FXML
    private Label labelWindMPH;
    @FXML
    private Label labelWindDir;
    @FXML
    private Label labelHumidity;
    @FXML
    private Label labelVisibility;
    @FXML
    private Label labelLastUpdated;
    @FXML
    private Label labelDay1;
    @FXML
    private Label labelDay2;
    @FXML
    private Label labelDay3;
    @FXML
    private ImageView imageviewIcon;
    @FXML
    private Label labelHighDay1;
    @FXML
    private  Label labelHighDay2;
    @FXML
    private Label labelHighDay3;
    @FXML
    private Label labelLowDay1;
    @FXML
    private Label labelLowDay2;
    @FXML
    private Label labelLowDay3;
    @FXML
    private Label labelAvgTempFDay1;
    @FXML
    private Label labelAvgTempFDay2;
    @FXML
    private Label labelAvgTempFDay3;
    @FXML
    private Label labelAvgHumidityDay1;
    @FXML
    private Label labelAvgHumidityDay2;
    @FXML
    private Label labelAvgHumidityDay3;
    @FXML
    private Label labelAvgVisMilesDay1;
    @FXML
    private Label labelAvgVisMilesDay2;
    @FXML
    private Label labelAvgVisMilesDay3;
    @FXML
    private Label labelChanceofRainDay1;
    @FXML
    private Label labelChanceofRainDay2;
    @FXML
    private Label labelChanceofRainDay3;
    @FXML
    private ImageView imageViewIconDay1;
    @FXML
    private ImageView imageViewIconDay2;
    @FXML
    private ImageView imageViewIconDay3;
    @FXML
    private ImageView imageViewBackground;
    public void initialize() throws IOException, InterruptedException {
        this.getCurrentData("29440");
        this.getForecastData("29440");
    }
    public void getCurrentData(String keyword) throws IOException, InterruptedException {
        keyword = keyword.replaceAll(" ", "_");
        WeatherData wd = WeatherAPI.getData(keyword);
        this.setCurrentF(wd);
    }

    public void getForecastData(String keyword) throws IOException, InterruptedException {
        keyword = keyword.replaceAll(" ", "_");
        WeatherForecast wf = WeatherAPI.getForecast(keyword);
        this.setForecast(wf);
    }

    public void setForecast(WeatherForecast forecast){

        this.labelDay1.setText(getDayOfWeek(forecast.getForecast().getForecastday().get(0).getDate()));
        this.labelDay2.setText(getDayOfWeek(forecast.getForecast().getForecastday().get(1).getDate()));
        this.labelDay3.setText(getDayOfWeek(forecast.getForecast().getForecastday().get(2).getDate()));

        this.imageViewIconDay1.setImage(new Image("http:" + forecast.getForecast().getForecastday().get(0).getHour().get(12).getCondition().getIcon()));
        this.imageViewIconDay2.setImage(new Image("http:" + forecast.getForecast().getForecastday().get(1).getHour().get(12).getCondition().getIcon()));
        this.imageViewIconDay3.setImage(new Image("http:" + forecast.getForecast().getForecastday().get(2).getHour().get(12).getCondition().getIcon()));

        this.labelHighDay1.setText("High: " + forecast.getForecast().getForecastday().get(0).getDay().getMaxtemp_f().toString() + "°F");
        this.labelHighDay2.setText("High: " + forecast.getForecast().getForecastday().get(1).getDay().getMaxtemp_f().toString() + "°F");
        this.labelHighDay3.setText("High: " + forecast.getForecast().getForecastday().get(2).getDay().getMaxtemp_f().toString() + "°F");

        this.labelLowDay1.setText("Low: " + forecast.getForecast().getForecastday().get(0).getDay().getMintemp_f().toString() + "°F");
        this.labelLowDay2.setText("Low: " + forecast.getForecast().getForecastday().get(1).getDay().getMintemp_f().toString() + "°F");
        this.labelLowDay3.setText("Low: " + forecast.getForecast().getForecastday().get(2).getDay().getMintemp_f().toString() + "°F");

        this.labelAvgTempFDay1.setText("Avg: " + forecast.getForecast().getForecastday().get(0).getDay().getAvgtemp_f().toString() + "°F");
        this.labelAvgTempFDay2.setText("Avg: " + forecast.getForecast().getForecastday().get(1).getDay().getAvgtemp_f().toString() + "°F");
        this.labelAvgTempFDay3.setText("Avg: " + forecast.getForecast().getForecastday().get(2).getDay().getAvgtemp_f().toString() + "°F");

        this.labelAvgHumidityDay1.setText("Humidity (Avg): " + forecast.getForecast().getForecastday().get(0).getDay().getAvghumidity().toString() + "%");
        this.labelAvgHumidityDay2.setText("Humidity (Avg): " + forecast.getForecast().getForecastday().get(1).getDay().getAvghumidity().toString() + "%");
        this.labelAvgHumidityDay3.setText("Humidity (Avg): " + forecast.getForecast().getForecastday().get(2).getDay().getAvghumidity().toString() + "%");

        this.labelAvgVisMilesDay1.setText("Visibility (Avg): " + forecast.getForecast().getForecastday().get(0).getDay().getAvgvis_miles().toString() + " Mi.");
        this.labelAvgVisMilesDay2.setText("Visibility (Avg): " + forecast.getForecast().getForecastday().get(1).getDay().getAvgvis_miles().toString() + " Mi.");
        this.labelAvgVisMilesDay3.setText("Visibility (Avg): " + forecast.getForecast().getForecastday().get(2).getDay().getAvgvis_miles().toString() + " Mi.");

        this.labelChanceofRainDay1.setText("Chance of Rain: " + forecast.getForecast().getForecastday().get(0).getDay().getDaily_chance_of_rain().toString() + "%");
        this.labelChanceofRainDay2.setText("Chance of Rain: " + forecast.getForecast().getForecastday().get(1).getDay().getDaily_chance_of_rain().toString() + "%");
        this.labelChanceofRainDay3.setText("Chance of Rain: " + forecast.getForecast().getForecastday().get(2).getDay().getDaily_chance_of_rain().toString() + "%");
    }
    private void setCurrentF(WeatherData wd) {
        setBackground(wd);
        this.labelF.setText(wd.getCurrent().getTemp_f().toString() + "°F");
        this.labelCity.setText(wd.getLocation().getName());
        this.labelRegion.setText(wd.getLocation().getRegion());
        this.labelConditon.setText(wd.getCurrent().getCondition().getText());
        this.labelFeelsLikeF.setText("Feels Like: " + (wd.getCurrent().getFeelslikeF().toString()) + "°F");
        this.labelWindMPH.setText("Wind: " + wd.getCurrent().getWind_mph().toString() + " MPH");
        this.labelWindDir.setText("Wind Dir: " + wd.getCurrent().getWind_dir());
        this.labelHumidity.setText("Humidity: " + wd.getCurrent().getHumidity().toString() + "%");
        this.labelVisibility.setText("Visibility: " + wd.getCurrent().getVis_miles().toString() + " Mi.");
        this.labelLastUpdated.setText("Last Updated: " + wd.getCurrent().getLast_updated());
        this.imageviewIcon.setImage(new Image("http:" + wd.getCurrent().getCondition().getIcon()));
    }
    public String getDayOfWeek(String date){
        int year, month, dayOfMonth;
        String[] dateCode = date.split("-");
        year = Integer.parseInt(dateCode[0]);
        month = Integer.parseInt(dateCode[1]);
        dayOfMonth = Integer.parseInt(dateCode[2]);

        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }
    @FXML
    private void searchAction(ActionEvent event) throws IOException, InterruptedException {
        String keyword = this.searchKeyword.getText();
        this.getCurrentData(keyword);
        this.getForecastData(keyword);
        this.searchKeyword.setText("");
    }
    @FXML
    private void switchToExploreScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneExplore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void switchToFavoritesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneFavorites.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void saveToFavorites(ActionEvent event) throws IOException {
        String keyword = this.labelCity.getText().replaceAll(" ", "_") + "_" +
                this.labelRegion.getText().replaceAll(" ", "_");

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/favorites.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/favorites.txt"));
        String line;
        int lineCount = 0;

        while ((line = reader.readLine()) != null) {
            lineCount++;
            System.out.println("line count: " + lineCount);
            // Check if the keyword already exists in the file
            if (line.equals(keyword)) {
                System.out.println("Location already saved to favorites!");
                reader.close();
                return; // keyword already exists
            }
        }

        if(lineCount >= 4){
            System.out.println("Favorites full. Remove a location before saving a new one.");
            reader.close();
            return;
        }

        reader.close();
        fileWriter.write(keyword + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
    private void setBackground(WeatherData weatherData) {
        ImageView SunnyBackground = new ImageView(new Image("/Sunny.jpg"));
        ImageView CloudyBackground = new ImageView(new Image("/Cloudy.jpg"));
        ImageView OvercastBackground = new ImageView(new Image("/Overcast.jpg"));
        ImageView MistyBackground = new ImageView(new Image("/Misty.jpg"));
        ImageView RainyBackground = new ImageView(new Image("/Rainy.jpg"));
        ImageView SnowyBackground = new ImageView(new Image("/Snowy.jpg"));
        ImageView ThunderBackground = new ImageView(new Image("/Thunder.jpg"));
        ImageView FoggyBackground = new ImageView(new Image("/Foggy.jpg"));

        switch (weatherData.getCurrent().getCondition().getCode()) {
            case 1000:
                this.imageViewBackground.setImage(SunnyBackground.getImage());
                break;
            case 1003, 1006:
                this.imageViewBackground.setImage(CloudyBackground.getImage());
                break;
            case 1009:
                this.imageViewBackground.setImage(OvercastBackground.getImage());
                break;
            case 1030:
                this.imageViewBackground.setImage(MistyBackground.getImage());
                break;
            case 1063, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1204, 1207, 1240, 1243, 1246, 1252, 1273, 1276:
                this.imageViewBackground.setImage(RainyBackground.getImage());
                break;
            case 1066, 1069, 1072, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1237, 1255, 1261, 1264, 1279, 1282, 1249:
                this.imageViewBackground.setImage(SnowyBackground.getImage());
                break;
            case 1087:
                this.imageViewBackground.setImage(ThunderBackground.getImage());
                break;
            case 1135, 1147:
                this.imageViewBackground.setImage(FoggyBackground.getImage());
                break;
        }
    }
}
