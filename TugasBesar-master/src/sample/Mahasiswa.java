package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.net.URL;
import java.util.*;

import static javafx.fxml.FXMLLoader.load;

public class Mahasiswa {

    public Button btndaftar;
    @FXML
    private Button btnbck;
    @FXML
    protected Button btncv;

    public TextField emailToField;
    public TextField emailFromField;
    public TextArea emailMessageField;
    public TextField emailSubjectField;
    public PasswordField emailPasswordField;
    public Label sentBoolValue;

    public void buttonClicked(ActionEvent actionEvent){
        sendEmail();
    }

    public void sendEmail(){
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }
    public void btncv(){
        try{
            Desktop.getDesktop().browse(new URL("https://drive.google.com/drive/folders/1C00YQoRuzGXP0hVPE1YIrs9CsMhQYcWe?usp=sharing").toURI());
        }
        catch (Exception e){
        }
    }

    public void MasukMahasiswa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) btnbck.getScene().getWindow();
        window.setScene(new Scene(root, 786, 480));
    }

    public void MasukMahasiswa1() throws Exception {
        Parent root = load(getClass().getResource("Mahasiswa1.fxml"));
        Stage window = (Stage) btndaftar.getScene().getWindow();
        window.setScene(new Scene(root, 786, 550));
    }

}
