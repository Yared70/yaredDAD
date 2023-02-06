/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.Pelicula;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yared
 */
public class FXMLEstadisticasController implements Initializable {

    @FXML
    private PieChart chartDate;
    @FXML
    private ImageView btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generarGrafico();
    }    
    
    public void generarGrafico(){
        
        
        GestorFichero gestorFichero = new GestorFichero();

        List<String> peliculasArchivo = gestorFichero.leerFichero();

        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        

        peliculasArchivo.forEach(peliculaFE -> {

            String[] peliculaSplit = peliculaFE.split(";");

            Pelicula pelicula = new Pelicula(Integer.parseInt(peliculaSplit[0]),
                    peliculaSplit[1],
                    peliculaSplit[2],
                    Integer.parseInt(peliculaSplit[3]),
                    peliculaSplit[4],
                    peliculaSplit[5]);

            System.out.println(pelicula.toString());

            peliculas.add(pelicula);
           

        });
        
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
        for (Pelicula pelicula : peliculas) {
            String[] año = pelicula.getRelease_date().split("-");
            PieChart.Data data = new PieChart.Data(año[0], Integer.parseInt(año[0]));
            datos.add(data);
        }
        chartDate.setData(datos);
        
        btnCerrar.setOnMouseClicked( event -> cerrar());
    }

    private void cerrar() {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Ventana");
        alert.setHeaderText("¿Quieres cerrar la aplicacion o volver a la ventana principal?");

        alert.getButtonTypes().clear();
        ButtonType btnPrincipal = new ButtonType("Principal");
        ButtonType btnCancelar = new ButtonType("Cancelar");
        ButtonType btnSalir = new ButtonType("Salir");
        alert.getButtonTypes().addAll(btnPrincipal, btnCancelar, btnSalir);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnPrincipal) {

            Stage stage = (Stage) btnCerrar.getScene().getWindow();
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

        } else if (result.get() == btnSalir) {

            Platform.exit();
        }

    }
    
}
