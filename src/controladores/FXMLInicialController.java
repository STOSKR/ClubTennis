/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
