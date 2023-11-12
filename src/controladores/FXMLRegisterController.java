/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import javafx.scene.image.Image;
import java.io.IOException;
import javafxmlapplication.JavaFXMLApplication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;


/**
 * FXML Controller class
 *
 * @author ustar
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private Label contraseña_label;
    @FXML
    private Label nombre_label;
    @FXML
    private Label apellidos_label;
    @FXML
    private Label telefono_label;
    @FXML
    private Label nickname_label;
    @FXML
    private Label rcontraseña_label;
    @FXML
    private Label tarjeta_label;
    @FXML
    private Label csv_label;
    @FXML
    private TextField nombre_text;
    @FXML
    private TextField apellidos_text;
    @FXML
    private TextField telefono_text;
    @FXML
    private TextField nickname_text;
    @FXML
    private PasswordField contraseña_text;
    @FXML
    private PasswordField rcontraseña_text;
    @FXML
    private TextField tarjeta_text;
    @FXML
    private PasswordField csv_text;
    @FXML
    private Button button_cancelar;
    @FXML
    private Button button_aceptar;
    
    private BooleanProperty validPassword;
    private BooleanProperty validNombre;
    private BooleanProperty validApellidos;
    private BooleanProperty validTelefono;
    private BooleanProperty equalPasswords; 
    private BooleanProperty validTarjeta;
    private BooleanProperty validCSV;
    private BooleanProperty validnickName;
    @FXML
    private Label IncorrectNickname;
    @FXML
    private Label lIncorrectPass;
    @FXML
    private Label lIncorrectPass1;
    @FXML
    private Label IncorrectTarjeta;
    @FXML
    private Label IncorrectNombre;
    @FXML
    private Label IncorrectApellidos;
    @FXML
    private Label IncorrectTelefono;
    Club club;
    @FXML
    private ImageView imagen;
    @FXML
    private Label IncorrectoCSV;
    @FXML
    private Button quitarImagen;
    private BooleanBinding camposVacios;
    Image def;
    @FXML
    private AnchorPane cuadrado;
    @FXML
    private Label nicknameUsuario;
    @FXML
    private ImageView imagenUsuario;
    
    public FXMLRegisterController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Poner el imagen por defecto
        def = new Image("/Iconos/avatars/default.png");
        imagen.setImage(def);    
        
        // Crear y asignar valores de propiedades booleanas para comprobar
        validPassword = new SimpleBooleanProperty();   
        equalPasswords = new SimpleBooleanProperty();
        validTarjeta = new SimpleBooleanProperty();
        validCSV= new SimpleBooleanProperty();
        validNombre = new SimpleBooleanProperty(); 
        validApellidos= new SimpleBooleanProperty(); 
        validTelefono= new SimpleBooleanProperty();
        validnickName= new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        validTarjeta.setValue(Boolean.FALSE);
        equalPasswords.setValue(Boolean.FALSE);
        validCSV.setValue(Boolean.FALSE);
        validNombre.setValue(Boolean.FALSE);
        validApellidos.setValue(Boolean.FALSE);
        validTelefono.setValue(Boolean.FALSE);
        validnickName.setValue(Boolean.FALSE);
        
        if(tarjeta_text.getText().equals("")){
            validTarjeta.setValue(Boolean.TRUE); 
        }
        
        if(csv_text.getText().equals("")){
            validCSV.setValue(Boolean.TRUE); 
        }
        
        contraseña_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditPassword();
            }
        });
        
        nombre_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditNombre();
            }
        });
  
        rcontraseña_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditPassword1();
            }
        });
        
        tarjeta_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditTarjeta();
            }
        });
        
        csv_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditCSV();
            }
        });
        
        apellidos_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditApellidos();
            }
        });
        telefono_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditTelefono();
            }
         });
        nickname_text.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditnickName();
            }
        });
        
        // Comprueba si todos los campos son válidos
        BooleanBinding validFields = Bindings.and(validNombre, validPassword).and(equalPasswords).and(validApellidos).and(validTelefono).and(validnickName);
        
        BooleanBinding campoCSV = Bindings.createBooleanBinding(() ->
               tarjeta_text.getText().isEmpty(),tarjeta_text.textProperty()
        );
        
        // Desavilita cvs_text si el texto de la tarjeta está vacía
        csv_text.disableProperty().bind(campoCSV);
         
        camposVacios = Bindings.createBooleanBinding(() ->
            nombre_text.getText().isEmpty() || apellidos_text.getText().isEmpty() || telefono_text.getText().isEmpty() ||
            nickname_text.getText().isEmpty() || contraseña_text.getText().isEmpty() || rcontraseña_text.getText().isEmpty(),
            nombre_text.textProperty(), apellidos_text.textProperty(), telefono_text.textProperty(), 
            nickname_text.textProperty(), contraseña_text.textProperty(), rcontraseña_text.textProperty()
        );
        
        // Si algún campo está vacío, deshabilitar el botón de aceptar
        button_aceptar.disableProperty().bind(camposVacios) ;
        quitarImagen.setVisible(false);
            if(imagen.getImage()==def){
                quitarImagen.setVisible(false);
            } else{
                quitarImagen.setVisible(true);
        }  
    }
    
    private boolean validNombre() {
        return validNombre.get();
    }
    private boolean validApellidos() {
        return validApellidos.get();
    }
    private boolean validTelefono() {
        return validTelefono.get();
    }
    private boolean validnickName() {
        return validnickName.get();
    }
    private boolean validPassword() {
        return validPassword.get();
    }
    private boolean equalPasswords() {
        return equalPasswords.get();
    }
    private boolean validTarjeta() { 
        return validTarjeta.get();
    }
    private boolean validcsv() {
        return validCSV.get();
    }
    
    private void checkEditPassword(){
        if(contraseña_text.getLength()==0){
          manageCorrect2(lIncorrectPass, contraseña_text, validPassword);
        }

        // Si la contraseña tiene menos de seis caracteres
        if(contraseña_text.textProperty().getValueSafe().length()<6){
            manageError(lIncorrectPass, contraseña_text, validPassword);
        } else{
            manageCorrect(lIncorrectPass, contraseña_text, validPassword);
        }
        
        // Si las contraseñas no son iguales
        if(!(contraseña_text.textProperty().getValueSafe().equals( rcontraseña_text.textProperty().getValueSafe()) )){
            showErrorMessage(lIncorrectPass1,rcontraseña_text);
            equalPasswords.setValue(Boolean.FALSE);
        } else{
            manageCorrect(lIncorrectPass1,rcontraseña_text,equalPasswords);
        }
    }
    
     private void checkEditnickName(){
   if(nickname_text.getLength()>0){      
     if(club.existsLogin(nickname_text.getText())||nickname_text.getText().contains(" "))
         //Incorrect nickName
         manageError(IncorrectNickname, nickname_text, validnickName);
     else
         manageCorrect(IncorrectNickname, nickname_text, validnickName);
   }
  if(nickname_text.getLength()==0){
      manageCorrect2(IncorrectNickname, nickname_text, validnickName);
     
     }
    }
     private void checkEditTarjeta(){
    if(tarjeta_text.getLength()>0){ 
         if(!(tarjeta_text.textProperty().getValueSafe().length()==16)|| !(tarjeta_text.getText().matches("[0-9]*")))
         //Incorrect Tarjeta
         manageError(IncorrectTarjeta, tarjeta_text, validTarjeta);
     else
        manageCorrect(IncorrectTarjeta, tarjeta_text, validTarjeta);
      }
     if(tarjeta_text.getLength()==0){
        manageCorrect(IncorrectTarjeta, tarjeta_text, validTarjeta);
     
     }    
    }
      private void checkEditCSV(){
          if(csv_text.getLength()>0){ 
     if(!(csv_text.textProperty().getValueSafe().length()==3)|| !(csv_text.getText().matches("[0-9]*")))
         //Incorrect CSV
         manageError(IncorrectoCSV, csv_text, validCSV);
     else
         manageCorrect(IncorrectoCSV, csv_text, validCSV);
         }
    if(csv_text.getLength()==0){
        if(tarjeta_text.getLength() != 0){
            manageError(IncorrectoCSV, csv_text, validCSV);
        } else{
            manageCorrect(IncorrectoCSV, csv_text, validCSV);
        }
    }
    }
      
    private void checkEditPassword1(){
        if(rcontraseña_text.getLength()>0){ 
                if(!(contraseña_text.textProperty().getValueSafe().equals( rcontraseña_text.textProperty().getValueSafe()) )){
                showErrorMessage(lIncorrectPass1,rcontraseña_text);
                equalPasswords.setValue(Boolean.FALSE);
              //  contraseña_text.textProperty().setValue("");
              // rcontraseña_text.textProperty().setValue("");
                //contraseña_text.requestFocus();
                }else
             
                 manageCorrect(lIncorrectPass1,rcontraseña_text,equalPasswords);
             }
        
      if(rcontraseña_text.getLength()==0){
                 manageCorrect2(lIncorrectPass1,rcontraseña_text,equalPasswords);
     }
        
        }
    private void checkEditNombre(){
     if(nombre_text.getLength()>0){
        if(!(nombre_text.getText().matches("[a-zA-Z]+")||nombre_text.getText().contains(" ")))
         //Incorrect Nombre
         manageError(IncorrectNombre, nombre_text, validNombre);
     else
         manageCorrect(IncorrectNombre, nombre_text, validNombre);
     }
     if(nombre_text.getLength()==0){
       manageCorrect2(IncorrectNombre, nombre_text, validNombre);
     
     }
    }
    private void checkEditApellidos(){
        if(apellidos_text.getLength()>0){ 
            if(!(apellidos_text.getText().matches("[a-zA-Z]+")||nombre_text.getText().contains(" "))){
             //Incorrect Apellido
            manageError(IncorrectApellidos, apellidos_text, validApellidos);
            }else{
            manageCorrect(IncorrectApellidos, apellidos_text, validApellidos);
            }
        }
       if(apellidos_text.getLength()==0){
     manageCorrect2(IncorrectApellidos, apellidos_text, validApellidos);
     
     }
    }
    
    private void checkEditTelefono(){
        if(telefono_text.getLength()>0){ 
     if(!(telefono_text.textProperty().getValueSafe().length()==9)||!(telefono_text.getText().matches("[0-9]*")))
         //Incorrect Telefono
         manageError(IncorrectTelefono, telefono_text, validTelefono);
     else
         manageCorrect(IncorrectTelefono, telefono_text, validTelefono);
        } 
         if(telefono_text.getLength()==0){
         manageCorrect2(IncorrectTelefono, telefono_text, validTelefono);
     
     }
    }

    
     private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
       // textField.requestFocus();
 
    }
    /**
     * Updates the boolProp to true. Changes the background 
     * of the edit to the default value. Makes the error label invisible. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     **/
       
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }
    private void manageCorrect2(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        hideErrorMessage(errorLabel,textField);
        
    }
    /**
     * Changes to red the background of the edit and
     * makes the error label visible
     * @param errorLabel
     * @param textField 
     */
    
    private void showErrorMessage(Label errorLabel,TextField textField){
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FCE5E0");    
    }
    /**
     * Changes the background of the edit to the default value
     * and makes the error label invisible.
     * @param errorLabel
     * @param textField 
     */
    
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    

    @FXML
    private void cancelarRegistro(ActionEvent e) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    
    }

    @FXML
    private void aceptarRegistro(ActionEvent e)throws IOException, ClubDAOException {
        Boolean pass = validNombre() && validPassword() && validApellidos() && validTelefono() && equalPasswords() && validnickName()&& validTarjeta() && validcsv();
      if(pass){
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
     
        String nickName = nickname_text.getText();
        String password = contraseña_text.getText();
        String name = nombre_text.getText();
        String surname = apellidos_text.getText();
        String creditC = tarjeta_text.getText();
        int svc;
              
        if(csv_text.getText().equals("")){
             svc =000;           
        }else{
            svc= Integer.parseInt(csv_text.getText());
        }
        String tel = telefono_text.getText();
        Image image;
        if(imagen.getImage()==null){
          image = new Image("Iconos/avatars/default.png");  
        
        }else{
         image = JavaFXMLApplication.getSharedImage();   
            
        }
        
             Member member = club.registerMember(name, surname, tel, nickName, password,creditC, svc,image);
        
      }
    }
    

    @FXML
    private void AñadirImagen(ActionEvent event) throws IOException {
        
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/FXMLSeleccionarImagen.fxml"));
        Parent root = miCargador.load();
         
        Scene scene = new Scene(root,600,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Seleccionar Imagen");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(650);
        stage.setMinHeight(700);
        //la ventana se muestra modal
        stage.showAndWait(); // espera a que se cierre la segunda ventana.
        // para obtener el valor modificado en la ventana emergente

        if(JavaFXMLApplication.getSharedImage()==null){
     imagen.setImage(def);
       }else{
     imagen.setImage(JavaFXMLApplication.getSharedImage());
       }  
    if(imagen.getImage()== def){
            quitarImagen.setVisible(false);
    }else{
            quitarImagen.setVisible(true);
 }
    }

    private void VolverInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void QuitarImagen(ActionEvent event) {
  
         imagen.setImage(new Image("/Iconos/avatars/default.png"));    
             quitarImagen.setVisible(false);
         
        }
}
        
    
