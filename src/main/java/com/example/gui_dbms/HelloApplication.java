package com.example.gui_dbms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    OperationWindow window;
    @Override
    public void start(Stage stage) throws IOException {
        window = new OperationWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
