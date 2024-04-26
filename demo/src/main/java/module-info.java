module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires javafx.web;
    exports com.example.demo.Service;
    exports com.example.demo.entity;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}