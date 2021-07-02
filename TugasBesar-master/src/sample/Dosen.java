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


public class Dosen implements Initializable {
    public TableView table_dosen;
    public TableColumn kolomidpengawas;
    public TableColumn kolompengawas;
    public TableColumn kolomgkb;
    public TableColumn kolomruangan;
    public TextField txtidpengawas;
    public TextField txtpengawas;
    public TextField txtgkb;
    public TextField txtruangan;
    public Label text31;
    public Label text32;
    public Label text33;
    public Button deletebtn;
    public Button updatebtn;
    public Button createbtn;
    public TextField txtgetdosen;
    public Button getbtn;
    public Button revertbtn;
    public Label text3;
    @FXML
    private AnchorPane tampilan;
    @FXML
    private Button btnBack;

    private static Connection connect;
    public static Connection getConnection()
    {
        if(connect == null)
        {
            try {
                String url  ="jdbc:mysql://localhost/db_dosen";
                String user = "root";
                String pass = "";

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connect = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                //Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("WARNING: Can not create connection!");
            }
        }
        return connect;

    }

    // implementing update from remote DB to Desktop GUI application
    public ObservableList<Edosen> getDosen(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Edosen> dosen = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM pengawas";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Edosen dosen_queried = new Edosen(result_set.getInt("id_pengawas"), result_set.getString("nama_pengawas"), result_set.getInt("gkb"), result_set.getInt("ruangan"));
                dosen.add(dosen_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return dosen;
    }

    // implementing update from remote DB to Desktop GUI application onyl for Get Button
    public ObservableList<Edosen> getDosenForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Edosen> dosen = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM pengawas WHERE id_pengawas = " + txtgetdosen.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Edosen dosen_queried = new Edosen(result_set.getInt("id_pengawas"), result_set.getString("nama_pengawas"), result_set.getInt("gkb"), result_set.getInt("ruangan"));
                dosen.add(dosen_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return dosen;
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushDosenOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<Edosen> dosen = getDosenForGetButton();

        // updating DB into GUI application
        kolomidpengawas.setCellValueFactory(new PropertyValueFactory<>("id_pengawas"));
        kolompengawas.setCellValueFactory(new PropertyValueFactory<>("nama_pengawas"));
        kolomgkb.setCellValueFactory(new PropertyValueFactory<>("gkb"));
        kolomruangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));

        table_dosen.setItems(dosen);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushDosenOntoTable(){

        // retrieving data from remote DB
        ObservableList<Edosen> dosen = getDosen();

        // updating DB into GUI application
        kolomidpengawas.setCellValueFactory(new PropertyValueFactory<>("id_pengawas"));
        kolompengawas.setCellValueFactory(new PropertyValueFactory<>("nama_pengawas"));
        kolomgkb.setCellValueFactory(new PropertyValueFactory<>("gkb"));
        kolomruangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));

        table_dosen.setItems(dosen);
    }

    // creating Vehicle object based on user input
    public void createDosen() throws SQLException {

        if(txtidpengawas.getText().equals("") || txtpengawas.getText().equals("") || txtgkb.getText().equals("") || txtruangan.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating Vehicle object based on user input
            String sql_query = "INSERT INTO pengawas VALUES(" + txtidpengawas.getText() + "," + txtpengawas.getText() + ",'" + txtgkb.getText() + "','" + txtruangan.getText() + "')";
            establishSQLConnection(sql_query);
            pushDosenOntoTable();
        }
    }

    // updating Vehicle object based on ID
    public void updateDosen() throws SQLException {

        if(txtidpengawas.getText().equals("") || txtpengawas.getText().equals("") || txtgkb.getText().equals("") || txtruangan.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating Vehicle object based on id
            String sql_query = "UPDATE pengawas SET nama_pengawas = " + txtpengawas.getText() + ",gkb = '" + txtgkb.getText() + "', ruangan = '" + txtruangan.getText() + "' WHERE id_pengawas = " + txtidpengawas.getText() + "";
            establishSQLConnection(sql_query);
            pushDosenOntoTable();
        }
    }

    // deleting Vehicle object based on ID
    @FXML
    private void deleteDosen() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(txtidpengawas.getText().equals("") || txtpengawas.getText().equals("") || txtgkb.getText().equals("") || txtruangan.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM pengawas WHERE id_pengawas = " + txtidpengawas.getText() + "";
            establishSQLConnection(sql_query);
            pushDosenOntoTable();
        }
    }

    // getting Vehicle objects based on ID
    public void getDosenByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(txtgetdosen.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding Vehicle entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM pengawas WHERE id_pengawas = " + txtgetdosen.getText() + "";
            establishSQLConnection(sql_query);
            pushDosenOntoTable();
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
        if (actionEvent.getSource() == createbtn){
            createDosen();
        }
        else if(actionEvent.getSource() == updatebtn){
            updateDosen();
        }

        else if(actionEvent.getSource() == deletebtn){
            deleteDosen();
        }

        else if (actionEvent.getSource() == getbtn){
            getDosenByID();
        }

        else if(actionEvent.getSource() == revertbtn){
            pushDosenOntoTable();
            txtgetdosen.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {

        Edosen vehicle = (Edosen) table_dosen.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        txtidpengawas.setText(String.valueOf(vehicle.getId_pengawas()));
        txtpengawas.setText(vehicle.getNama_pengawas());
        txtgkb.setText(String.valueOf(vehicle.getGkb()));
        txtruangan.setText(String.valueOf(vehicle.getRuangan()));
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) { pushDosenOntoTable(); }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }
}
