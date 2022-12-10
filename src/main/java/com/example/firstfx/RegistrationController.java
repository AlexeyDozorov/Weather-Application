package com.example.firstfx;

import com.example.firstfx.Constants.Const;
import com.example.firstfx.JDBC.DataBaseHandler;
import com.example.firstfx.JDBC.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

public class RegistrationController {

    @FXML
    private RadioButton CheckMenButton;

    @FXML
    private RadioButton CheckWoomanButton;

    @FXML
    private TextField Countryfield;

    @FXML
    private TextField Loginfield;

    @FXML
    private TextField NameField;

    @FXML
    private PasswordField Passwordfield;

    @FXML
    private Button RegistrationButton;

    @FXML
    private TextField Surnamefield;

    @FXML
    void initialize() {
        RegistrationButton.setOnAction(actionEvent -> {
            SighnUpNewUser();
        });

    }

    private void SighnUpNewUser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String firstname = NameField.getText();
        String lastname = Surnamefield.getText();
        String login = Loginfield.getText();
        String password = Passwordfield.getText();
        String location = Countryfield.getText();
        String gender = "Man";
        if (CheckMenButton.isSelected()){
            gender = "Man";
        } else gender = "Woman";
        if (!Objects.equals(firstname, "") && !Objects.equals(lastname, "") &&
                !Objects.equals(login, "") && !Objects.equals(password, "") &&
                !Objects.equals(location, "") && (CheckMenButton.isSelected() || CheckWoomanButton.isSelected())){
            Users users = new Users(firstname,lastname,login,password,location,gender);
            RegistrationButton.setOnAction(event -> {
                dataBaseHandler.sighnUpUser(users);
                RegistrationButton.getScene().getWindow().hide();
                openNewScene("second-view.fxml");
            });
        }

    }
    public void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
