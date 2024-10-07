module com.example.findingprimenumbergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.findingprimenumbergui to javafx.fxml;
    exports com.example.findingprimenumbergui;
}