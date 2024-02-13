package com.devlab.warforce;

import com.devlab.warforce.model.CreatePlatoon;
import com.devlab.warforce.model.Platoon;
import com.devlab.warforce.model.PlatoonOperator;
import com.devlab.warforce.model.UpdatePlatoon;
import com.devlab.warforce.service.PlatoonService;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PlatoonController implements Initializable {

    @FXML
    private TableColumn<Platoon, Integer> id;

    @FXML
    private TableColumn<Platoon, String> name;

    @FXML
    private TableColumn<Platoon, String> commander;

    @FXML
    private TableColumn<Platoon, String> location;

    @FXML
    private TableView<Platoon> table;

    @FXML
    private ListView<PlatoonOperator> list;

    @FXML
    private TextField nameTf;

    @FXML
    private TextField commanderTf;

    @FXML
    private TextField locationTf;

    @FXML
    private Label platoonIdLabel;

    @FXML
    private TextField nameTfUpdate;

    @FXML
    private TextField commanderTfUpdate;

    @FXML
    private TextField locationTfUpdate;

    private ObservableList<Platoon> platoons;

    private PlatoonService platoonService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            platoonService = new PlatoonService();

            getPlatoons();
            initializeTable();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void getPlatoons() throws URISyntaxException, IOException, InterruptedException {
        List<Platoon> platoonsDb = platoonService.getPlatoons();

        platoons = FXCollections.observableArrayList(platoonsDb);
    }

    private void initializeTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        commander.setCellValueFactory(new PropertyValueFactory<>("commander"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));

        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                ObservableList<PlatoonOperator> operators = FXCollections.observableArrayList(table.getSelectionModel().getSelectedItem().getOperators());
                list.getItems().clear();
                list.setItems(operators);

                initializeUpdate(table.getSelectionModel().getSelectedItem());
            }
        });

        table.setItems(platoons);
    }

    private void initializeUpdate(Platoon platoon) {
        nameTfUpdate.setText(platoon.getName());
        commanderTfUpdate.setText(platoon.getCommander());
        locationTfUpdate.setText(platoon.getLocation());
        platoonIdLabel.setText(platoon.getId().toString());
    }

    @FXML
    private void createPlatoon() throws URISyntaxException, IOException, InterruptedException {
        if (validateCreateForm()) {
            CreatePlatoon createPlatoon = new CreatePlatoon(nameTf.getText(), commanderTf.getText(), locationTf.getText());

            Platoon platoon = platoonService.createPlatoon(createPlatoon);

            platoons.add(platoon);

            clearForm();
        }
    }

    private Boolean validateCreateForm() {
        return !nameTf.getText().isEmpty() && !commanderTf.getText().isEmpty() && !locationTf.getText().isEmpty();
    }

    private Boolean validateUpdateForm() {
        return !nameTfUpdate.getText().isEmpty() && !commanderTfUpdate.getText().isEmpty() && !locationTfUpdate.getText().isEmpty() && !platoonIdLabel.getText().isEmpty();
    }

    @FXML
    private void clearForm() {
        nameTf.clear();
        commanderTf.clear();
        locationTf.clear();
    }

    @FXML
    private void updatePlatoon() throws URISyntaxException, IOException, InterruptedException {
        if (validateUpdateForm()) {
            UpdatePlatoon updatePlatoon = new UpdatePlatoon(nameTfUpdate.getText(), commanderTfUpdate.getText(), locationTfUpdate.getText());

            Platoon platoon = platoonService.updatePlatoon(platoonIdLabel.getText(), updatePlatoon);

            platoons.removeIf(x -> x.getId() == Integer.parseInt(platoonIdLabel.getText()));
            platoons.add(platoon);

            clearUpdateForm();
        }
    }

    @FXML
    private void clearUpdateForm() {
        nameTfUpdate.clear();
        commanderTfUpdate.clear();
        locationTfUpdate.clear();
        platoonIdLabel.setText("");
    }

    @FXML
    private void deletePlatoon() throws URISyntaxException, IOException, InterruptedException {
        if (!platoonIdLabel.getText().isEmpty()) {
            platoonService.deletePlatoon(platoonIdLabel.getText());

            platoons.removeIf(x -> x.getId() == Integer.parseInt(platoonIdLabel.getText()));

            clearUpdateForm();
        }
    }
}
