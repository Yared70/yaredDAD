/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.Pelicula;
import es.iespuertodelacruz.ymp.model.PeliculaSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yared
 */
public class FXMLPeliculaInfoController implements Initializable {

    @FXML
    private ListView lvPelicula;
    @FXML
    private ImageView ivPelicula;
    @FXML
    private ImageView btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarPelicula();
        
    }    

    private void mostrarPelicula() {
        
        
        ObservableList<String> peliculaView = FXCollections.observableArrayList();
        Pelicula pelicula = PeliculaSingleton.getInstance().getPelicula();
        
        peliculaView.add("Informacion de la pelicula");
        peliculaView.add("Id: \t\t\t\t\t" + pelicula.getId());
        peliculaView.add("Titulo: \t\t\t\t" + pelicula.getTitle());
        peliculaView.add("Fecha lanzamiento: \t\t" + pelicula.getRelease_date());
        peliculaView.add("Duracion: \t\t\t" + pelicula.getDuracion());
        peliculaView.add("Descripcion: \t\t\t" + pelicula.getDescripcion());
        
        lvPelicula.setItems(peliculaView);
        
        Image imagen = null;
        try{
            
         imagen = new Image("/es/iespuertodelacruz/ymp/assets/" + pelicula.getCover_url());
            
        }catch(Exception ex){
            imagen = new Image(pelicula.getCover_url());
        }
        
        ivPelicula.setImage(imagen);
        
        btnCerrar.setOnMouseClicked( event -> salir());
        
        
    }

    private void salir() {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Ventana");
        alert.setHeaderText("¿Estás seguro de que quieres cerrar la ventana?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            
            Stage stage = (Stage)btnCerrar.getScene().getWindow();
            stage.close();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLPeliculas.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLPeliculasController controller = (FXMLPeliculasController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();
            
        }
    }
    
}
