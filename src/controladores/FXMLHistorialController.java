/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
public class FXMLHistorialController implements Initializable {

    Club club;
    private static Member member;
    ArrayList<Booking>lista = new ArrayList<Booking>(); 
    Booking b1;
    
    public ObservableList<Reserva> observable = null; 
    
    public TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> fechaCol;
    @FXML
    private TableColumn<Reserva, String> horaIniCol;
    @FXML
    private TableColumn<Reserva, String> estadoCol;
    
    private int contador = 0; 
    @FXML
    private TableColumn<Reserva, String> horaFinCol;
    @FXML
    private TableColumn<Reserva, String> pistaCol;
    public static Reserva reserva;
    @FXML
    private TableColumn<Reserva, String> pagarCol;
    @FXML
    private TableColumn<Reserva, String> cancelarCol;
    
  
    
    @FXML
    private Button b_volver;
    @FXML
    private AnchorPane cuadrado;
    @FXML
    private Label nicknameUsuario;
    @FXML
    private ImageView imagenUsuario;
    Image def;

    private void btnNuevaReserva(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLReservar.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }
    
    public static class MiembroA{
        public static Member m;
        public static Member getMiembroA(){
            return m;
        }
        
        public static void setMiembroA(Member m2){
            m = m2;
        }
        
    }
    
    public FXMLHistorialController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
        
    }
    private void inicializaModelo() throws ClubDAOException {
        member = JavaFXMLApplication.getMember();
        LocalDate now = LocalDate.now();
        LocalTime horaNow = LocalTime.now();
        observable = FXCollections.observableArrayList();
        List<Booking> list = club.getUserBookings(member.getNickName()); 
        lista = new ArrayList<Booking>(list);
        ArrayList<LocalDateTime> listaHoras= new ArrayList<>();
        for(Booking b:lista){
            listaHoras.add(b.getBookingDate());
            /*
            if(contador >= 10){
                break;
            }
            if(b.getMadeForDay().isBefore(now) || (b.getMadeForDay().equals(now) && b.getFromTime().isBefore(horaNow))){
                
            } else{
                Court court = b.getCourt();
                String pista = court.getName();
                LocalDate fecha = b.getMadeForDay();
                LocalTime hora = b.getFromTime();
                member = JavaFXMLApplication.getMember();
                String c = member.getCreditCard();
                String pago;
                if(c.equals("")){
                    pago = "No pagado";
                } else{
                    pago = "Pagado";
                }
                if(pago.equals("Pagado")){
                    
                }
                String horaTerminar = hora.plusHours(1).toString();
                String horaReserva = hora.toString();
                observable.add(new Reserva(fecha.toString(), pista, horaReserva, horaTerminar, pago,b));
                contador++;
            }*/
            
            //if(listaBooking.isEmpty()){
              //  lista.sort(c);
                
                //Codigo para meter las reservas en la tabla
                
                /*Court court = b.getCourt();
                String pista = court.getName();
                LocalDate fecha = b.getMadeForDay();
                LocalTime hora = b.getFromTime();
                boolean pagoBoolean = b.getPaid();
                String pago;
                if(pagoBoolean == true){
                    pago = "Pagado";
                } else{
                    pago = "No pagado";
                }   
                String horaTerminar = hora.plusHours(1).toString();
                String horaReserva = hora.toString();
                observable.add(new Reserva(fecha.toString(), pista, horaReserva, horaTerminar, pago,b));
                *//*
            } else {
                for(int i = listaBooking.size(); i >= 0; i--){
                    if(b.getBookingDate().isAfter(listaBooking.get(i).getBookingDate())){
                        if(listaBooking.size() == 10){
                            listaBooking.remove(9);
                        }
                    }
                
                }
            }*/
        }
      
        Collections.sort(listaHoras, Collections.reverseOrder());
      
        boolean comprobado = false;
        //Collections.reverse(listaHoras);
        for(int i = 0; i<listaHoras.size() && i < 10; i++){
            for(Booking b:lista){
                if(b.getPaid() == false && (LocalDate.now().equals(b.getMadeForDay()) || (LocalDate.now().plusDays(1).equals(b.getMadeForDay()) && LocalTime.now().isAfter(b.getFromTime())))){
                        club.removeBooking(b);
                } else if(b.getBookingDate().equals(listaHoras.get(i))){
                   
                    Court court = b.getCourt();
                    String pista = court.getName();
                    LocalDate fecha = b.getMadeForDay();
                    LocalTime hora = b.getFromTime();
                    boolean pagoBoolean = b.getPaid();
                    String pago;
                    if(pagoBoolean == true){
                        pago = "Pagado";
                    } else{
                        pago = "No pagado";
                    }
                    if(fecha.equals(LocalDate.now()) || (LocalDate.now().plusDays(1).equals(fecha) && LocalTime.now().isAfter(hora))){
                        if(!member.getCreditCard().equals("") && member.getSvc() != 000){
                            pago = "Pagado";
                        }
                    }
                    String horaTerminar = hora.plusHours(1).toString();
                    String horaReserva = hora.toString();
                    Reserva r1 = new Reserva(fecha.toString(), pista, horaReserva, horaTerminar, pago,b);
                    
                    if(fecha.isBefore(LocalDate.now()) || (fecha.equals(LocalDate.now()) && LocalTime.now().isAfter(hora))){
                        r1.getBotonP().setDisable(true);
                    }
                    observable.add(r1);
                    Collections.sort(observable, Collections.reverseOrder());   
                    contador++;
                    break;
                }
            }
            if(contador >= 10) break;
        }
    }
    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){ 
         member = JavaFXMLApplication.getMember();
         nicknameUsuario.setText(member.getNickName());
      
        def = new Image("/Iconos/avatars/default.png");
        if(member.getImage()==def){
          Image image1 = new Image("/Iconos/avatars/default.png");
          imagenUsuario.setImage(image1);
         }else{
          imagenUsuario.setImage(member.getImage());
        }
        
        
        try {
            // TODO
            inicializaModelo();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLHistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        member = JavaFXMLApplication.getMember();
       
        tablaReservas.setItems(observable);
            tablaReservas.setRowFactory(tableView -> {
               TableRow<Reserva> row = new TableRow();
               return row;
            });
        fechaCol.setCellValueFactory(cellData -> cellData.getValue().getFecha());
        pistaCol.setCellValueFactory(cellData -> cellData.getValue().getPista());
        horaIniCol.setCellValueFactory(cellData -> cellData.getValue().getHora());
        horaFinCol.setCellValueFactory(cellData -> cellData.getValue().getHoraFin());
        estadoCol.setCellValueFactory(cellData -> cellData.getValue().getPago());
        pagarCol.setCellValueFactory( new PropertyValueFactory<Reserva, String>("botonP"));
        cancelarCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("botonC"));
        
      }    
    
    public class HyperlinkTableCell extends TableCell<String, String> {
        private final Hyperlink link;
        int columnIndex;
        
        public HyperlinkTableCell(int myColumn, TableColumn<String, String> columna) {
            link = new Hyperlink("Libre");
            link.getStyleClass().add("a");
            columnIndex = myColumn;
            link.setOnAction(event -> {});       
        }
    }
    
    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/vista/FXMLInicial.fxml"));
        Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }
    
    public class Reserva implements Comparable<Reserva>{   
        public StringProperty pista = new SimpleStringProperty();
        public StringProperty fecha = new SimpleStringProperty();
        public StringProperty hora = new SimpleStringProperty();        
        public StringProperty horaFin = new SimpleStringProperty();
        public StringProperty pago = new SimpleStringProperty();
        public Booking booking;
        public Button botonP;
        public Button botonC;
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        public Reserva(String fecha, String pista, String hora, String horaFin, String pago, Booking reserva) {
            /*this.madeForDay = lista.get(contador).getMadeForDay();
            this.fromTime = lista.get(contador).getFromTime();
            if( lista.get(contador).getPaid() == true && (lista.get(contador).getMadeForDay().isBefore(LocalDate.now()) 
            && lista.get(contador).getFromTime().isBefore(LocalTime.now()))){
                this.estado1 = "Terminado";
            } else if( lista.get(contador).getPaid() == true){
                this.estado1 = "Pagado";
            } else{
                this.estado1 = "Pendiente";
            }
            this.court = lista.get(contador).getCourt();*/
            this.fecha.setValue(fecha);
            this.pista.setValue(pista);
            this.hora.setValue(hora);
            this.horaFin.setValue(horaFin);
            this.pago.setValue(pago);
            this.booking = reserva;
            this.botonP = new Button("Pagar");
            this.botonP.setOnAction(event ->{
                try {
                    FXMLHistorialController.reserva = new Reserva(fecha, pista, hora, horaFin, pago, reserva);
                    /*FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPagar.fxml"));
                    Parent root = loader.load();
                    JavaFXMLApplication.setRoot(root);*/
                    FXMLLoader miCargador = new
                    FXMLLoader(getClass().getResource("/vista/FXMLPagar.fxml"));
                    Parent root = miCargador.load();
                    Scene scene = new Scene(root,720,310);
                    Stage stage = new Stage();
                       stage.setMinWidth(720);
                       stage.setMinHeight(310);
                    stage.setScene(scene);
                    stage.setTitle("Ventana de pago");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    //la ventana se muestra modal
                    stage.showAndWait();
                    FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLHistorial.fxml"));
                    Parent root2 = loader.load();
                    JavaFXMLApplication.setRoot(root2);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLHistorialController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if(pago.equals("Pagado")){
                this.botonP.setDisable(true);
            } else {
                this.botonP.setDisable(false);
            }
            
            
            this.botonC = new Button("Anular");
            this.botonC.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dialogo de confirmación");
                alert.setHeaderText("Confimación para anular cita solicitada");
                alert.setContentText("¿Estás seguro que quieres anular la cita previamente solicitada?");
                 //Preparar css 
                DialogPane alertDialog = alert.getDialogPane();
                 alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                 alertDialog.getStyleClass().add("alert");
 
                  //Preparar botones 
 
                 alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
                 alertDialog.lookupButton(ButtonType.CANCEL).setId("secondaryButton"); 
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    try {
                        try {
                            club = Club.getInstance();
                            //Reserva r = FXMLHistorialController.reserva;
                            //Booking b = r.getReserva();             
                            club.removeBooking(booking);
                        } catch (ClubDAOException ex) {
                            Logger.getLogger(FXMLHistorialController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/vista/FXMLHistorial.fxml"));
                        Parent root = loader.load();
                        JavaFXMLApplication.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLHistorialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            //  (Si el dia que quieres reservar coincide con el dia de hoy) ó (si el dia que se quiere reservar es mañana y ((la hora de reserva es antes de la hora actual) ó (es igual a la hora actual))) ó (si el dia de reserva es antes del dia actual)
            
            if(booking.getMadeForDay().equals(LocalDate.now()) || ((booking.getMadeForDay().minusDays(1).equals(LocalDate.now())) && (booking.getFromTime().isBefore(LocalTime.now()) || booking.getFromTime().equals(LocalTime.now()))) || booking.getMadeForDay().isBefore(LocalDate.now())){
                this.botonC.setDisable(true);
            } else {
                this.botonC.setDisable(false);
            }
            
            
        }

        public StringProperty getPista() {
            return pista;
        }

        public void setPista(StringProperty pista) {
            this.pista = pista;
        }

        public StringProperty getFecha() {
            return fecha;
        }

        public void setFecha(StringProperty fecha) {
            this.fecha = fecha;
        }

        public StringProperty getHora() {
            return hora;
        }

        public void setHora(StringProperty hora) {
            this.hora = hora;
        }
        
        public StringProperty getHoraFin() {
            return horaFin;
        }

        public void setHoraFin(StringProperty horaFin) {
            this.horaFin = horaFin;
        }

        public StringProperty getPago() {
            return pago;
        }

        public void setPago(StringProperty pago) {
            this.pago = pago;
        }

        public Booking getReserva() {
            return booking;
        }

        public void setReserva(Booking reserva) {
            this.booking = reserva;
        }

        public Button getBotonP() {
            return botonP;
        }

        public void setBotonP(Button botonP) {
            this.botonP = botonP;
        }

        public Button getBotonC() {
            return botonC;
        }

        public void setBotonC(Button botonC) {
            this.botonC = botonC;
        }
        public int compareTo(Reserva otraReserva) {
        if (!this.getFecha().getValue().equals("") && !otraReserva.getFecha().getValue().equals("")) {
            return otraReserva.getFecha().getValue().compareTo(this.getFecha().getValue()) == 0 ? this.getHora().getValue().compareTo(otraReserva.getHora().getValue()) : this.getFecha().getValue().compareTo(otraReserva.getFecha().getValue());
        } else {
            return 0;
        }
    }
        
    }
    public void setReserva(Reserva r){
        reserva = r;
    }
}
