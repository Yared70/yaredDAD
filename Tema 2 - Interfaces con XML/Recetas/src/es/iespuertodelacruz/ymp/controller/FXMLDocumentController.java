package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.Ingrediente;
import es.iespuertodelacruz.ymp.model.Paso;
import es.iespuertodelacruz.ymp.model.Receta;
import es.iespuertodelacruz.ymp.model.RecetaSingleton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {

    public Button btnPrincipal;
    public Button btnInfo;
    public Button btnSearch;
    public Button btnStats;
    public Button btnAdmin;
    public Button btnNotes;
    public AnchorPane panePanel;
    public ListView lvRecetas;

    public TextField txtFiltrar;

    GestorFichero gestorFichero;
    @FXML
    private ImageView iconHelp;
    @FXML
    private Label lblInfo;
    @FXML
    private ImageView imgNew;
    @FXML
    private Button btnNew;
    @FXML
    private ImageView ivCerrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        generarRecetas();
        

    }

    public void generarRecetas() {


        gestorFichero = new GestorFichero();
        ObservableList<String> recetasSimplificadas = FXCollections.observableArrayList();
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
            recetasSimplificadas.add(receta.getRecetaSimplificada());

        });

        recetasSimplificadas.forEach(receta -> {
            System.out.println(receta);
        });
        FilteredList<String> listaFiltradaPorNombre = new FilteredList<>(recetasSimplificadas);

        lvRecetas.setItems(listaFiltradaPorNombre);

        txtFiltrar.textProperty().addListener((listaRecetas, nombreAnterior, nombreFiltrado) -> {

            listaFiltradaPorNombre.setPredicate(receta -> {

                if (receta.toLowerCase().contains(nombreFiltrado.toLowerCase())) {
                    return true;
                }

                return false;
            });

        });

        lvRecetas.setOnMousePressed(event -> {

            if (event.isSecondaryButtonDown()) {

                int posicionReceta = lvRecetas.getSelectionModel().getSelectedIndex();
                Receta receta = recetas.get(posicionReceta);
                RecetaSingleton recetaSingleton = RecetaSingleton.getInstance();
                recetaSingleton.setReceta(receta);

                ContextMenu contextMenu = new ContextMenu();

                MenuItem item1 = new MenuItem("Ver");
                item1.setStyle("-fx-text-fill: #d0d6d6;");
                item1.setOnAction(eventItem -> ver());

                MenuItem item2 = new MenuItem("Modificar");
                item2.setStyle("-fx-text-fill: #d0d6d6;");
                item2.setOnAction(eventItem -> modificar());

                MenuItem item3 = new MenuItem("Eliminar");
                item3.setStyle("-fx-text-fill: #d0d6d6;");
                item3.setOnAction(eventItem -> eliminar());

                MenuItem item4 = new MenuItem("Cancelar");
                item4.setStyle("-fx-text-fill: #d0d6d6;");
                item4.setOnAction(eventItem -> contextMenu.hide());
                
                contextMenu.getItems().addAll(item1, item2, item3, item4);

                contextMenu.setStyle("-fx-background-color: #042630;");

                lvRecetas.setContextMenu(contextMenu);

            }
        });
      
        iconHelp.setOnMouseEntered(event -> {
            lblInfo.setVisible(true);
        });
        iconHelp.setOnMouseExited(event -> {
            lblInfo.setVisible(false);
        });
        
        imgNew.setOnMouseClicked(event -> nuevaReceta());
        btnNew.setOnMouseClicked(event -> nuevaReceta());
        
        btnStats.setOnMouseClicked(event -> verStats());

        ivCerrar.setOnMouseClicked(event -> cerrarAplicacion());
 
        
    }

    private void modificar() {
       
         Stage stage2 = (Stage) btnPrincipal.getScene().getWindow();
        stage2.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLModificarReceta.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLModificarRecetaController controller = (FXMLModificarRecetaController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();
        
        
    }

    private void eliminar() {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar receta");
        alert.setHeaderText("¿Estás seguro de que quieres eliminar la receta?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           Receta receta = RecetaSingleton.getInstance().getReceta();
        
        List<String> leerFichero = gestorFichero.leerFichero();
        
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
        
        gestorFichero.escribirFichero(nuevoArchivo);
        generarRecetas();
        }
        
        
            
    }

    private void ver() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLRecetaInfo.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLRecetaInfoController controller = (FXMLRecetaInfoController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();

    }

    private void nuevaReceta() {
       
        Stage stage2 = (Stage) btnPrincipal.getScene().getWindow();
        stage2.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLNuevaReceta.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLNuevaRecetaController controller = (FXMLNuevaRecetaController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();
        
    }

    private void verStats() {
        
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
