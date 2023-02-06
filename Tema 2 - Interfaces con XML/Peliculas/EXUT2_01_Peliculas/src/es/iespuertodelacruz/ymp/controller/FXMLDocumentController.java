/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Yared
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnPrincipal;
    @FXML
    private Button btnPeliculas;
    @FXML
    private Button btnEstadisticas;
    @FXML
    private ImageView ivMarvel;
    @FXML
    private ImageView btnSalir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnPrincipal.setOnMouseClicked( event -> { ocultarVerLogo(); } );
        
        btnPeliculas.setOnMouseClicked( event -> { verPeliculas(); });
        
        btnEstadisticas.setOnMouseClicked( event -> { verEstadisticas(); } );
        
        btnSalir.setOnMouseClicked( event -> cerrarAplicacion());
        
    }    

    private void ocultarVerLogo() {
        
        if( ivMarvel.visibleProperty().get() ){
           
            ivMarvel.setVisible(false);
       
        }else{
           
            ivMarvel.setVisible(true);
            
        }
        
    }

    private void verPeliculas() {
        
        Stage stage = (Stage) btnPrincipal.getScene().getWindow();
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

    private void verEstadisticas() {
        
        Stage stage = (Stage) btnPrincipal.getScene().getWindow();
        stage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLEstadisticas.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLEstadisticasController controller = (FXMLEstadisticasController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();
        
        
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
    
}
