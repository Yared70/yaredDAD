/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.Pelicula;
import es.iespuertodelacruz.ymp.model.PeliculaSingleton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yared
 */
public class FXMLPeliculasController implements Initializable {

    @FXML
    private ListView lvPeliculas;
    @FXML
    private TextField txtFiltrar;
    @FXML
    private Button btnAddPelicula;

    public GestorFichero gestorFichero;
    @FXML
    private ImageView btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarListaPeliculas();

    }

    private void cargarListaPeliculas() {

        gestorFichero = new GestorFichero();

        List<String> peliculasArchivo = gestorFichero.leerFichero();

        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        ObservableList<String> peliculasSimplificadas = FXCollections.observableArrayList();

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
            peliculasSimplificadas.add(pelicula.reduceInfo());

        });

        FilteredList<String> listaFiltradaPorNombre = new FilteredList<>(peliculasSimplificadas);

        lvPeliculas.setItems(listaFiltradaPorNombre);

        txtFiltrar.textProperty().addListener((listaPeliculas, nombreAnterior, nombreFiltrado) -> {

            listaFiltradaPorNombre.setPredicate(pelicula -> {

                if (pelicula.toLowerCase().contains(nombreFiltrado.toLowerCase())) {
                    return true;
                }

                return false;
            });

        });

        lvPeliculas.setOnMousePressed(event -> {

            if (event.isSecondaryButtonDown()) {

                int posicionPelicula = lvPeliculas.getSelectionModel().getSelectedIndex();
                Pelicula pelicula = peliculas.get(posicionPelicula);
                PeliculaSingleton peliculaSingleton = PeliculaSingleton.getInstance();
                peliculaSingleton.setPelicula(pelicula);

                ContextMenu contextMenu = new ContextMenu();

                MenuItem item1 = new MenuItem("Ver");
                item1.setOnAction(eventItem -> ver());

                MenuItem item2 = new MenuItem("Modificar");
                item2.setOnAction(eventItem -> modificar());

                MenuItem item3 = new MenuItem("Eliminar");
                item3.setOnAction(eventItem -> eliminar());

                MenuItem item4 = new MenuItem("Cancelar");
                item4.setOnAction(eventItem -> contextMenu.hide());

                contextMenu.getItems().addAll(item1, item2, item3, item4);

                lvPeliculas.setContextMenu(contextMenu);

            }
        });

        btnAddPelicula.setOnMouseClicked(event -> nuevaPelicula());

        btnCerrar.setOnMouseClicked(event -> cerrarVentana());

    }

    private void ver() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLPeliculaInfo.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLPeliculaInfoController controller = (FXMLPeliculaInfoController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();

    }

    private void modificar() {

        Stage stage = (Stage) btnAddPelicula.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLModificarPelicula.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLModificarPeliculaController controller = (FXMLModificarPeliculaController) loader.getController();
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
        if (result.get() == ButtonType.OK) {
            Pelicula pelicula = PeliculaSingleton.getInstance().getPelicula();

            List<String> leerFichero = gestorFichero.leerFichero();

            Iterator<String> iterator = leerFichero.iterator();
            while (iterator.hasNext()) {

                if (iterator.next().contains(pelicula.getTitle())) {
                    iterator.remove();
                }
            }

            String nuevoArchivo = "";
            for (String string : leerFichero) {
                nuevoArchivo += string + "\n";
            }

            gestorFichero.escribirFichero(nuevoArchivo);
            cargarListaPeliculas();
        }

    }

    private void nuevaPelicula() {

        Stage stage = (Stage) btnAddPelicula.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/ymp/view/FXMLCrearPelicula.fxml"));

        Parent newLayout = null;
        try {
            newLayout = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLCrearPeliculaController controller = (FXMLCrearPeliculaController) loader.getController();
        Scene newScene = new Scene(newLayout);
        Stage stageNew = new Stage();
        stageNew.setScene(newScene);

        //stage.setMaximized(true);
        stageNew.initStyle(StageStyle.UNDECORATED);
        stageNew.show();

    }

    private void cerrarVentana() {

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
