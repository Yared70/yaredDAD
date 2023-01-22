/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.Ingrediente;
import es.iespuertodelacruz.ymp.model.Receta;
import es.iespuertodelacruz.ymp.model.RecetaSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author yared
 */
public class FXMLRecetaInfoController implements Initializable {

    String manolo;
    
    @FXML
    private ImageView imgReceta;
    @FXML
    private ListView<String> lvReceta;
    @FXML
    private ImageView ivSalir;
    @FXML
    private ImageView ivCerrar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarReceta();
    } 
    
    
    public void mostrarReceta(){
        
        ObservableList<String> recetaView = FXCollections.observableArrayList();
        Receta receta = RecetaSingleton.getInstance().getReceta();
        
        recetaView.add("Nombre: \t\t\t\t" + receta.getTitulo());
        recetaView.add("Autor: \t\t\t\t" + receta.getAutor());
        recetaView.add("Tipo: \t\t\t\t" + receta.getTipo());
        recetaView.add("Ingredientes: ");
        for (Ingrediente ingrediente : receta.getIngredientes()) {
            recetaView.add("\t\t\t\t\t" + ingrediente.getNombre());
        }
        recetaView.add("Pasos: ");
        receta.getPasos().forEach( paso -> {
            recetaView.add("\t\t\t\t\t" + paso.getDescripcion());
        });
        recetaView.add("Comensales: \t\t\t" + receta.getNumeroComensales());
        recetaView.add("Tiempo: \t\t\t\t" + receta.getTiempoPreparacion() + " minutos");
        recetaView.add("Calorias: \t\t\t\t" + receta.getCalorias());
        
        lvReceta.setItems(recetaView);

        Image imagen2 = null;
        try{
            
         imagen2 = new Image("/es/iespuertodelacruz/ymp/assets/" + receta.getImagenPath());
            
        }catch(Exception ex){
            imagen2 = new Image(receta.getImagenPath());
        }
        
        imgReceta.setImage(imagen2);
        
         ivCerrar.setOnMouseClicked(event -> cerrarAplicacion());
         ivSalir.setOnMouseClicked(event -> salir());
        
    }
    
    private void cerrarAplicacion() {
       
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("¿Estás seguro de que quieres salir de la aplicación?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
        
    }
    
    private void salir() {
       
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Ventana");
        alert.setHeaderText("¿Estás seguro de que quieres volver a la ventana principal?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            
            Stage stage = (Stage)ivCerrar.getScene().getWindow();
            stage.close();
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLDocument.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLDocumentController controller = (FXMLDocumentController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();
            
        }
        
    }
    
}
