package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import static javafx.fxml.FXMLLoader.load;

public class Controller {
    @FXML
    private Button btn;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Button btnpkn;

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Mahasiswa .fxml"));
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void MasukDosen() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dosen.fxml"));
        Stage window = (Stage) btn2.getScene().getWindow();
        window.setScene(new Scene(root, 786, 550));
    }

    public void MasukAdmin() throws Exception {
        Parent root = load(getClass().getResource("Admin.fxml"));
        Stage window = (Stage) btn3.getScene().getWindow();
        window.setScene(new Scene(root, 786, 550));
    }
    public void MasukMahasiswa1() throws Exception {
        Parent root = load(getClass().getResource("Mahasiswa1.fxml"));
        Stage window = (Stage) btnpkn.getScene().getWindow();
        window.setScene(new Scene(root, 786, 550));
    }



}
