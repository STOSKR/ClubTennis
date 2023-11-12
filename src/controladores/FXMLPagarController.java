/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import controladores.FXMLHistorialController.Reserva;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmlapplication.JavaFXMLApplication;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author shiyi
 */
public class FXMLPagarController implements Initializable {

    @FXML
    private TextField tarjeta_text;
    @FXML
    private TextField csv_text;
    
    private BooleanProperty validTarjeta;
    private BooleanProperty validCSV;
    @FXML
    private Label IncorrectTarjeta;
    @FXML
    private Label IncorrectoCSV;
    @FXML
    private Button btnConfirmar;
    Booking booking;
    Reserva reserva;
    Member member;
    Club club;
    public StringProperty pago = new SimpleStringProperty();
    @FXML
    private CheckBox GuardarDatos;
    @FXML
    private Button btnVolver;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnConfirmar.setDisable(true);
        validTarjeta = new SimpleBooleanProperty(Boolean.FALSE);
        validCSV= new SimpleBooleanProperty(Boolean.FALSE);
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
        BooleanBinding validFields = Bindings.and(validTarjeta, validCSV);
          
        BooleanBinding campoCSV =Bindings.createBooleanBinding(() ->
               tarjeta_text.getText().isEmpty(),
            tarjeta_text.textProperty()
    );
         csv_text.disableProperty().bind(campoCSV);
        
        BooleanBinding booleanBindpass = Bindings.or(tarjeta_text.textProperty().isEmpty(),
                                              csv_text.textProperty().isEmpty());
        
          btnConfirmar.disableProperty().bind(booleanBindpass);
        
  
    }
    private void checkEditTarjeta(){
    if(tarjeta_text.getLength()>0){ 
         if(!(tarjeta_text.textProperty().getValueSafe().length()==16)|| !(tarjeta_text.getText().matches("[^a-zA-Z]+")))
         //Incorrect Tarjeta
         manageError(IncorrectTarjeta, tarjeta_text, validTarjeta);
     else
        manageCorrect(IncorrectTarjeta, tarjeta_text, validTarjeta);
      }
     if(tarjeta_text.getLength()==0){
        manageCorrect2(IncorrectTarjeta, tarjeta_text, validTarjeta);
     
     }    
    }
     
       private void checkEditCSV(){
          if(csv_text.getLength()>0){ 
     if(!(csv_text.textProperty().getValueSafe().length()==3)|| !(csv_text.getText().matches("[^a-zA-Z]+")))
         //Incorrect CSV
         manageError(IncorrectoCSV, csv_text, validCSV);
     else
         manageCorrect(IncorrectoCSV, csv_text, validCSV);
         }
    if(csv_text.getLength()==0){
        manageCorrect2(IncorrectoCSV, csv_text, validCSV);
     
     }
    }
          private boolean validTarjeta() {
        // Comprobar si los apellidos son válidos
        return validTarjeta.get();
    }  
          private boolean validCSV() {
        // Comprobar si los apellidos son válidos
        return validCSV.get();
    }
       
     private void manageCorrect2(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        hideErrorMessage(errorLabel,textField);
        
    }
      private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
       // textField.requestFocus();
 
    }
      private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }
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
    private void confirmarPago(ActionEvent event) throws IOException, ClubDAOException {
        Booking b = null;
        
                Boolean pass = validTarjeta() && validCSV();
        if(pass){
        //De que parte viene el usuario Reservar o Historial
        if(FXMLReservarController.estaEnReservar){
            b = FXMLReservarController.boo;
        }else {
            Reserva r = FXMLHistorialController.reserva;
            b = r.getReserva();
        }
        
        club = Club.getInstance();
        
        member = JavaFXMLApplication.getMember();
        if(GuardarDatos.isSelected()){
        String creditC = tarjeta_text.getText();
        int svc = Integer.parseInt(csv_text.getText());
        member.setCreditCard(creditC);
        member.setSvc(svc);
        } 
        b.setPaid(Boolean.TRUE);
        Court court = b.getCourt();
        String pista = court.getName();
        LocalDateTime dia = b.getBookingDate();
        LocalDate fecha = b.getMadeForDay();
        LocalTime hora = b.getFromTime();
        club.removeBooking(b);
        club.registerBooking(dia, fecha, hora, true, court, member);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dialogo de confirmación de pago");
                    alert.setHeaderText("Pago exitoso");
                    alert.setContentText("Que tenga un execelente día");
                      DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
      

        //Preparar botones 
          alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
                    Optional<ButtonType> result = alert.showAndWait();
        /*reserva.pago.setValue("Pagado");
        reserva.setPago(pago);
        
        Reserva reserva2 = FXMLHistorialController.reserva;
        Booking booking = reserva2.getReserva();
        Club club = Club.getInstance();
        
        LocalDateTime bookingDate = booking.getBookingDate();
        LocalDate madeForDay = booking.getMadeForDay();
        LocalTime fromHour = booking.getFromTime();
        boolean paid = true;
        Court court = booking.getCourt();
        
        club.removeBooking(booking);
        club.registerBooking(bookingDate, madeForDay, fromHour, paid, court, member);
        */
        
        if(FXMLReservarController.estaEnReservar){
            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();
        }else {
            /*FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLHistorial.fxml"));
            Parent root = loader.load();
            JavaFXMLApplication.setRoot(root);*/
            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();
        }
        
}
    }

    @FXML
    private void volver(ActionEvent event) {
        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();
    }
    
}
