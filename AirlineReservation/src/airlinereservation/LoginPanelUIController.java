/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservation;

import hibernate.HibernateSingleton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import md5.HashMD5;
import model.LoginAs;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class LoginPanelUIController implements Initializable {
    @FXML
    private ComboBox<LoginAs> loginAsBox;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text loginMessageText;
    
    private List<User> users;
    
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginAsBox.getItems().addAll(LoginAs.values());
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        loginMessageText.setText("");
        String username = usernameField.getText();
        String passText = passwordField.getText();
        HashMD5 pass = new HashMD5(passText);
        String password = pass.getHash();
        String userType = loginAsBox.getSelectionModel().getSelectedItem() + "";
        
        User user = new User(username, password, userType);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        try{
            users  = session.createCriteria(User.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        
        int loginConf = 0;
        
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(user.getUsername()) && users.get(i).getPassword().equals(user.getPassword()) && users.get(i).getUserType().equals(user.getUserType())){
                loginConf = 1;
                break;
            }
        }
        
        if(loginConf == 1){
            Parent root = null;
            try{
            if(user.getUserType().equals("Admin")){
                root = FXMLLoader.load(getClass().getResource("AdminUI.fxml"));
            }
            else if(user.getUserType().equals("Booker")){
                root = FXMLLoader.load(getClass().getResource("BookingOfficeUI.fxml"));
            }
            }catch(Exception e){
                System.err.println(e);
            }
            Scene scene = new Scene(root);
            AirlineReservation.getMainStage().setScene(scene);
            AirlineReservation.getMainStage().show();
        }
        else{
            loginMessageText.setText("Check you given information Please");
        }
    }
    
}
