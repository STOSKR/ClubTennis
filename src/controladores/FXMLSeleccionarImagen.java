/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafxmlapplication.JavaFXMLApplication;

/**
 * FXML Controller class
 *
 * @author ziadf
 */
public class FXMLSeleccionarImagen implements Initializable {

    @FXML
    private ImageView men;
    @FXML
    private ImageView men2;
    @FXML
    private ImageView men3;
    @FXML
    private ImageView men4;
    @FXML
    private ImageView men5;
    @FXML
    private ImageView woman;
    @FXML
    private ImageView woman2;
    @FXML
    private ImageView woman3;
    @FXML
    private ImageView woman4;
    @FXML
    private ImageView woman5;
    @FXML
    private ImageView woman6;
    @FXML
    private ImageView imagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
        
     men.setImage(new Image( "/Iconos/avatars/men.PNG"));
     men5.setImage(new Image( "/Iconos/avatars/men5.PNG"));
     men2.setImage(new Image( "/Iconos/avatars/men2.PNG"));
     men3.setImage(new Image( "/Iconos/avatars/men3.PNG"));
     men4.setImage(new Image( "/Iconos/avatars/men4.PNG"));
     woman.setImage(new Image( "/Iconos/avatars/woman.PNG"));
     woman2.setImage(new Image( "/Iconos/avatars/woman2.PNG"));
     woman3.setImage(new Image( "/Iconos/avatars/woman3.PNG"));
     woman4.setImage(new Image( "/Iconos/avatars/woman4.PNG"));
     woman5.setImage(new Image( "/Iconos/avatars/woman5.PNG"));
     woman6.setImage(new Image( "/Iconos/avatars/woman6.PNG"));
  
    }  
    
    
    @FXML
    private void men(MouseEvent event) {
         imagen.setImage(men.getImage());
    }  
    @FXML
    private void men2(MouseEvent event) {
        imagen.setImage(men2.getImage());
    }

    @FXML
    private void men3(MouseEvent event) {
       imagen.setImage(men3.getImage());
    }

    @FXML
    private void men4(MouseEvent event) {
        imagen.setImage(men4.getImage());
    }

    @FXML
    private void men5(MouseEvent event) {
       imagen.setImage(men5.getImage());
    }

    @FXML
    private void woman(MouseEvent event) {
       imagen.setImage(woman.getImage());
    }

    @FXML
    private void women2(MouseEvent event) {
        imagen.setImage(woman2.getImage());
    }

    @FXML
    private void women3(MouseEvent event) {
        imagen.setImage(woman3.getImage());
    }

    @FXML
    private void women4(MouseEvent event) {
        imagen.setImage(woman4.getImage());
    }

    @FXML
    private void women5(MouseEvent event) {
        imagen.setImage(woman5.getImage());
    }

    @FXML
    private void women6(MouseEvent event) {
       imagen.setImage(woman6.getImage());
    }

    @FXML
    private void Siguiente(ActionEvent event) throws IOException {
     if(imagen.getImage()!=null){
        JavaFXMLApplication.setSharedImage(imagen.getImage());
        woman.getScene().getWindow().hide();
     }else{
        JavaFXMLApplication.setSharedImage(null);
        woman.getScene().getWindow().hide();
     
     }
     
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
         if(imagen.getImage()==null){
        JavaFXMLApplication.setSharedImage(null);
        woman.getScene().getWindow().hide();
    }
    }

    @FXML
    private void add(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        
        if (archivoSeleccionado != null) {
            String urlImage = archivoSeleccionado.toURI().toString();
            Image imagenV = new Image(archivoSeleccionado.toURI().toString());
            imagen.setImage(imagenV);
            
        }
    }
}


    

