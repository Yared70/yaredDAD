/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.Pelicula;
import es.iespuertodelacruz.ymp.model.PeliculaSingleton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author Yared
 */
public class FXMLModificarPeliculaController implements Initializable {

    @FXML
    private Label lblModo;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private TextField txtTitulo;
    @FXML
    private Spinner<Integer> spnDuracion;
    @FXML
    private ImageView ivImagen;
    @FXML
    private Label lblImagen;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private ImageView btnCerrar;
    @FXML
    private Button btnCrear;

    String imagePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Pelicula pelicula = PeliculaSingleton.getInstance().getPelicula();

        txtTitulo.setText(pelicula.getTitle());
        lblModo.setText("Modificar: " + pelicula.getTitle());

        txtDescripcion.setText(pelicula.getDescripcion());

        Image imagen = null;
        try {

            imagen = new Image("/es/iespuertodelacruz/ymp/assets/" + pelicula.getCover_url());

        } catch (Exception ex) {
            imagen = new Image(pelicula.getCover_url());
        }

        ivImagen.setImage(imagen);

        spnDuracion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, pelicula.getDuracion()));
        spnDuracion.setEditable(true);

        ivImagen.setOnMouseClicked(event -> elegirImagen());
        btnCerrar.setOnMouseClicked(event -> cerrar());
        lblImagen.setOnMouseClicked(event -> elegirImagen());

        btnCrear.setOnMouseClicked(event -> Modificar());

    }

    private void Modificar() {

        String titulo = txtTitulo.getText();
        String fecha = PeliculaSingleton.getInstance().getPelicula().getRelease_date();
        Integer duracion = spnDuracion.getValue();
        String descripcion = txtDescripcion.getText();

        if (titulo == null || titulo.length() < 1
                || duracion == null || duracion < 1
                || imagePath == null || imagePath.length() < 1
                || descripcion == null || descripcion.length() < 1) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear la pelicula");
            alert.setHeaderText("No puedes crear una pelicula sin titulo, duracion, descripcion o imagen");
            alert.showAndWait();

        } else {

            GestorFichero gf = new GestorFichero();

            Pelicula pelicula = PeliculaSingleton.getInstance().getPelicula();

            List<String> leerFichero = gf.leerFichero();
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
            gf.escribirFichero(nuevoArchivo);

            List<String> peliculasFichero = gf.leerFichero();

            String[] idSplit = peliculasFichero.get(peliculasFichero.size() - 1).split(";");
            Integer id = Integer.parseInt(idSplit[0]);

            Pelicula peliculaMod = new Pelicula((id + 1), titulo, fecha, duracion, imagePath, descripcion);
            peliculasFichero.add(peliculaMod.getPeliculaFile());

            nuevoArchivo = "";

            for (String string : peliculasFichero) {
                nuevoArchivo += string + "\n";
            }

            gf.escribirFichero(nuevoArchivo);

            Stage stage = (Stage) btnCerrar.getScene().getWindow();
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

    private void elegirImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la imagen");

        File imagenSeleccionada = fileChooser.showOpenDialog(null);

        if (imagenSeleccionada != null) {

            imagePath = imagenSeleccionada.toURI().toString();
            System.out.println(imagePath);
            ivImagen.setImage(new Image(imagePath));
        }

    }

    private void cerrar() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Ventana");
        alert.setHeaderText("¿Estás seguro de que quieres cerrar la ventana?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Stage stage = (Stage) btnCerrar.getScene().getWindow();
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
