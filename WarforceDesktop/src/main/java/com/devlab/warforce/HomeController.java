package com.devlab.warforce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    protected void onPlatoonsButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("platoons-view.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Platoons");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void onOperatorsButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("operators-view.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Operators");
        stage.setScene(new Scene(root));
        stage.show();
    }
}