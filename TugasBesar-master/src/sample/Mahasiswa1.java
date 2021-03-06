package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Mahasiswa1 implements Initializable {
    public TableView table_mhs;
    public TextField txtidpeserta;
    public TextField txtwaktu;
    public TextField txttempat;
    public Button btnuploadnilai;
    public Button btnuploadlaporan;
    public TextField txt_get;
    public Button btn_get;
    public Button btn_revert;
    public TableColumn kolomidpes;
    public TableColumn kolomnamapes;
    public TableColumn kolomwaktupes;
    public TableColumn kolomtempatpes;
    public TextField txtpeserta;
    @FXML
    private AnchorPane tampilan;
    @FXML
    private Button create_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Button btnBack;

    private static Connection connect;
    public static Connection getConnection()
    {
        if(connect == null)
        {
            try {
                String url  ="jdbc:mysql://localhost/db_mhs";
                String user = "root";
                String pass = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                connect = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                //Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("WARNING: Can not create connection!");
            }
        }
        return connect;

    }

    // implementing update from remote DB to Desktop GUI application
    public ObservableList<Emahasiswa> getMahasiswa(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Emahasiswa> mahasiswa = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM mahasiswa";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Emahasiswa mahasiswa_queried = new Emahasiswa(result_set.getInt("id_peserta"), result_set.getString("nama_peserta"), result_set.getString("waktu"), result_set.getString("tempat"));
                mahasiswa.add(mahasiswa_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return mahasiswa;
    }

    // implementing update from remote DB to Desktop GUI application onyl for Get Button
    public ObservableList<Emahasiswa> getMahasiswaForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Emahasiswa> mahasiswa = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM mahasiswa WHERE id_peserta = " + btn_get.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Emahasiswa mahasiswa_queried = new Emahasiswa(result_set.getInt("id_pkn"), result_set.getString("nama_peserta"), result_set.getString("waktu"), result_set.getString("tempat"));
                mahasiswa.add(mahasiswa_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return mahasiswa;
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushMahasiswaOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<Emahasiswa> mahasiswa = getMahasiswaForGetButton();

        // updating DB into GUI application
        kolomidpes.setCellValueFactory(new PropertyValueFactory<>("id_peserta"));
        kolomnamapes.setCellValueFactory(new PropertyValueFactory<>("nama_peserta"));
        kolomwaktupes.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        kolomtempatpes.setCellValueFactory(new PropertyValueFactory<>("tempat"));

        table_mhs.setItems(mahasiswa);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushMahasiswaOntoTable(){

        // retrieving data from remote DB
        ObservableList<Emahasiswa> mahasiswa = getMahasiswa();

        // updating DB into GUI application
        kolomidpes.setCellValueFactory(new PropertyValueFactory<>("id_peserta"));
        kolomnamapes.setCellValueFactory(new PropertyValueFactory<>("nama_peserta"));
        kolomwaktupes.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        kolomtempatpes.setCellValueFactory(new PropertyValueFactory<>("tempat"));

        table_mhs.setItems(mahasiswa);
    }

    // creating Vehicle object based on user input
    public void createMahasiswa() throws SQLException {

        if(txtidpeserta.getText().equals("") || txtpeserta.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating Vehicle object based on user input
            String sql_query = "INSERT INTO mahasiswa VALUES(" + txtidpeserta.getText() + "," + txtpeserta.getText() + ",'" + txtwaktu.getText() + "','" + txttempat.getText() + "')";
            establishSQLConnection(sql_query);
            pushMahasiswaOntoTable();
        }
    }

    // updating Vehicle object based on ID
    public void updateMahasiswa() throws SQLException {

        if(txtidpeserta.getText().equals("") || txtpeserta.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating Vehicle object based on id
            String sql_query = "UPDATE mahasiswa SET nama_peserta = " + txtpeserta.getText() + ",waktu = '" + txtwaktu.getText() + "', tempat = '" + txttempat.getText() + "' WHERE id_peserta = " + txtidpeserta.getText() + "";
            establishSQLConnection(sql_query);
            pushMahasiswaOntoTable();
        }
    }

    // deleting Vehicle object based on ID
    @FXML
    private void deleteMahasiswa() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(txtidpeserta.getText().equals("") || txtpeserta.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM mahasiswa WHERE id_pkn = " + txtidpeserta.getText() + "";
            establishSQLConnection(sql_query);
            pushMahasiswaOntoTable();
        }
    }

    // getting Vehicle objects based on ID
    public void getMahasiswaByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(txt_get.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding Vehicle entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM mahasiswa WHERE id_pkn = " + txt_get.getText() + "";
            establishSQLConnection(sql_query);
            pushMahasiswaOntoTableForGetButton();
        }
    }

    // using SQL statement to make relevant query to update table accordingly
    // param: sql_query:String
    private void establishSQLConnection(String sql_query) throws SQLException {

        try (Connection connect_object = getConnection()) {

            try (Statement statement = connect_object.createStatement()) {
                statement.executeUpdate(sql_query);
            } catch (Exception e) {
            }
        }
    }

    // event handler for button press
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == create_btn ){
            createMahasiswa();
        }
        else if(actionEvent.getSource() == update_btn){
            updateMahasiswa();
        }

        else if(actionEvent.getSource() == delete_btn){
            deleteMahasiswa();
        }

        else if (actionEvent.getSource() == btn_get){
            getMahasiswaByID();
        }

        else if(actionEvent.getSource() == btn_revert){
            pushMahasiswaOntoTable();
            txt_get.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {

        Emahasiswa vehicle = (Emahasiswa) table_mhs.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        txtidpeserta.setText(String.valueOf(vehicle.getId_peserta()));
        txtpeserta.setText(String.valueOf(vehicle.getNama_peserta()));
        txtwaktu.setText(vehicle.getWaktu());
        txttempat.setText(vehicle.getTempat());
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) { pushMahasiswaOntoTable(); }

    public void uploadNilaiPerusahaan(){
        try{
            Desktop.getDesktop().browse(new URL("https://drive.google.com/drive/folders/1OgqEtlIOtebALlbmSf9mNPTGATV2yQ0S?usp=sharing").toURI());
        }
        catch (Exception e){
        }
    }

    public void uploadLaporan(){
        try{
            Desktop.getDesktop().browse(new URL("https://drive.google.com/drive/folders/12TOp_iwrVL6R5XnUjqriPT3_O6JrRvCF?usp=sharing").toURI());
        }
        catch (Exception e){
        }
    }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

}
