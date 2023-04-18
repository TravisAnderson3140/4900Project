package com.example.v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
    @FXML
    private Button buttonDelete1;
    @FXML
    private Button buttonDelete2;
    @FXML
    private Button buttonDelete3;
    @FXML
    private Button buttonDelete4;
    @FXML
    private ImageView iconCity1;
    @FXML
    private ImageView iconCity2;
    @FXML
    private ImageView iconCity3;
    @FXML
    private ImageView iconCity4;
    private Settings settings;
    private final SettingsReader settingsReader = new SettingsReader();
    
    public void initialize() throws IOException, InterruptedException {
        this.settings = settingsReader.readSettings();
        this.setFavoritesPage();
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
    private void setFavoritesPage() throws IOException {
        File file = new File("data/favorites.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<CompletableFuture<WeatherData>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String keyword = reader.readLine();
            if (keyword != null) {
                CompletableFuture<WeatherData> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        return WeatherAPI.getData(keyword);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }, executor);
                futures.add(future);
            }
        }

        List<WeatherData> weatherDataList = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        executor.shutdown();
        reader.close();

        if (weatherDataList.size() >=1 && weatherDataList.get(0) != null) {
            this.labelCity1.setText(weatherDataList.get(0).getLocation().getName());
            this.labelRegion1.setText(weatherDataList.get(0).getLocation().getRegion());
            if (this.settings.getTempUnit().equals("Fahrenheit")) {
                this.labelCity1Temp.setText(weatherDataList.get(0).getCurrent().getTemp_f().toString() + "°F");
            } else {
                this.labelCity1Temp.setText(weatherDataList.get(0).getCurrent().getTemp_c().toString() + "°C");
            }
            this.iconCity1.setImage(new Image("http:" + weatherDataList.get(0).getCurrent().getCondition().getIcon()));
            
            buttonDelete1.setOnAction(event -> {
                try {
                    String keyword = weatherDataList.get(0).getLocation().getName().replaceAll(" ", "_") + "_" +
                            weatherDataList.get(0).getLocation().getRegion().replaceAll(" ", "_");

                    removeKeywordFromFavoritesFile(keyword);
                    this.labelCity1.setText("");
                    this.labelRegion1.setText("");
                    this.labelCity1Temp.setText("");
                    this.iconCity1.setImage(null);
                    this.buttonDelete1.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            this.buttonDelete1.setVisible(false);
        }

        if (weatherDataList.size() >=2 && weatherDataList.get(1) != null) {
            this.labelCity2.setText(weatherDataList.get(1).getLocation().getName());
            this.labelRegion2.setText(weatherDataList.get(1).getLocation().getRegion());
            if (this.settings.getTempUnit().equals("Fahrenheit")) {
                this.labelCity2Temp.setText(weatherDataList.get(1).getCurrent().getTemp_f().toString() + "°F");
            } else {
                this.labelCity2Temp.setText(weatherDataList.get(1).getCurrent().getTemp_c().toString() + "°C");
            }
            this.iconCity2.setImage(new Image("http:" + weatherDataList.get(1).getCurrent().getCondition().getIcon()));

            buttonDelete2.setOnAction(event -> {
                try {
                    String keyword = weatherDataList.get(1).getLocation().getName().replaceAll(" ", "_") + "_" +
                            weatherDataList.get(1).getLocation().getRegion().replaceAll(" ", "_");

                    removeKeywordFromFavoritesFile(keyword);
                    this.labelCity2.setText("");
                    this.labelRegion2.setText("");
                    this.labelCity2Temp.setText("");
                    this.iconCity2.setImage(null);
                    this.buttonDelete2.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            this.buttonDelete2.setVisible(false);
        }

        if (weatherDataList.size() >=3 && weatherDataList.get(2) != null) {
            this.labelCity3.setText(weatherDataList.get(2).getLocation().getName());
            this.labelRegion3.setText(weatherDataList.get(2).getLocation().getRegion());
            if (this.settings.getTempUnit().equals("Fahrenheit")) {
                this.labelCity3Temp.setText(weatherDataList.get(2).getCurrent().getTemp_f().toString() + "°F");
            } else {
                this.labelCity3Temp.setText(weatherDataList.get(2).getCurrent().getTemp_c().toString() + "°C");
            }            this.iconCity3.setImage(new Image("http:" + weatherDataList.get(2).getCurrent().getCondition().getIcon()));

            buttonDelete3.setOnAction(event -> {
                try {
                    String keyword = weatherDataList.get(2).getLocation().getName().replaceAll(" ", "_") + "_" +
                            weatherDataList.get(2).getLocation().getRegion().replaceAll(" ", "_");

                    removeKeywordFromFavoritesFile(keyword);
                    this.labelCity3.setText("");
                    this.labelRegion3.setText("");
                    this.labelCity3Temp.setText("");
                    this.iconCity3.setImage(null);
                    this.buttonDelete3.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            this.buttonDelete3.setVisible(false);
        }

        if (weatherDataList.size() >=4 && weatherDataList.get(3) != null) {
            this.labelCity4.setText(weatherDataList.get(3).getLocation().getName());
            this.labelRegion4.setText(weatherDataList.get(3).getLocation().getRegion());
            if (this.settings.getTempUnit().equals("Fahrenheit")) {
                this.labelCity4Temp.setText(weatherDataList.get(3).getCurrent().getTemp_f().toString() + "°F");
            } else {
                this.labelCity4Temp.setText(weatherDataList.get(3).getCurrent().getTemp_c().toString() + "°C");
            }            this.iconCity4.setImage(new Image("http:" + weatherDataList.get(3).getCurrent().getCondition().getIcon()));


            buttonDelete4.setOnAction(event -> {
                try {
                    String keyword = weatherDataList.get(3).getLocation().getName().replaceAll(" ", "_") + "_" +
                            weatherDataList.get(3).getLocation().getRegion().replaceAll(" ", "_");

                    removeKeywordFromFavoritesFile(keyword);
                    this.labelCity4.setText("");
                    this.labelRegion4.setText("");
                    this.labelCity4Temp.setText("");
                    this.iconCity4.setImage(null);
                    this.buttonDelete4.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            this.buttonDelete4.setVisible(false);
        }
    }
    private void removeKeywordFromFavoritesFile(String keywordToRemove) throws IOException {
        try {
            File inFile = new File("data/favorites.txt");
            if (!inFile.isFile()) {
                System.out.println("File not found.");
                return;
            }

            // Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader("data/favorites.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line;
            // Read each line from the file and compare it with the line to remove.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(keywordToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            // Delete the original file and rename the new file to the original filename.
            if (!inFile.delete()) {
                System.out.println("Could not delete file.");
                return;
            }
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file.");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
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
        scene.getStylesheets().add("weather.css");
        stage.setScene(scene);
        stage.show();
    }
}