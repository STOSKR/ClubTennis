/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import controladores.FXMLHistorialController.Reserva;
import static controladores.FXMLHistorialController.reserva;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
 * @author ustar
 */
public class FXMLReservarController implements Initializable {

    @FXML
    private Button button_reservar;
    @FXML
    private Label fecha_label;
    @FXML
    private DatePicker fecha_datePicker;
    @FXML
    private Label pista_label;
    @FXML
    private ComboBox<String> pista_comboBox;
    @FXML
    private ToggleButton b_hora0;
    @FXML
    private ToggleButton b_hora1;
    @FXML
    private ToggleButton b_hora2;
    @FXML
    private ToggleButton b_hora3;
    @FXML
    private ToggleButton b_hora4;
    @FXML
    private ToggleButton b_hora5;
    @FXML
    private ToggleButton b_hora6;
    @FXML
    private ToggleButton b_hora7;
    @FXML
    private ToggleButton b_hora8;
    @FXML
    private ToggleButton b_hora9;
    @FXML
    private ToggleButton b_hora10;
    @FXML
    private ToggleButton b_hora11;
    @FXML
    private ToggleButton b_hora12;
    @FXML
    private Label la0;
    @FXML
    private Label la1;
    @FXML
    private Label la2;
    @FXML
    private Label la3;
    @FXML
    private Label la4;
    @FXML
    private Label la5;
    @FXML
    private Label la6;
    @FXML
    private Label la7;
    @FXML
    private Label la8;
    @FXML
    private Label la9;
    @FXML
    private Label la10;
    @FXML
    private Label la11;
    @FXML
    private Label la12;
    @FXML
    private Button b_volver;
    @FXML
    private AnchorPane cuadrado;
    @FXML
    private Label nicknameUsuario;
    @FXML
    private ImageView imagenUsuario;

    //Clase para asociar botones y labels etc
    private class SelecHora{
        public ToggleButton b;
        public Label l;
        
        public SelecHora(ToggleButton b1, Label l1){
            this.b = b1;
            this.l = l1;
        }
    }
    
    //Variables "compartidas" 
    Member miembro;
    Club club;
    boolean esta_logueado;
    ArrayList<Booking> reservas_usuario;
    private SelecHora[] arrayHoras;
    private boolean[] horas_seleccionadas;
    static boolean estaEnReservar = false;
    public static Booking boo; 
    
    
    //Codigo para inicializar el club
    public FXMLReservarController() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        miembro = JavaFXMLApplication.getMember(); 
   
        cuadrado.setId("cuadrado");
        
        //Obtener los datos importantes del miembro / saber si esta login
        
        if(!(miembro == null)){
            esta_logueado = true;
            List<Booking> lista_aux = (List<Booking>) club.getUserBookings(miembro.getNickName());
            reservas_usuario = new ArrayList<>(lista_aux);
            //Inicializacion del perfil visible

            nicknameUsuario.setText(miembro.getNickName());
            if(miembro.getImage()==null){
                Image image1 = new Image("/Iconos/avatars/default.png");
                imagenUsuario.setImage(image1);
            }else{
                imagenUsuario.setImage(miembro.getImage());
            }
        
        } else{
            esta_logueado = false;
        }
        
        estaEnReservar = true;
        boo = null;
        
        //Rellenar el booleano
        horas_seleccionadas = new boolean[13];
        for(int i = 0; i < horas_seleccionadas.length; i++){
            horas_seleccionadas[i] = false;
        }
        
        // Hacer un array con los botones de las horas
        arrayHoras = new SelecHora[13];
        arrayHoras[0] = new SelecHora(b_hora0, la0);
        arrayHoras[1] = new SelecHora(b_hora1, la1);
        arrayHoras[2] = new SelecHora(b_hora2, la2);
        arrayHoras[3] = new SelecHora(b_hora3, la3);
        arrayHoras[4] = new SelecHora(b_hora4, la4);
        arrayHoras[5] = new SelecHora(b_hora5, la5);
        arrayHoras[6] = new SelecHora(b_hora6, la6);
        arrayHoras[7] = new SelecHora(b_hora7, la7);
        arrayHoras[8] = new SelecHora(b_hora8, la8);
        arrayHoras[9] = new SelecHora(b_hora9, la9);
        arrayHoras[10] = new SelecHora(b_hora10, la10);
        arrayHoras[11] = new SelecHora(b_hora11, la11);
        arrayHoras[12] = new SelecHora(b_hora12, la12);
        
        // Cargar las opciones de pista
        pista_comboBox.setValue("Pista 1");
        var pistas_lista = club.getCourts();
        int num = 1;
        for(Court pista : pistas_lista){
            //pista_comboBox.getItems().add("Pista" + num++);
            pista_comboBox.getItems().add(pista.getName());
        }
        
        //Detectar si esta iniciada la sesion o no
            button_reservar.setDisable(true);
            int indice2 = 0;
            for(SelecHora b : arrayHoras){
               b.b.setDisable(!esta_logueado);
               b.l.setVisible(false);
            }
            indice2++;
        
        // Colocar por defecto el dia actual en el calencadario y desabilitar fechas aparte
        fecha_datePicker.setValue(LocalDate.now());
        fecha_datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
            
            //setDisable(empty ||  );
            setDisable(empty || date.compareTo(today) < 0 ||date.compareTo(today.plusDays(14)) > 0);
            
            }
        });
        
        //Hacer que la fecha no sea editable manualmente
        fecha_datePicker.getEditor().setDisable(true);
        fecha_datePicker.getEditor().setOpacity(1);
        
        //Cargamos datos de la pista por defecto (la primera) y el dia actual
        actualizarTabla("Pista 1", (LocalDate) fecha_datePicker.getValue());
        
        //Añadir manejador para el cambio de pista
        pista_comboBox.setOnAction((e)-> {
            actualizarTabla(pista_comboBox.getValue(),(LocalDate) fecha_datePicker.getValue());
            for(int i = 0; i < 13; i++){
                horas_seleccionadas[i] = false;
                arrayHoras[i].b.setSelected(false);
                button_reservar.setDisable(true);
            }
            
        });
        fecha_datePicker.setOnAction((e)-> {
            actualizarTabla(pista_comboBox.getValue(),(LocalDate) fecha_datePicker.getValue());
            for(int i = 0; i < 13; i++){
                horas_seleccionadas[i] = false;
                arrayHoras[i].b.setSelected(false);
                button_reservar.setDisable(true);
            }
        });
    }
        
        
    
    
    private void actualizarTabla(String pista_nombre, LocalDate fecha){
        List<Booking> lista_aux = (List<Booking>) club.getCourtBookings(pista_nombre, fecha);
        if(!(lista_aux.isEmpty())){
            for(SelecHora hora : arrayHoras){
                hora.b.setDisable(!esta_logueado);
                hora.l.setVisible(false);
            }
            ArrayList<Booking> reservas_pista = new ArrayList<>(lista_aux);
            int indice;
            for(Booking reser : reservas_pista){
                indice = reser.getFromTime().getHour() - 9;
                String nickname = reser.getMember().getNickName();
                arrayHoras[indice].b.setDisable(true);
                arrayHoras[indice].l.setText(nickname);
                arrayHoras[indice].l.setDisable(false);
                arrayHoras[indice].l.setVisible(true);
                horas_seleccionadas[indice] = false;
            }
        }else{
            for(SelecHora hora : arrayHoras){
                hora.b.setDisable(!esta_logueado);
                hora.l.setVisible(false);
            }
        }
        //Desabilitar las horas del dia de hoy que ya han pasado
        if(fecha.equals(LocalDate.now())){
            int horaActual = LocalDateTime.now().getHour();
            int hola = 0;
            for(SelecHora hora2 : arrayHoras){
                if((hola + 9) < horaActual){
                    hora2.b.setDisable(true);
                }
                hola++;
            }
        }
        for(SelecHora hora2 : arrayHoras){
                if(!esta_logueado){
                    hora2.b.setDisable(true);
                }
                
        }
        
    }

    @FXML
    private void reservarHora(ActionEvent event) throws ClubDAOException, IOException {
        int indice = 0;
        Booking reser = null;
        boolean f = true;
        int numHorasS = 0;
        for(boolean bb : horas_seleccionadas){
            if(bb){
                numHorasS++;
            }
        }
        for(boolean estaSelec : horas_seleccionadas){
            if(estaSelec && f){
                reser = hacerReserva(indice);
                if(reser == null){
                    f = false;
                }
            }
            indice++;
        }
        boolean continuee = true;
        while(continuee){
            if(f){
                if(miembro.checkHasCreditInfo()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dialogo de confirmación");
                    if (numHorasS > 1) {
                        alert.setHeaderText("Se han hecho las reservas");
                         alert.setContentText("Reserva hecha exitosamente");
                         DialogPane alertDialog = alert.getDialogPane();
                          alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                          alertDialog.getStyleClass().add("alert");
                           alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
                    }else{
                        alert.setHeaderText("Se ha hecho la reserva");
                          alert.setContentText("Reserva hecha exitosamente");
                            DialogPane alertDialog = alert.getDialogPane();
                              alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                           alertDialog.getStyleClass().add("alert");
                            alertDialog.lookupButton(ButtonType.OK).setId("primaryButton");
                    }
                    //alert.setContentText("¿Estás seguro que quieres anular la cita previamente solicitada?");
                    Optional<ButtonType> result = alert.showAndWait();
                    continuee = false;
                    
                    //Si no tiene tarjeta
                } else{
                    //Caso en el que la reserva sea del dia actual
                    if(fecha_datePicker.getValue().equals(LocalDate.now())){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText("Información");
                        alert.setContentText("Para poder hacer la reserva de una pista previo a 24 horas requiere pagarla");
                        ButtonType buttonTypeOne = new ButtonType("Cancelar Reserva");
                        ButtonType buttonTypeTwo = new ButtonType("Pagar");
                        DialogPane alertDialog = alert.getDialogPane();
                        alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                        alertDialog.getStyleClass().add("alert");
                  alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                  //Preparar botones 
 
                 alertDialog.lookupButton(buttonTypeOne).setId("primaryButton"); 
                 alertDialog.lookupButton(buttonTypeTwo).setId("secondaryButton"); 

                    
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == buttonTypeOne){

                            //Mostra un aviso de que debe pagar si no se le anulara...

                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Aviso");
                            alerta.setContentText("Se ha cancelado la reserva");
                              alertDialog = alert.getDialogPane();
                              alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                           alertDialog.getStyleClass().add("alert");
                            alertDialog.lookupButton(ButtonType.OK).setId("primaryButton");
                            Optional<ButtonType> resulta = alerta.showAndWait();
                            club.removeBooking(reser);
                            actualizarTabla(pista_comboBox.getValue(),fecha_datePicker.getValue());
                            continuee = false;
                        }else if(result.get() == buttonTypeTwo){
                            try {
                                boo = reser;
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
                                if(reser.getPaid()){
                                    continuee = false;
                                }
                            } catch (IOException ex) {
                            }
                        }

                    }else if(fecha_datePicker.getValue().minusDays(1).equals(LocalDate.now())
                             && reser.getFromTime().getHour() <= LocalDateTime.now().getHour()){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText("Información");
                        alert.setContentText("Para poder hacer la reserva de una pista previo a 24 horas requiere pagarla");
                        ButtonType buttonTypeOne = new ButtonType("Cancelar Reserva");
                        ButtonType buttonTypeTwo = new ButtonType("Pagar");

                        alert.getButtonTypes().setAll( buttonTypeTwo, buttonTypeOne);
                           //Preparar css 
                        DialogPane alertDialog = alert.getDialogPane();
                        alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                        alertDialog.getStyleClass().add("alert");
 
                  //Preparar botones 
 
                        alertDialog.lookupButton(buttonTypeOne).setId("primaryButton"); 
                        alertDialog.lookupButton(buttonTypeTwo).setId("secondaryButton"); 
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == buttonTypeOne){

                            //Mostra un aviso de que debe pagar si no se le anulara...

                            Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                            alerta2.setTitle("Aviso");
                            alerta2.setHeaderText("Reserva cancelada");
                            alerta2.setContentText(null);
                            //ButtonType buttonType3 = new ButtonType("Aceptar");
                            //alert.getButtonTypes().setAll(buttonType3);
                            DialogPane alertDialog1 = alerta2.getDialogPane();
                            alertDialog1.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                            alertDialog1.getStyleClass().add("alert");
                            alertDialog1.lookupButton(ButtonType.OK).setId("primaryButton"); 
                            
                            Optional<ButtonType> resulta = alerta2.showAndWait();
                            club.removeBooking(reser);
                            actualizarTabla(pista_comboBox.getValue(),fecha_datePicker.getValue());
                            continuee = false;
                        }else if(result.get() == buttonTypeTwo){
                            try {
                                boo = reser;
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
                                if(reser.getPaid()){
                                    continuee = false;
                                }
                            } catch (IOException ex) {
                            }
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Dialogo de confirmación");
                        if (numHorasS > 1) {
                            alert.setHeaderText("Se han hecho las reservas");
                        }else{
                            alert.setHeaderText("Se ha hecho la reserva");
                        }
                        alert.setContentText("¿Desea pagar ahora o pagar luego?");
                        ButtonType buttonTypeOne = new ButtonType("Luego");
                        ButtonType buttonTypeTwo = new ButtonType("Ahora");

                        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                              //Preparar css 
                DialogPane alertDialog = alert.getDialogPane();
                 alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                 alertDialog.getStyleClass().add("alert");
 
                  //Preparar botones 
 
                 alertDialog.lookupButton(buttonTypeOne).setId("primaryButton"); 
                 alertDialog.lookupButton(buttonTypeTwo).setId("secondaryButton"); 
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == buttonTypeOne){

                            //Mostra un aviso de que debe pagar si no se le anulara...

                            Alert alerta = new Alert(Alert.AlertType.WARNING);
                            alerta.setTitle("Aviso");
                            alerta.setContentText("Se recuerda que si no ha pagado su reserva antes de las 24 horas estas quedaran anuladas automaticamente");
                          
                             alertDialog = alerta.getDialogPane();
                            alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                            alertDialog.getStyleClass().add("alert");
                   //Preparar botones 
                          alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
                             Optional<ButtonType> resulta = alerta.showAndWait();
                            continuee = false;
                        }else if(result.get() == buttonTypeTwo){
                            try {
                                boo = reser;
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
                                if(reser.getPaid()){
                                    continuee = false;
                                }
                            } catch (IOException ex) {
                            }
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dialogo de Aviso");

                alert.setContentText("No se pueden reservar 3 o más horas seguidas ni reservar diferentes pistas a la misma hora");
                  DialogPane alertDialog = alert.getDialogPane();
                   alertDialog.getStylesheets().add(getClass().getResource("/CSS/alerta.css").toExternalForm());
                    alertDialog.getStyleClass().add("alert");
      

             //Preparar botones 
                  alertDialog.lookupButton(ButtonType.OK).setId("primaryButton"); 
                Optional<ButtonType> result = alert.showAndWait();
                
                for(int i = 0; i < 13; i++){
                    horas_seleccionadas[i] = false;
                    arrayHoras[i].b.setSelected(false);
                    button_reservar.setDisable(true);
                }
                continuee = false;
            }
        }
    }
    
    //Metodo que genere la reserva
    private Booking hacerReserva(int indice) throws ClubDAOException{
        Booking ress = null;
        int numHorasS = 0;
        boolean seReserva = true;
        int año = ((LocalDate) fecha_datePicker.getValue()).getYear();
        int mes = ((LocalDate) fecha_datePicker.getValue()).getMonthValue();
        int dia = ((LocalDate) fecha_datePicker.getValue()).getDayOfMonth();
        for(boolean bb : horas_seleccionadas){
            if(bb){
                numHorasS++;
            }
        }
        
        List<Booking> lista_aux = (List<Booking>) club.getUserBookings(miembro.getNickName());
        for(Booking reserva : lista_aux){
            if(reserva.isForDay((LocalDate) fecha_datePicker.getValue())){
                int indice2 = reserva.getFromTime().getHour() - 9;
                if(indice == indice2 || !sePuedeReservar(indice, lista_aux, horas_seleccionadas)){
                    seReserva = false;
                    break;
                }
            }
            
            if (numHorasS > 2){
                if(!sePuedeReservar(indice, lista_aux, horas_seleccionadas)){
                    seReserva = false;
                    break;
                }
            }
        }
        
        //Argumentos para crear un booking
        if(seReserva){
            LocalDateTime fechaBooking = LocalDateTime.now();
            LocalDate fechaDeReserva = LocalDate.of(año, mes, dia);
            LocalTime horaDeReserva = LocalTime.of(indice+9, 0);
            boolean pagado = miembro.checkHasCreditInfo();
            Court pista = club.getCourt(pista_comboBox.getValue());
            ress = club.registerBooking(fechaBooking, fechaDeReserva, horaDeReserva, pagado, pista, miembro);
            arrayHoras[indice].b.setDisable(true);
            arrayHoras[indice].b.setSelected(false);
            horas_seleccionadas[indice] = false;
            arrayHoras[indice].l.setText(miembro.getNickName());
            arrayHoras[indice].l.setVisible(true);
        }
        return ress;
    }

    @FXML
    private void pulsar_volver(ActionEvent event) throws IOException {
        //Dependiendo si hay o no usuario logueado volvera a la pantalla correspondiente
        FXMLLoader loader;
        estaEnReservar = false;
        if (esta_logueado){
            loader = new  FXMLLoader(getClass().getResource("/vista/FXMLInicial.fxml"));
        } else{
            loader = new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        }
                Parent root = loader.load();
        JavaFXMLApplication.setRoot(root);
    }

    private boolean sePuedeReservar(int indice0, List<Booking> lista_aux, boolean[] hSelec){
        boolean res = true;
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        int num = 0;
        int num2 = 0;
        
        for(boolean as : hSelec){
        ArrayList<Integer> horas = new ArrayList<>();
        if(as){
        indice0 = num2;
        
        for(Booking reserva : lista_aux){
            if(reserva.isForDay((LocalDate) fecha_datePicker.getValue())){
                horas.add(reserva.getFromTime().getHour() - 9);
            }
        }
        
        num = 0;
        for(boolean hselec : hSelec){
            if(hselec && num != indice0){
                horas.add(num);
            }
            num++;
        }
        num = 0;
        horas.sort(Comparator.naturalOrder());
        
        for (int i = 0; i < horas.size(); i++){
            for (int j = i + 1; j < horas.size(); j++){
                if (horas.get(i) != null && horas.get(i).equals(horas.get(j))) {
                    res = false;
                }
            }
        }
        
        
        switch(indice0){
                case 0:
                    contador1 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == 0){
                            res = false;
                        }else if (hora1 == 1 || hora1 == 2){
                            contador1++;
                        }
                    }
                    if (contador1 >= 2) res = false;
                    break;
                case 1:
                    contador1 = 0;
                    contador2 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == 1){
                            res = false;
                        }else if (hora1 == 2){
                            contador1++;
                            contador2++;
                        }else if(hora1 == 0){
                            contador1++;
                        }else if(hora1 == 3){
                            contador2++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2) res = false;
                    break;
                case 2:
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == 2){
                            res = false;
                        }else if (hora1 == 1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == 0){
                            contador1++;
                        }else if(hora1 == 3){
                            contador2++;
                            contador3++;
                        }else if(hora1 == 4){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 3:
                    num = 3;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 4:
                    num = 4;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 5:
                    num = 5;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 6:
                    num = 6;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 7:
                    num = 7;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 8:
                    num = 8;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 9:
                    num = 9;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 10:
                    num = 10;
                    contador1 = 0;
                    contador2 = 0;
                    contador3 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                            contador3++;
                        }else if(hora1 == num+2){
                            contador3++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2 || contador3 >= 2) res = false;
                    break;
                case 11:
                    num = 11;
                    contador1 = 0;
                    contador2 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1){
                            contador1++;
                            contador2++;
                        }else if(hora1 == num-2){
                            contador1++;
                        }else if(hora1 == num+1){
                            contador2++;
                        }
                    }
                    if (contador1 >= 2 || contador2 >= 2) res = false;
                    break;
                case 12:
                    num = 12;
                    contador1 = 0;
                    for(Integer hora1 : horas){
                        if(hora1 == num){
                            res = false;
                        }else if (hora1 == num-1 || hora1 == num-2){
                            contador1++;
                        }
                    }
                    if (contador1 >= 2) res = false;
                    break;
            }
        }
        num2++;
        }
        return res;
    }
    
    private void horaSeleccionada(MouseEvent event) {
        //Primera
        ToggleButton hora_selec = botonSelec(event);
        //if(hora_selec.isSelected()){
            
        //}
        
        //if(hora_selec.isDisable()){
        //    //hora_selec.set .setDisable(false);
        //} else {
        //    hora_selec.setDisable(true);
        //}
    }
    
    //Codigo para detectar que boton esta presionado
    @FXML
    protected ToggleButton botonSelec(MouseEvent event){
        ToggleButton hora = null;
        int indice = 0;
        for(SelecHora b : arrayHoras){
            if (event.getSource().equals(b.b)){
                horas_seleccionadas[indice] = b.b.isSelected();
                for(boolean bb : horas_seleccionadas){
                    if(bb){
                        button_reservar.setDisable(false);
                        break;
                    }else{
                        button_reservar.setDisable(true);
                    }
                }
                return b.b;
            }
            indice++;
        }
        return hora;
    }
    
}
