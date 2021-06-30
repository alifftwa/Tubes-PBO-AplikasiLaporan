package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login {
    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private Button btnlogin;

    public void Masuklogin() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnlogin.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

}
