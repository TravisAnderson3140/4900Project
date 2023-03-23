module com.example.v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.v1 to javafx.fxml;
    exports com.example.v1;
}