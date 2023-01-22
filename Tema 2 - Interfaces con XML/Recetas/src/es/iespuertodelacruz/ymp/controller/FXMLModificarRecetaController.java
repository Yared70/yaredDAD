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
import es.iespuertodelacruz.ymp.model.RecetaSingleton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author yared
 */
public class FXMLModificarRecetaController implements Initializable {

    @FXML
    private ImageView ivImagen;
    @FXML
    private Button btnImagen;
    @FXML
    private TextField txtTitulo;
    @FXML
    private ComboBox<String> cbAutor;
    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private Slider sliComensales;
    @FXML
    private Spinner<Integer> spinTiempo;
    @FXML
    private Spinner<Integer> spinCalorias;
    @FXML
    private TextArea txtIngredientes;
    @FXML
    private TextArea txtPasos;
    @FXML
    private Button btnAdd;
    
    String imagePath;
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
        modificarReceta();
    }    
    
    
    public void modificarReceta(){
        
        spinTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, 1));
        spinTiempo.setEditable(true);
        spinCalorias.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, 1));
        spinCalorias.setEditable(true);
        
        btnAdd.setOnMouseClicked(event -> crearReceta());
        btnImagen.setOnMouseClicked(event -> addImagen());
        ivImagen.setOnMouseClicked(event -> addImagen());
        cbAutor.getItems().add("juan");
        cbAutor.getItems().add("admin");
        cbAutor.getItems().add("laura");
        cbAutor.getItems().add("yaiza");
        cbTipo.getItems().add("Entrante");
        cbTipo.getItems().add("Primero");
        cbTipo.getItems().add("Segundo");
        cbTipo.getItems().add("Postre");
        
        Receta receta = RecetaSingleton.getInstance().getReceta();
        
         Image imagen2 = null;
        try{
            
         imagen2 = new Image("/es/iespuertodelacruz/ymp/assets/" + receta.getImagenPath());
            
        }catch(Exception ex){
            imagen2 = new Image(receta.getImagenPath());
        }
        
        
        
        ivImagen.setImage(imagen2);
        txtTitulo.setText(receta.getTitulo());
        txtIngredientes.setText(receta.getIngredientesView());
        txtPasos.setText(receta.getPasosView());
        cbAutor.setValue(receta.getAutor());
        cbTipo.setValue(receta.getTipo());
        sliComensales.setValue((double)receta.getNumeroComensales());
        spinCalorias.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, receta.getCalorias()));
        spinTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, receta.getTiempoPreparacion()));
        imagePath = receta.getImagenPath();
        
         ivCerrar.setOnMouseClicked(event -> cerrarAplicacion());
         ivSalir.setOnMouseClicked(event -> salir());
        
        
    }

    private void crearReceta() {
       
        String nombre = txtTitulo.getText();
        String autor = cbAutor.getValue();
        String tipo = cbTipo.getValue();
        String ingredientesStr = txtIngredientes.getText();
        String[] ingredientesSplit = ingredientesStr.split("\n");
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        for (String ingredienteStr : ingredientesSplit) {
            ingredientes.add(new Ingrediente(ingredienteStr));
        }
        String pasosStr = txtPasos.getText();
        String[] pasosSplit = pasosStr.split("\n");
        List<Paso> pasos = new ArrayList<>();
        for (String paso : pasosSplit) {
            pasos.add(new Paso(paso));
        }
        Integer comensales = (int)sliComensales.getValue();
        Integer duracion = spinTiempo.getValue();
        Integer calorias = spinCalorias.getValue();
        String image = imagePath;
        
        if( nombre == null || nombre.length() < 1 || 
                ingredientesStr == null || ingredientesStr.length() < 1 ||
                pasosStr == null || pasosStr.length() < 1 ||
                imagePath == null || imagePath.length() < 1){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear la receta");
            alert.setHeaderText("No puedes crear una receta sin titulo, autor, pasos o ingredientes");
            alert.showAndWait();
            
        }else{
          
            GestorFichero gf = new GestorFichero();
            
             Receta receta = RecetaSingleton.getInstance().getReceta();

            List<String> leerFichero = gf.leerFichero();
            Iterator<String> iterator = leerFichero.iterator();
            while(iterator.hasNext()){

                if(iterator.next().contains(receta.getTitulo())){
                    iterator.remove();
                }  
            }
            String nuevoArchivo = "";
            for (String string : leerFichero) {
                nuevoArchivo += string + "\n";
            }
            gf.escribirFichero(nuevoArchivo);
            

            List<String> leerFichero2 = gf.leerFichero();
            System.out.println(leerFichero2);
            Receta recetaNew = new Receta(nombre, autor, tipo, ingredientes, pasos, comensales, duracion, calorias, imagePath);
            leerFichero2.add(recetaNew.getRecetaFileFormat());
            String nuevoArchivo2 = "";
            for (String string : leerFichero2) {
                nuevoArchivo2 += string + "\n";
            }
            System.out.println(nuevoArchivo2);
            gf.escribirFichero(nuevoArchivo2);
            
            Stage stage = (Stage)btnAdd.getScene().getWindow();
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

    private void addImagen() {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la imagen");
        /*
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        */
        File imagenSeleccionada = fileChooser.showOpenDialog(null);

        if(imagenSeleccionada != null){
            
            imagePath = imagenSeleccionada.toURI().toString();
            System.out.println(imagePath);
            ivImagen.setImage(new Image(imagePath));

        }

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
