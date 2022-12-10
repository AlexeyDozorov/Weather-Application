package com.example.firstfx;

import com.example.firstfx.JDBC.DataBaseHandler;
import com.example.firstfx.JDBC.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FirstController {

    @FXML
    private Button EnterButton;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordFild;

    @FXML
    void initialize() {
        EnterButton.setOnAction(actionEvent -> {
            String loginText = LoginField.getText().trim();
            String passText = PasswordFild.getText().trim();
            if (!loginText.equals("") && !passText.equals("")) {
                loginUser(loginText, passText);
            } else {
                System.out.println("Error");
            }
        });
        LoginButton.setOnAction(event -> {
            LoginButton.getScene().getWindow().hide();
            openNewScene("Registr-view.fxml");
        });
    }

    private void loginUser(String loginText, String passText) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Users users = new Users();
        users.setLogin(loginText);
        users.setPassword(passText);
        ResultSet resSet = dbHandler.getUser(users);
        int counter = 0;
        while (true) {
            try {
                if (!resSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1) {
            EnterButton.getScene().getWindow().hide();
            openNewScene("second-view.fxml");
        } else {
            System.out.println("not success");
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

