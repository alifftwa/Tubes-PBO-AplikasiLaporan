package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

public class Mahasiswa {
    @FXML
    private AnchorPane tampilan;

    @FXML
    private Label txt1;

    @FXML
    private TextField txtfield;

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TextField txtfield3;

    @FXML
    private TextField txtfield4;

    @FXML
    private Label txt2;

    @FXML
    private Button btn;

    @FXML
    private Button btnUpload;

    @FXML
    private Button btnBack;

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

    public void btnUpload(){
        try{
            Desktop.getDesktop().browse(new URL("https://drive.google.com/drive/folders/1C00YQoRuzGXP0hVPE1YIrs9CsMhQYcWe?usp=sharing").toURI());
        }
        catch (Exception e){
        }
    }

    public void btnDownload(){
        try{
            Desktop.getDesktop().browse(new URL("https://drive.google.com/drive/folders/1C00YQoRuzGXP0hVPE1YIrs9CsMhQYcWe?usp=sharing").toURI());
        }
        catch (Exception e){
        }
    }
}
