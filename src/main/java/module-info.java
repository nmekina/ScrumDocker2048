module com.example.scrumdocker2048 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scrumdocker2048 to javafx.fxml;
    exports com.example.scrumdocker2048;
    exports com.example.scrumdocker2048.Controller;
    opens com.example.scrumdocker2048.Controller to javafx.fxml;
}