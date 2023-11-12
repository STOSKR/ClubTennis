/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafxmlapplication.JavaFXMLApplication;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author ziadf
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Club club;
    Member check;
    @FXML
    private TextField nickname_text;
    @FXML
    private TextField contraseña_textMostrada;
    @FXML
    private PasswordField contraseña_text;
    @FXML
    private CheckBox mostrarContraseña;
    @FXML
    private Label mensajeError;
    @FXML
    private BorderPane borderPanePrincipal;
    @FXML
    private Button Iniciar;
    
    public FXMLPrincipalController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Codigo iniciar sesion (Pruebas)
        // nickname_text.setText("edu11");
        // contraseña_text.setText("123456");
        
        BooleanBinding booleanBind = Bindings.or(nickname_text.textProperty().isEmpty(),contraseña_text.textProperty().isEmpty());
        Iniciar.disableProperty().bind(booleanBind);
        contraseña_textMostrada.setDisable(true);
        // Final código iniciar sesión
    }    
    
    @FXML
    private void Iniciar(ActionEvent event) throws IOException {
        if(contraseña_text.getText().equals("")){
            check = club.getMemberByCredentials(nickname_text.getText(), contraseña_textMostrada.getText());   
        }else{
            check = club.getMemberByCredentials(nickname_text.getText(), contraseña_text.getText());   
        }
        
        JavaFXMLApplication.setMember(check);
        
        if(!(check==null)){
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLInicial.fxml"));
            Parent root = loader.load();
            JavaFXMLApplication.setRoot(root);   
            //JavaFXMLApplication.setMember(check);
            contraseña_text.setText("");
            nickname_text.setText("");
        }else if(club.existsLogin(nickname_text.getText())){
            contraseña_text.setText("");
            contraseña_textMostrada.setText("");
            mensajeError.setText("Contraseña Incorrecta"); 
            mensajeError.visibleProperty().set(true);
       }else{
            mensajeError.setText("Usuario No Registrado"); 
            mensajeError.visibleProperty().set(true);
            nickname_text.setText(""); 
            contraseña_text.setText("");
            contraseña_textMostrada.setText("");
            }   
    }
    
    @FXML
    private void Registrarse(ActionEvent event)throws IOException, ClubDAOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLRegister.fxml"));
        JavaFXMLApplication.setRoot(loader.load());
    }

    @FXML
    private void Pistas_Disponibles(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLReservar.fxml"));
        JavaFXMLApplication.setRoot(loader.load());
    }

    @FXML
    private void MostrarContraseña(ActionEvent event) {
        if(mostrarContraseña.isSelected()){
            contraseña_textMostrada.setDisable(false);
            contraseña_textMostrada.setVisible(true);
            contraseña_textMostrada.setText(contraseña_text.getText());
            contraseña_text.setVisible(false);
            contraseña_text.setDisable(true);
        } else{
            contraseña_text.setDisable(false);
            contraseña_text.setVisible(true);
            contraseña_text.setText(contraseña_textMostrada.getText());
            contraseña_textMostrada.setDisable(true);
            contraseña_textMostrada.setVisible(false);
        }
    }

    @FXML
    private void Iniciark(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Iniciar.fire();
        }
    }

}
