package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Mahasiswa1 {
    @FXML
    private AnchorPane tampilan;

    @FXML
    private TableView<?> main_table;

    @FXML
    private TableColumn<?, ?> id_column;

    @FXML
    private TableColumn<?, ?> pengawas_column;

    @FXML
    private TableColumn<?, ?> ruangan_column;

    @FXML
    private TextField txtid_jadwal_kuliah;

    @FXML
    private TextField txt_nama_pengawas;

    @FXML
    private TextField txtruangan;

    @FXML
    private Button create_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Button btnBack;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;



    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }



}
