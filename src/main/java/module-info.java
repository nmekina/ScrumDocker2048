module com.example.scrumdocker2048 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scrumdocker2048 to javafx.fxml;
    exports com.example.scrumdocker2048;
}