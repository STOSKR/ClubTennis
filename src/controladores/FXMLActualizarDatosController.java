/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
public class FXMLActualizarDatosController implements Initializable {

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
    private Button button_cancelar;
    @FXML
    private Button button_aceptar;
    @FXML
    private TextField nickname_text;
    @FXML
    private Label IncorrectNickname;
    @FXML
    private PasswordField contraseña_text;
    @FXML
    private Label lIncorrectPass;
    @FXML
    private PasswordField rcontraseña_text;
    @FXML
    private Label lIncorrectPass1;
    @FXML
    private TextField tarjeta_text;
    @FXML
    private Label IncorrectTarjeta;
    @FXML
    private PasswordField csv_text;
    @FXML
    private Label IncorrectoCSV;
    @FXML
    private TextField nombre_text;
    @FXML
    private Label IncorrectNombre;
    @FXML
    private TextField apellidos_text;
    @FXML
    private Label IncorrectApellidos;
    @FXML
    private TextField telefono_text;
    @FXML
    private Label IncorrectTelefono;
    @FXML
    private ImageView imagen;
      private BooleanProperty validPassword;
    private BooleanProperty validNombre;
    private BooleanProperty validApellidos;
    private BooleanProperty validTelefono;
    private BooleanProperty equalPasswords; 
    private BooleanProperty validTarjeta;
    private BooleanProperty validCSV;
    
  private FXMLInicialController inicialController;
    
    Club club;
   Member member;
    @FXML
    private Button quitarImagen;
    Image def;
    @FXML
    private AnchorPane cuadrado;
    @FXML
    private Label nicknameUsuario;
    @FXML
    private ImageView imagenUsuario;

    public FXMLActualizarDatosController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
    }
    
 

    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
           member = JavaFXMLApplication.getMember();
         def = new Image("/Iconos/avatars/default.png");
        quitarImagen.setVisible(false);
        //System.out.println(member.getImage().getUrl());
        
        if(member.getImage()==def){    
           quitarImagen.setVisible(false);
          }else{
           quitarImagen.setVisible(true);
        }
        
          imagen.setImage(member.getImage()); 
          
           nombre_text.setText(member.getName());
          apellidos_text.setText(member.getNickName());
          telefono_text.setText(member.getTelephone());
          contraseña_text.setText(member.getPassword());
          rcontraseña_text.setText(member.getPassword());
          tarjeta_text.setText(member.getCreditCard());
          nickname_text.setPromptText(member.getNickName());
        
          if(member.getSvc()==000){
             csv_text.setText("");
           }else{
             csv_text.setText(String.valueOf(member.getSvc()));
           }
         

              
        validPassword = new SimpleBooleanProperty();   
        equalPasswords = new SimpleBooleanProperty();
        validTarjeta = new SimpleBooleanProperty();
        validCSV= new SimpleBooleanProperty();
        validNombre = new SimpleBooleanProperty(); 
        validApellidos= new SimpleBooleanProperty(); 
        validTelefono= new SimpleBooleanProperty();
            
        validPassword.setValue(Boolean.TRUE);
        validTarjeta.setValue(Boolean.TRUE);
        equalPasswords.setValue(Boolean.TRUE);
        validCSV.setValue(Boolean.TRUE);
        validNombre.setValue(Boolean.TRUE);
        validApellidos.setValue(Boolean.TRUE);
        validTelefono.setValue(Boolean.TRUE);
         
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
        /*
             BooleanBinding campoPass =Bindings.createBooleanBinding(() ->
               contraseña_text.getText().isEmpty(),
            contraseña_text.textProperty()
    );    
         rcontraseña_text.disableProperty().bind(campoPass);
      
        
    */
   
    }   
    
    private void checkEditPassword(){
     if(contraseña_text.getLength()>0){
        if(contraseña_text.textProperty().getValueSafe().length()<6)
         //Incorrect Password
         manageError(lIncorrectPass, contraseña_text, validPassword);
     else
         manageCorrect(lIncorrectPass, contraseña_text, validPassword);
    }
    if(contraseña_text.getLength()==0){
      manageCorrect2(lIncorrectPass, contraseña_text, validPassword);
     }
    if(rcontraseña_text.getLength()!=0){
       
      if(!(contraseña_text.textProperty().getValueSafe().equals( rcontraseña_text.textProperty().getValueSafe()) )){
                showErrorMessage(lIncorrectPass1,rcontraseña_text);
                equalPasswords.setValue(Boolean.FALSE);
              //  contraseña_text.textProperty().setValue("");
             //   rcontraseña_text.textProperty().setValue("");
                //contraseña_text.requestFocus();
                }else
             
                 manageCorrect(lIncorrectPass1,rcontraseña_text,equalPasswords);
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
        manageCorrect(IncorrectoCSV, csv_text, validCSV);
     
     }
    }
      
    private void checkEditPassword1(){
        if(rcontraseña_text.getLength()>0){ 
                if(!(contraseña_text.textProperty().getValueSafe().equals( rcontraseña_text.textProperty().getValueSafe()) )){
                showErrorMessage(lIncorrectPass1,rcontraseña_text);
                equalPasswords.setValue(Boolean.FALSE);
              //  contraseña_text.textProperty().setValue("");
             //   rcontraseña_text.textProperty().setValue("");
                //contraseña_text.requestFocus();
                }else
             
                 manageCorrect(lIncorrectPass1,rcontraseña_text,equalPasswords);
             }
        
      if(rcontraseña_text.getLength()==0){
          hideErrorMessage(lIncorrectPass1,rcontraseña_text);    
     
     }
        
        }
    private void checkEditNombre(){
     if(nombre_text.getLength()>0){
        if(nombre_text.getText().matches(".*\\d.*"))
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
            if(apellidos_text.getText().matches(".*\\d.*")){
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
  
       private boolean validNombre() {
        // Comprobar si el nombre es válido
        return validNombre.get();
    }
        private boolean validApellidos() {
        // Comprobar si los apellidos son válidos
        return validApellidos.get();
    }

    private boolean validTelefono() {
        // Comprobar si el teléfono es válido
        // ...
        return validTelefono.get();
    }

    

    private boolean validPassword() {
        // Comprobar si la contraseña es válida
        // ...
        return validPassword.get();
    }

    private boolean equalPasswords() {
        // Comprobar si las contraseñas son iguales
        // ...
        return equalPasswords.get();
    }
          private boolean validTarjeta() {
        // Comprobar si el nombre es válido
        return validTarjeta.get();
    }  private boolean validcsv() {
        // Comprobar si el nombre es válido
        return validCSV.get();
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
    
    private void hideErrorMessage(Label errorLabel,TextField textField){
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    

    @FXML
    private void cancelarRegistro(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLInicial.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }

    @FXML
    private void aceptarRegistro(ActionEvent event) throws IOException {
        Boolean pass = validNombre() && validPassword() && validApellidos() && validTelefono() && equalPasswords() && validTarjeta() && validcsv();
      if(pass){ 
          
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion de Actualizacion de Datos");
        alert.setHeaderText("Vas a Cambiar tus datos");
        alert.setContentText("¿Seguro que quieres Actualizar tus datos?");
     
        //Preparar css 
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
      

        //Preparar botones 
          alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
        alertDialog.lookupButton(ButtonType.CANCEL).setId("secondaryButton"); 

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){

        member.setName(nombre_text.getText());
        member.setSurname(apellidos_text.getText());
        member.setTelephone(telefono_text.getText());
        member.setPassword(contraseña_text.getText());
        
        if(csv_text.getText().equals("")){
             member.setSvc(000);           
        }else{
           member.setSvc(Integer.parseInt(csv_text.getText()));
        }
        member.setCreditCard(tarjeta_text.getText());
        member.setImage(imagen.getImage()); 
        inicialController.actualizarDatosMiembro(member);  
      
     FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLInicial.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }
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
     quitarImagen.setVisible(false);
       }else{
     imagen.setImage(JavaFXMLApplication.getSharedImage());
     quitarImagen.setVisible(true);          
       }

    }

    @FXML
    private void QuitarImagen(ActionEvent event) {
        
              imagen.setImage(new Image("/Iconos/avatars/default.png"));     
            quitarImagen.setVisible(false);
    }

    void setInicialController(FXMLInicialController aThis) {
    this.inicialController = aThis;  
    }          
}
