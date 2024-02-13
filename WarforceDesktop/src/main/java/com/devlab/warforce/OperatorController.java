package com.devlab.warforce;

import com.devlab.warforce.enumeration.Gender;
import com.devlab.warforce.model.*;
import com.devlab.warforce.service.OperatorService;
import com.devlab.warforce.service.PlatoonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class OperatorController implements Initializable {

    @FXML
    private TableColumn<Operator, Integer> idCol;

    @FXML
    private TableColumn<Operator, String> firstNameCol;

    @FXML
    private TableColumn<Operator, String> lastNameCol;

    @FXML
    private TableColumn<Operator, String> dobCol;

    @FXML
    private TableColumn<Operator, String> genderCol;

    @FXML
    private TableView<Operator> table;

    @FXML
    private Label platoonIdLbl;

    @FXML
    private Label platoonNameLbl;

    @FXML
    private Label platoonCommanderLbl;

    @FXML
    private Label platoonLocationLbl;

    @FXML
    private TextField firstNameTf;

    @FXML
    private TextField lastNameTf;

    @FXML
    private DatePicker dobDp;

    @FXML
    private ComboBox<Gender> genderCb;

    @FXML
    private ComboBox<Platoon> platoonCb;

    @FXML
    private TextField firstNameTfUpdate;

    @FXML
    private TextField lastNameTfUpdate;

    @FXML
    private DatePicker dobDpUpdate;

    @FXML
    private ComboBox<Gender> genderCbUpdate;

    @FXML
    private ComboBox<Platoon> platoonCbUpdate;

    @FXML
    private Label operatorIdLbl;

    private ObservableList<Operator> operators;

    private OperatorService operatorService;

    private PlatoonService platoonService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            operatorService = new OperatorService();
            platoonService = new PlatoonService();

            getOperators();
            initializeTable();
            initializeCreateAndUpdateOperator();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeCreateAndUpdateOperator() throws URISyntaxException, IOException, InterruptedException {
        List<Platoon> platoonsDb = platoonService.getPlatoons();

        ObservableList<Platoon> platoons = FXCollections.observableArrayList(platoonsDb);

        platoonCb.setItems(platoons);
        platoonCbUpdate.setItems(platoons);

        genderCb.getItems().setAll(Gender.values());
        genderCbUpdate.getItems().setAll(Gender.values());
    }

    private void getOperators() throws URISyntaxException, IOException, InterruptedException {
        List<Operator> operatorsDb = operatorService.getOperators();

        operators = FXCollections.observableArrayList(operatorsDb);
    }

    private void initializeTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                initializeOperatorPlatoon();

                initializeUpdate(table.getSelectionModel().getSelectedItem());
            }
        });

        table.setItems(operators);
    }

    private void initializeOperatorPlatoon() {
        OperatorPlatoon platoon = table.getSelectionModel().getSelectedItem().getPlatoon();
        platoonIdLbl.setText(platoon.getId().toString());
        platoonNameLbl.setText(platoon.getName());
        platoonCommanderLbl.setText(platoon.getCommander());
        platoonLocationLbl.setText(platoon.getLocation());
    }

    @FXML
    private void createOperator() throws URISyntaxException, IOException, InterruptedException {
        if (validateCreateForm()) {
            CreateOperator createOperator = new CreateOperator(
                    firstNameTf.getText(),
                    lastNameTf.getText(),
                    dobDp.getValue().toString(),
                    genderCb.getValue(),
                    platoonCb.getValue().getId());

            Operator operator = operatorService.createOperator(createOperator);

            operators.add(operator);

            clearForm();
        }
    }

    private boolean validateCreateForm() {
        return !firstNameTf.getText().isEmpty() &&
                !lastNameTf.getText().isEmpty() &&
                dobDp.getValue() != null &&
                !genderCb.getSelectionModel().isEmpty() &&
                !platoonCb.getSelectionModel().isEmpty();
    }

    @FXML
    private void clearForm() {
        firstNameTf.clear();
        lastNameTf.clear();
        dobDp.getEditor().clear();
        genderCb.setValue(null);
        platoonCb.setValue(null);
    }

    @FXML
    private void updateOperator() throws URISyntaxException, IOException, InterruptedException {
        if (validateUpdateForm()) {
            UpdateOperator updateOperator = new UpdateOperator(
                    firstNameTfUpdate.getText(),
                    lastNameTfUpdate.getText(),
                    dobDpUpdate.getValue().toString(),
                    genderCbUpdate.getValue(),
                    platoonCbUpdate.getValue().getId());

            Operator operator = operatorService.updateOperator(operatorIdLbl.getText(), updateOperator);

            operators.removeIf(x -> x.getId() == Integer.parseInt(operatorIdLbl.getText()));
            operators.add(operator);

            clearUpdateForm();
        }
    }

    private boolean validateUpdateForm() {
        return !firstNameTfUpdate.getText().isEmpty() &&
                !lastNameTfUpdate.getText().isEmpty() &&
                dobDpUpdate.getValue() != null &&
                !genderCbUpdate.getSelectionModel().isEmpty() &&
                !platoonCbUpdate.getSelectionModel().isEmpty() &&
                !operatorIdLbl.getText().isEmpty();
    }

    @FXML
    private void clearUpdateForm() {
        firstNameTfUpdate.clear();
        lastNameTfUpdate.clear();
        dobDpUpdate.getEditor().clear();
        genderCbUpdate.setValue(null);
        platoonCbUpdate.setValue(null);
        operatorIdLbl.setText("");
    }

    private void initializeUpdate(Operator operator) {
        firstNameTfUpdate.setText(operator.getFirstName());
        lastNameTfUpdate.setText(operator.getLastName());
        genderCbUpdate.setValue(operator.getGender());
        operatorIdLbl.setText(operator.getId().toString());

        Platoon platoon = platoonCbUpdate.getItems().stream().filter(x -> x.getId().equals(operator.getPlatoon().getId())).findFirst().orElse(null);
        platoonCbUpdate.setValue(platoon);

        LocalDate dob = LocalDate.parse(operator.getDateOfBirth().substring(0, operator.getDateOfBirth().indexOf('T')));
        dobDpUpdate.setValue(dob);
    }

    @FXML
    private void deleteOperator() throws URISyntaxException, IOException, InterruptedException {
        if (!operatorIdLbl.getText().isEmpty()) {
            operatorService.deleteOperator(operatorIdLbl.getText());

            operators.removeIf(x -> x.getId() == Integer.parseInt(operatorIdLbl.getText()));

            clearUpdateForm();
        }
    }
}
