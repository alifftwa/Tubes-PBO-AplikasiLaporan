package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TextField txtfield1;
    @FXML
    private TextField txtfield2;
    @FXML
    private TextField txtfield3;
    @FXML
    private TextField txtfield4;
    @FXML
    private Button btninsert;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<Admin1> table;
    @FXML
    private TableColumn<?, ?> colomnama;
    @FXML
    private TableColumn<?, ?> colomnim;
    @FXML
    private TableColumn<?, ?> colomtempat;
    @FXML
    private TableColumn<?, ?> colomwaktu;
    public TextField txtid_jadwal_kuliah;
    public TextField txt_nama_pengawas;
    public TextField txtruangan;
    public TextField txtgkb;
    public TextField txtnama_peserta;
    public Button create_btn;
    public Button delete_btn;
    public Button update_btn;
    public TextField get_text;
    public Button get_btn;
    public Button revert_btn;
    public TableColumn id_column;
    public TableColumn pengawas_column;
    public TableColumn ruangan_column;
    public TableColumn gkb_column;
    public TableColumn waktu_column;
    public TableColumn peserta_column;
    public TextField txtwaktu;
    public TableView main_table;

    // establishing initial connection with MySQL server

    private static Connection connect;
    public static Connection getConnection()
    {
        if(connect == null)
        {
            try {
                String url  ="jdbc:mysql://localhost/db_admin";
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
    public ObservableList<Admin1> getAdmin(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Admin1> admin = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM admin";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Admin1 admin_queried = new Admin1(result_set.getString("id_jadwal_ujian"), result_set.getString("nama_pengawas"), result_set.getString("ruangan"), result_set.getString("gkb"), result_set.getString("waktu"), result_set.getString("nama_peserta"));
                admin.add(admin_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return admin;
    }

    // implementing update from remote DB to Desktop GUI application onyl for Get Button
    public ObservableList<Admin1> getAdminForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Admin1> admin = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM admin WHERE id_jadwal_ujian = " + get_text.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant Vehicle objects from remote DB
            while(result_set.next()){
                Admin1 admin_queried = new Admin1(result_set.getString("id_jadwal_ujian"), result_set.getString("nama_pengawas"), result_set.getString("ruangan"), result_set.getString("gkb"), result_set.getString("waktu"), result_set.getString("nama_peserta"));
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
        ObservableList<Admin1> admin = getAdminForGetButton();

        // updating DB into GUI application
        id_column.setCellValueFactory(new PropertyValueFactory<>("id_jadwal_ujian"));
        pengawas_column.setCellValueFactory(new PropertyValueFactory<>("nama_pengawas"));
        ruangan_column.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        gkb_column.setCellValueFactory(new PropertyValueFactory<>("gkb"));
        waktu_column.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        peserta_column.setCellValueFactory(new PropertyValueFactory<>("nama_peserta"));

        table.setItems(admin);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushVehiclesOntoTable(){

        // retrieving data from remote DB
        ObservableList<Admin1> admin = getAdmin();

        // updating DB into GUI application
        id_column.setCellValueFactory(new PropertyValueFactory<>("id_jadwal_ujian"));
        pengawas_column.setCellValueFactory(new PropertyValueFactory<>("nama_pengawas"));
        ruangan_column.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        gkb_column.setCellValueFactory(new PropertyValueFactory<>("gkb"));
        waktu_column.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        peserta_column.setCellValueFactory(new PropertyValueFactory<>("nama_peserta"));

        main_table.setItems(admin);
    }

    // creating Vehicle object based on user input
    public void createAdmin() throws SQLException {

        if(txtid_jadwal_kuliah.getText().equals("") || txt_nama_pengawas.getText().equals("") || txtruangan.getText().equals("") || txtgkb.getText().equals("")|| txtwaktu.getText().equals("")|| txtnama_peserta.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating Vehicle object based on user input
            String sql_query = "INSERT INTO admin VALUES(" + txtid_jadwal_kuliah.getText() + "," + txt_nama_pengawas.getText() + ",'" + txtruangan.getText() + "','" + txtgkb.getText() + "','" + txtnama_peserta.getText() + "')";
            establishSQLConnection(sql_query);
            pushVehiclesOntoTable();
        }
    }

    // updating Vehicle object based on ID
    public void updateAdmin() throws SQLException {

        if(txtid_jadwal_kuliah.getText().equals("") || txt_nama_pengawas.getText().equals("") || txtruangan.getText().equals("") || txtgkb.getText().equals("")|| txtwaktu.getText().equals("")|| txtnama_peserta.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating Vehicle object based on id
            String sql_query = "UPDATE admin SET nama_pengawas = " + txt_nama_pengawas.getText() + ",ruangan = '" + txtruangan.getText() + "', gkb = '" + txtgkb.getText() + ",waktu = '" + txtwaktu.getText() + "', ruangan = '" + txtnama_peserta.getText() + "' WHERE id = " + txtid_jadwal_kuliah.getText() + "";
            establishSQLConnection(sql_query);
            pushVehiclesOntoTable();
        }
    }

    // deleting Vehicle object based on ID
    @FXML
    private void deleteAdmin() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(txtid_jadwal_kuliah.getText().equals("") || txt_nama_pengawas.getText().equals("") || txtruangan.getText().equals("") || txtgkb.getText().equals("")|| txtwaktu.getText().equals("")|| txtnama_peserta.getText().equals("") || txtid_jadwal_kuliah.getText().equals("") || txt_nama_pengawas.getText().equals("") || txtruangan.getText().equals("") || txtgkb.getText().equals("")|| txtwaktu.getText().equals("")|| txtnama_peserta.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM vehicles WHERE id = " + txtid_jadwal_kuliah.getText() + "";
            establishSQLConnection(sql_query);
            pushVehiclesOntoTable();
        }
    }

    // getting Vehicle objects based on ID
    public void getAdminByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(get_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding Vehicle entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM vehicles WHERE id = " + get_text.getText() + "";
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
        catch (Exception e){

        }
    }

    // event handler for button press
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == create_btn ){
            createAdmin();
        }
        else if(actionEvent.getSource() == update_btn){
            updateAdmin();
        }

        else if(actionEvent.getSource() == delete_btn){
            deleteAdmin();
        }

        else if (actionEvent.getSource() == get_btn){
            getAdminByID();
        }

        else if(actionEvent.getSource() == revert_btn){
            pushVehiclesOntoTable();
            get_text.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {

        Admin1 vehicle = (Admin1) main_table.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        txtid_jadwal_kuliah.setText(String.valueOf(vehicle.getId_jadwal_ujian()));
        txt_nama_pengawas.setText(String.valueOf(vehicle.getNama_pengawas()));
        txtruangan.setText(vehicle.getRuangan());
        txtgkb.setText(vehicle.getGkb());
        txtwaktu.setText(vehicle.getWaktu());
        txtnama_peserta.setText(vehicle.getNama_Peserta());
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pushVehiclesOntoTable();
    }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

}
