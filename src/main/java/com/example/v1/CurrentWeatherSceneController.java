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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
    private Label labelCondition;
    @FXML
    private Label labelFeelsLike;
    @FXML
    private Label labelWindSpeed;
    @FXML
    private Label labelWindDir;
    @FXML
    private Label labelHumidity;
    @FXML
    private Label labelVisibility;
    @FXML
    private Label labelUV;
    @FXML
    private Label labelPrecip;
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
    private Label labelAvgTempDay1;
    @FXML
    private Label labelAvgTempDay2;
    @FXML
    private Label labelAvgTempDay3;
    @FXML
    private Label labelAvgHumidityDay1;
    @FXML
    private Label labelAvgHumidityDay2;
    @FXML
    private Label labelAvgHumidityDay3;
    @FXML
    private Label labelAvgVisDay1;
    @FXML
    private Label labelAvgVisDay2;
    @FXML
    private Label labelAvgVisDay3;
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
    @FXML
    private VBox vBoxForecastDay1;
    @FXML
    private VBox vBoxForecastDay2;
    @FXML
    private VBox vBoxForecastDay3;
    @FXML
    private Label labelSaveStatus;
    @FXML
    private Label labelSunrise;
    @FXML
    private Label labelSunset;
    @FXML
    private Rectangle feelsLikeRectangle;
    @FXML
    private Rectangle humidityRectangle;
    @FXML
    private StackPane feelsLikeSP;
    @FXML
    private StackPane precipSP;
    @FXML
    private StackPane humiditySP;
    @FXML
    private StackPane windSP;
    @FXML
    private StackPane currentSP;
    @FXML
    private VBox vBoxCurrent1;
    @FXML
    private VBox vBoxCurrent2;
    private Settings settings;
    private final SettingsReader settingsReader = new SettingsReader();
    public void initialize() throws IOException, InterruptedException {
        this.settings = settingsReader.readSettings();

        this.getCurrentData(settings.getDefaultLocation());
        if (this.settings.getShowForecast().equals("Yes")) {
            this.getForecastData(settings.getDefaultLocation());
        }else{
            currentSP.setLayoutY(250);
            vBoxCurrent1.setLayoutY(250);
            vBoxCurrent2.setLayoutY(250);
            this.vBoxForecastDay1.managedProperty().bind(vBoxForecastDay1.visibleProperty());
            this.vBoxForecastDay1.setVisible(false);
            this.vBoxForecastDay2.managedProperty().bind(vBoxForecastDay2.visibleProperty());
            this.vBoxForecastDay2.setVisible(false);
            this.vBoxForecastDay3.managedProperty().bind(vBoxForecastDay3.visibleProperty());
            this.vBoxForecastDay3.setVisible(false);
        }
    }
    public void getCurrentData(String keyword) throws IOException, InterruptedException {
        keyword = keyword.replaceAll(" ", "_");
        WeatherData wd = WeatherAPI.getData(keyword);
        this.setCurrentInfo(wd);
    }

    public void getForecastData(String keyword) throws IOException, InterruptedException {
        keyword = keyword.replaceAll(" ", "_");
        WeatherForecast wf = WeatherAPI.getForecast(keyword);
        this.setForecast(wf);
    }

    public void setForecast(WeatherForecast forecast){
        labelSunrise.setText("Sunrise: " + forecast.getForecast().getForecastday().get(0).getAstro().getSunrise());
        labelSunset.setText("Sunset: " + forecast.getForecast().getForecastday().get(0).getAstro().getSunset());

        Label[] dayLabels = {labelDay1, labelDay2, labelDay3 };
        ImageView[] dayIcons = {imageViewIconDay1, imageViewIconDay2, imageViewIconDay3 };
        Label[] highTempLabels = {labelHighDay1, labelHighDay2, labelHighDay3 };
        Label[] lowTempLabels = {labelLowDay1, labelLowDay2, labelLowDay3 };
        Label[] avgTempLabels = {labelAvgTempDay1, labelAvgTempDay2, labelAvgTempDay3 };
        Label[] avgHumidityLabels = {labelAvgHumidityDay1, labelAvgHumidityDay2, labelAvgHumidityDay3 };
        Label[] avgVisMilesLabels = {labelAvgVisDay1, labelAvgVisDay2, labelAvgVisDay3};
        Label[] chanceOfRainLabels = {labelChanceofRainDay1, labelChanceofRainDay2, labelChanceofRainDay3};

        for (int i = 0; i < 3; i++) {
            dayLabels[i].setText(getDayOfWeek(forecast.getForecast().getForecastday().get(i).getDate()));
            dayIcons[i].setImage(new Image("http:" + forecast.getForecast().getForecastday().get(i).getHour().get(12).getCondition().getIcon()));
            avgHumidityLabels[i].setText("Humidity (Avg): " + forecast.getForecast().getForecastday().get(i).getDay().getAvghumidity().toString() + "%");
            chanceOfRainLabels[i].setText("Chance of Rain: " + forecast.getForecast().getForecastday().get(i).getDay().getDaily_chance_of_rain() + "%");
            if (this.settings.getTempUnit().equals("Fahrenheit")) {
                highTempLabels[i].setText("High: " + forecast.getForecast().getForecastday().get(i).getDay().getMaxtemp_f().toString() + "°F");
                lowTempLabels[i].setText("Low: " + forecast.getForecast().getForecastday().get(i).getDay().getMintemp_f().toString() + "°F");
                avgTempLabels[i].setText("Avg: " + forecast.getForecast().getForecastday().get(i).getDay().getAvgtemp_f().toString() + "°F");
            } else {
                highTempLabels[i].setText("High: " + forecast.getForecast().getForecastday().get(i).getDay().getMaxtemp_c().toString() + "°C");
                lowTempLabels[i].setText("Low: " + forecast.getForecast().getForecastday().get(i).getDay().getMintemp_c().toString() + "°C");
                avgTempLabels[i].setText("Avg: " + forecast.getForecast().getForecastday().get(i).getDay().getAvgtemp_c().toString() + "°C");
            }
            if (this.settings.getDistanceUnit().equals("Miles")) {
                avgVisMilesLabels[i].setText("Visibility (Avg): " + forecast.getForecast().getForecastday().get(i).getDay().getAvgvis_miles().toString() + " MI.");
            } else {
                avgVisMilesLabels[i].setText("Visibility (Avg): " + forecast.getForecast().getForecastday().get(i).getDay().getAvgvis_km().toString() + " KM.");
            }
        }
    }
    private void setCurrentInfo(WeatherData wd) {
        setBackground(wd);

        if(settings.getTempUnit().equals("Fahrenheit")) {
            this.labelF.setText(wd.getCurrent().getTemp_f().toString() + "°F");
            this.labelFeelsLike.setText(wd.getCurrent().getFeelslikeF().toString() + "°F");
            this.feelsLikeRectangle.setWidth(wd.getCurrent().getFeelslikeF());
        }else{
            this.labelF.setText(wd.getCurrent().getTemp_c().toString() + "°C");
            this.labelFeelsLike.setText(wd.getCurrent().getFeelslike_c().toString() + "°C");
            this.feelsLikeRectangle.setWidth(wd.getCurrent().getFeelslike_c());
        }

        if(settings.getDistanceUnit().equals("Miles")){
            this.labelVisibility.setText(wd.getCurrent().getVis_miles().toString() + " MI.");
        }else{
            this.labelVisibility.setText(wd.getCurrent().getVis_km().toString() + " KM.");
        }

        if(settings.getRainfallUnit().equals("in.")){
            this.labelPrecip.setText(wd.getCurrent().getPrecip_in().toString() + " in.");
        }else{
            this.labelPrecip.setText(wd.getCurrent().getPrecipMm().toString() + " mm.");
        }

        switch (settings.getWindSpeedUnit()) {
            case "MPH" -> this.labelWindSpeed.setText(wd.getCurrent().getWind_mph().toString() + " MPH");
            case "KPH" -> this.labelWindSpeed.setText(wd.getCurrent().getWindKph().toString() + " KPH");
            case "m/s" -> {
                double windSpeedMps = wd.getCurrent().getWindKph() / 3.6; // convert to m/s
                this.labelWindSpeed.setText(String.format(" %.2f m/s", windSpeedMps));
            }
        }

        this.labelUV.setText(wd.getCurrent().getUv().toString());
        this.labelCity.setText(wd.getLocation().getName() + ",");
        this.labelRegion.setText(wd.getLocation().getRegion());
        this.labelCondition.setText(wd.getCurrent().getCondition().getText());
        this.labelWindDir.setText(wd.getCurrent().getWind_dir());

        this.labelHumidity.setText(wd.getCurrent().getHumidity().toString() + "%");
        this.humidityRectangle.setWidth(wd.getCurrent().getHumidity());

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
    private void searchAction(ActionEvent event) {
        String keyword = this.searchKeyword.getText();

        try {
            this.getCurrentData(keyword);
            this.getForecastData(keyword);
        } catch (Exception e) {
            this.labelLastUpdated.setText("Invalid keyword: " + keyword);
            System.err.println(e.getMessage());
        }

        this.searchKeyword.setText("");
    }
    @FXML
    private void switchToExploreScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneExplore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void switchToFavoritesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneFavorites.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void switchToSettingsScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(WetherApplication.class.getResource("SceneSettings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void saveToFavorites(ActionEvent event) throws IOException {
        String keyword = this.labelCity.getText().replaceAll(" ", "_").replaceAll(",", "") + "_" +
                this.labelRegion.getText().replaceAll(" ", "_");

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data/favorites.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new FileReader("data/favorites.txt"));
        String line;
        int lineCount = 0;

        while ((line = reader.readLine()) != null) {
            lineCount++;
            // Check if the keyword already exists in the file
            if (line.equals(keyword)) {
                this.labelSaveStatus.setText("Location already saved to favorites!");
                reader.close();
                return; // keyword already exists
            }
        }

        if(lineCount >= 4){
            this.labelSaveStatus.setText("Error, Favorites list is full.");
            reader.close();
            return;
        }

        reader.close();
        fileWriter.write(keyword + "\n");
        this.labelSaveStatus.setText("Location saved to Favorites!");
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
