package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Dosen implements Initializable {
    @FXML
    private AnchorPane tampilan;

    @FXML
    private Label text;

    @FXML
    private TextField txtfield;

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TextField txtfield3;

    @FXML
    private Label text1;

    @FXML
    private Label text2;

    @FXML
    private Label text3;

    @FXML
    private Label text4;

    @FXML
    private TableView<Jadwal> table;

    @FXML
    private TableColumn<Jadwal, String> tbl;

    @FXML
    private TableColumn<Jadwal, Integer> tbl1;

    @FXML
    private TableColumn<Jadwal, String> tbl2;

    @FXML
    private TableColumn<Jadwal, Integer> tbl3;

    @FXML
    private Button btn;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btnBack;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btn){
            insertRecord();
        }else if (event.getSource() == btn2){
            deleteButton();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showJadwal();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jadwal","user","");
            return conn;
        }catch (Exception ex){
            System.out.println("error: "+ex.getMessage());
            return null;
        }
    }
    public ObservableList<Jadwal> getJadwalList(){
        ObservableList<Jadwal> jadwals = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM jadwal";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Jadwal jadwal;
            while (rs.next()){
               jadwal = new Jadwal(rs.getString("Nama Pengawas"),rs.getInt("Ruangan"),rs.getString("Tempat"),rs.getInt("Waktu"));
               jadwals.add(jadwal);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jadwals;
    }
    public void showJadwal(){
        ObservableList<Jadwal> list = getJadwalList();

        tbl.setCellValueFactory(new PropertyValueFactory<Jadwal,String>("Nama Pengawas"));
        tbl1.setCellValueFactory(new PropertyValueFactory<Jadwal,Integer>("Ruangan"));
        tbl2.setCellValueFactory(new PropertyValueFactory<Jadwal,String>("Tempat"));
        tbl3.setCellValueFactory(new PropertyValueFactory<Jadwal,Integer>("Waktu"));

        table.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO jadwall VALUES (" + txtfield.getText() + ",'" + txtfield1.getText() + "','" + txtfield2.getText() + "',"
                + txtfield3.getText() + ")";
        executeQuery(query);
        showJadwal();
    }
    private void deleteButton(){
        String query = "DELETE FROM jadwall WHERE Nama Pengawas =" + txtfield.getText() + "";
        executeQuery(query);
        showJadwal();
    }

    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 300, 275));
    }
}
