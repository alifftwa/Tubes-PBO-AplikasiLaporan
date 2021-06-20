package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Admin {
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
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> colomnama;

    @FXML
    private TableColumn<?, ?> colomnim;

    @FXML
    private TableColumn<?, ?> colomtempat;

    @FXML
    private TableColumn<?, ?> colomwaktu;

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root, 300, 275));
    }

    public void btndelete (Admin1 m) {
        try {
            Connection c = Database.tryConnect();
            PreparedStatement ps;
            String sql = "delete from sample where txtfield1 = ?;";
            ps = c.prepareStatement(sql);
            ps.setString(1, m.getTxtfield1());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
