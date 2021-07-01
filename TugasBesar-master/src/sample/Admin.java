package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Admin implements Initializable {



    @FXML
    private AnchorPane tampilan;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btnBack;
    @FXML
    private Button btndelete;
    public TextField txtwaktu;
    public TableView tabel_utama;
    public TableColumn kolomid;
    public TableColumn kolomanggota;
    public TableColumn kolomwaktu;
    public TableColumn kolomtempat;
    public TextField txtidpkn;
    public TextField txtjumlah;
    public TextField txttempat;
    public Button btncreate;
    public Button btnupdate;
    public TextField txtget;
    public Button btnget;
    public Button btnrevert;
    public Button btnpkn;
    public Button btnujian;

    // establishing initial connection with MySQL server

    private static Connection connect;
    public static Connection getConnection()
    {
        if(connect == null)
        {
            try {
                String url  ="jdbc:mysql://localhost/db_pkn";
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
    public ObservableList<Eadmin> getAdmin(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Eadmin> admin = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM admin";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Eadmin admin_queried = new Eadmin(result_set.getInt("id_pkn"), result_set.getInt("jumlah_anggota"), result_set.getString("waktu"), result_set.getString("tempat"));
                admin.add(admin_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return admin;
    }

    // implementing update from remote DB to Desktop GUI application onyl for Get Button
    public ObservableList<Eadmin> getAdminForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Eadmin> admin = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM admin WHERE id_pkn = " + txtget.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Eadmin admin_queried = new Eadmin(result_set.getInt("id_pkn"), result_set.getInt("jumlah_anggota"), result_set.getString("waktu"), result_set.getString("tempat"));
                admin.add(admin_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return admin;
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushAdminOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<Eadmin> admin = getAdminForGetButton();

        // updating DB into GUI application
        kolomid.setCellValueFactory(new PropertyValueFactory<>("id_pkn"));
        kolomanggota.setCellValueFactory(new PropertyValueFactory<>("jumlah_anggota"));
        kolomwaktu.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        kolomtempat.setCellValueFactory(new PropertyValueFactory<>("Tempat"));

        tabel_utama.setItems(admin);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushAdminOntoTable(){

        // retrieving data from remote DB
        ObservableList<Eadmin> admin = getAdmin();

        // updating DB into GUI application
        kolomid.setCellValueFactory(new PropertyValueFactory<>("id_pkn"));
        kolomanggota.setCellValueFactory(new PropertyValueFactory<>("jumlah_anggota"));
        kolomwaktu.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        kolomtempat.setCellValueFactory(new PropertyValueFactory<>("Tempat"));

        tabel_utama.setItems(admin);
    }

    // creating Vehicle object based on user input
    public void createAdmin() throws SQLException {

        if(txtidpkn.getText().equals("") || txtjumlah.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating Vehicle object based on user input
            String sql_query = "INSERT INTO admin VALUES(" + txtidpkn.getText() + "," + txtjumlah.getText() + ",'" + txtwaktu.getText() + "','" + txttempat.getText() + "')";
            establishSQLConnection(sql_query);
            pushAdminOntoTable();
        }
    }

    // updating Vehicle object based on ID
    public void updateAdmin() throws SQLException {

        if(txtidpkn.getText().equals("") || txtjumlah.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating Vehicle object based on id
            String sql_query = "UPDATE admin SET jumlah_anggota = " + txtjumlah.getText() + ",waktu = '" + txtwaktu.getText() + "', tempat = '" + txttempat.getText() + "' WHERE id = " + txtidpkn.getText() + "";
            establishSQLConnection(sql_query);
            pushAdminOntoTable();
        }
    }

    // deleting Vehicle object based on ID
    @FXML
    private void deleteAdmin() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(txtidpkn.getText().equals("") || txtjumlah.getText().equals("") || txtwaktu.getText().equals("") || txttempat.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM admin WHERE id = " + txtidpkn.getText() + "";
            establishSQLConnection(sql_query);
            pushAdminOntoTable();
        }
    }

    // getting Vehicle objects based on ID
    public void getAdminByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(txtget.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding Vehicle entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM admin WHERE id = " + txtget.getText() + "";
            establishSQLConnection(sql_query);
            pushAdminOntoTableForGetButton();
        }
    }

    // using SQL statement to make relevant query to update table accordingly
    // param: sql_query:String
    private void establishSQLConnection(String sql_query) throws SQLException {

        Connection connect_object = getConnection();

        try(Statement statement = connect_object.createStatement()){
            statement.executeUpdate(sql_query);
        }
        catch (Exception e) {
        }
    }

    // event handler for button press
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == btncreate ){
            createAdmin();
        }
        else if(actionEvent.getSource() == btnupdate){
            updateAdmin();
        }

        else if(actionEvent.getSource() == btndelete){
            deleteAdmin();
        }

        else if (actionEvent.getSource() == btnget){
            getAdminByID();
        }

        else if(actionEvent.getSource() == btnrevert){
            pushAdminOntoTable();
            txtget.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {

        Eadmin vehicle = (Eadmin) tabel_utama.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        txtidpkn.setText(String.valueOf(vehicle.getId_pkn()));
        txtjumlah.setText(String.valueOf(vehicle.getJumlah_anggota()));
        txtwaktu.setText(vehicle.getWaktu());
        txttempat.setText(vehicle.getTempat());
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pushAdminOntoTable();
    }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

}
