/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.Ingrediente;
import es.iespuertodelacruz.ymp.model.Paso;
import es.iespuertodelacruz.ymp.model.Receta;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author yared
 */
public class FXMLEstadisticasController implements Initializable {

    @FXML
    private BarChart<String, Integer> bcTipos;
    @FXML
    private PieChart pcCalorias;
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
        generarGraficos();
    }

    public void generarGraficos() {

        GestorFichero gestorFichero = new GestorFichero();
        List<Receta> recetas = new ArrayList<>();

        List<String> recetasStr = gestorFichero.leerFichero();
        recetasStr.forEach(recetaStr -> {

            String[] recetaSplit = recetaStr.split(";");
            String titulo = recetaSplit[0];
            String autor = recetaSplit[1];
            String tipo = recetaSplit[2];
            List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            List<Paso> pasos = new ArrayList<Paso>();
            String[] ingredientesStr = recetaSplit[3].split(",");
            for (String ingrediente : ingredientesStr) {
                ingredientes.add(new Ingrediente(ingrediente));
            }
            String[] pasosStr = recetaSplit[4].split(",");
            for (String paso : pasosStr) {
                pasos.add(new Paso(paso));
            }
            Integer numComensales = Integer.parseInt(recetaSplit[5]);
            Integer tiempo = Integer.parseInt(recetaSplit[6]);
            Integer calorias = Integer.parseInt(recetaSplit[7]);
            String imagePath = recetaSplit[8];

            Receta receta = new Receta(titulo, autor, tipo, ingredientes, pasos, numComensales, tiempo, calorias, imagePath);
            recetas.add(receta);

        });

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        Map<String, Integer> mapTiposRecetas = new HashMap<>();

        for (Receta receta : recetas) {
            String tipo = receta.getTipo();
            if (mapTiposRecetas.containsKey(tipo)) {
                mapTiposRecetas.put(tipo, mapTiposRecetas.get(tipo) + 1);
            } else {
                mapTiposRecetas.put(tipo, 1);
            }
        }

        series.getData().add(new XYChart.Data<>("Entrante", mapTiposRecetas.get("Entrante")));
        series.getData().add(new XYChart.Data<>("Primero", mapTiposRecetas.get("Primero")));
        series.getData().add(new XYChart.Data<>("Segundo", mapTiposRecetas.get("Segundo")));
        series.getData().add(new XYChart.Data<>("Postre", mapTiposRecetas.get("Postre")));
        bcTipos.setStyle("-fx-background-color:  #042630;");
        bcTipos.getXAxis().setTickLabelFill(Color.WHITE);
        bcTipos.getYAxis().setTickLabelFill(Color.WHITE);
        bcTipos.getData().add(series);

        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
        for (Receta receta : recetas) {
            PieChart.Data data = new PieChart.Data(receta.getTitulo() + ": " + receta.getCalorias(), receta.getCalorias());
            datos.add(data);
        }
        pcCalorias.setData(datos);
        
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
