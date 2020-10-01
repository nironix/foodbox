
package sample.foody;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    public LoginModel loginModel=new LoginModel();

    @FXML
    private JFXTextField textemail;

    @FXML
    private JFXPasswordField textpass,otp2;
    
    @FXML
    private Label alertLabel;
    public static int cust_id;
    private String otps;
    
    
  @FXML
    public void exitScreen(ActionEvent event){
        System.exit(0);
    }
      @FXML
     public void MenuScreen(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
                primaryStage.setTitle("Welcome To Foody Menu");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(loginModel.isDbConnected()){
             System.out.println("Db connected");
        }else{
             System.out.println("Db not connected");
        }
    } 
    
     public static void CustomerId(int cusst){
        cust_id=LoginModel.customer_id;
        
    }
    public void Login(ActionEvent event){
     try {
            if(loginModel.isLogin(textemail.getText(), textpass.getText())){
                infoBox("Login Successfull "+cust_id,null,"Success" );
                
                Node node = (Node)event.getSource();
                Stage primaryStage =new Stage();
               Stage dialogStage = (Stage) node.getScene().getWindow();
               dialogStage.close();
               Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
		primaryStage.setScene(scene);
                
               dialogStage.setScene(scene);
               dialogStage.show();
                
                
            }else{
                infoBox("Enter correct email and password",null,"Failed" );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            alertLabel.setText("Enter correct email and password");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    @FXML
    protected void send(ActionEvent event) {
        otps =otp.otpp();
        String m=textemail.getText();
        System.out.println(otps);
        System.out.println(m);
        mail.sendMail(m,otps);
        System.out.println("finnn");
    }
    public void verify(ActionEvent event) {
        String m =otp2.getText();
        System.out.println(otps);
        System.out.println(m);
        if (otps.equals(m)) {
            {
                try {
                    if(loginModel.isLogin1(textemail.getText())){
                        infoBox("Login Successfull "+cust_id,null,"Success" );

                        Node node = (Node)event.getSource();
                        Stage primaryStage =new Stage();
                        Stage dialogStage = (Stage) node.getScene().getWindow();
                        dialogStage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
                        primaryStage.setScene(scene);

                        dialogStage.setScene(scene);
                        dialogStage.show();


                    }else{
                        infoBox("Enter correct email and OTP",null,"Failed" );
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    alertLabel.setText("Enter correct email and OTP");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        }

    }



