/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import javafx.scene.image.Image;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;


public class JavaFXMLApplication extends Application {
    private static Scene scene;
    Club club;
    
    
   
     public static void setRoot(Parent root)throws IOException{
        scene.setRoot(root);
    }
   
    
/*
    public JavaFXMLApplication() throws ClubDAOException, IOException {
        this.club = Club.getInstance();
    }
    */
    @Override
    public void start(Stage stage) throws ClubDAOException, IOException {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Parent root = loader.load();
        
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena

        //Prueba de creación miembro y reeserva NO TOCAR LA LINEA 77 .

        

        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
       //     - se muestra el stage de manera no modal mediante el metodo show()
        scene = new Scene(root);
        //club = Club.getInstance();
        //club.setInitialData();
       // setRoot("Principal");
        stage.setScene(scene);
        stage.setTitle("GreenBall");
        stage.setMinWidth(820);
        stage.setMinHeight(650);
        //stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
                launch(args);
    }
    
     
  
   private static  Image imagen;
   private static Member member;


    
    
    public static Image getSharedImage() {
        return imagen;
    }

    public static void setSharedImage(Image imagen1) {
        imagen = imagen1;
    }
    
     public static Member getMember() {
        return member;
    }

    public static void setMember(Member member1) {
       member=member1;
    }
} 
    

