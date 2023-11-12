/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import javafxmlapplication.JavaFXMLApplication;
import model.Club;
import model.ClubDAOException;
import model.Member;
/**
 * FXML Controller class
 *
 * @author ziadf
 */
public class FXMLInicialController implements Initializable {

    @FXML
    private Label nicknameUsuario;
    @FXML
    private ImageView imagenUsuario;

    Club club;
    Member member1;
    Member member2;
    
    String nick;
    private static Member member;
    @FXML
    private Label nicknameUsuario11;
    @FXML
    private Label nicknameUsuario1;

    public FXMLInicialController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
        
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
     
        member = JavaFXMLApplication.getMember();
         nicknameUsuario.setText(member.getNickName());
        if(member.getImage()==null){
          Image image1 = new Image("/Iconos/avatars/default.png");
          imagenUsuario.setImage(image1);
         }else{
          imagenUsuario.setImage(member.getImage());
        }
        
    }
   
    @FXML
    private void CerrarSesion(ActionEvent event) throws IOException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion de Cierre de Sesion");
        alert.setHeaderText("Vas a Cerrar Tu Sesion");
        alert.setContentText("¿Seguro que quieres cerrar la sesion?");
 
        //Preparar css 
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
      

        //Preparar botones 
          alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
        alertDialog.lookupButton(ButtonType.CANCEL).setId("secondaryButton"); 

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
        JavaFXMLApplication.setMember(null);
         JavaFXMLApplication.setSharedImage(null); 
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
        }
    }

    @FXML
    private void MisReservas(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLHistorial.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void ActualizarDatos(ActionEvent event) throws IOException {
    FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLActualizarDatos.fxml"));
        Parent root = loader.load();
    FXMLActualizarDatosController actualizarDatosController = loader.getController();
    actualizarDatosController.setInicialController(this); // Pasar la referencia del controlador inicial
    JavaFXMLApplication.setRoot(root);
        
   
    }

    @FXML
    private void ReservarPista(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLReservar.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }
 
    public void actualizarDatosMiembro(Member member) {
    nicknameUsuario.setText(member.getNickName());
    if (member.getImage() == null) {
        Image image1 = new Image("/Iconos/avatars/default.png");
        imagenUsuario.setImage(image1);
    } else {
        imagenUsuario.setImage(member.getImage());
    }    }

    
}
