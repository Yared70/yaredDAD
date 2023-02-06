/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.UsuarioDTV;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Angeles
 */
public class FXMLUsuariosController implements Initializable {

    @FXML
    private TableView<UsuarioDTV> tblUsuarios;
    @FXML
    private TableColumn<UsuarioDTV, String> clmNombre;
    @FXML
    private TableColumn<UsuarioDTV, String> clmPass;
    @FXML
    private TableColumn<UsuarioDTV, String> clmRol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        rellenarTabla();
    }    
    
    
    public void rellenarTabla(){
        
        
        clmNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        clmPass.setCellValueFactory(new PropertyValueFactory<>("Pass"));
        clmRol.setCellValueFactory(new PropertyValueFactory<>("Rol"));

        List<UsuarioDTV> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioDTV(new SimpleStringProperty("admin"), new SimpleStringProperty("1234"), new SimpleStringProperty("admin")));
        usuarios.add(new UsuarioDTV(new SimpleStringProperty("juan"), new SimpleStringProperty("pass"), new SimpleStringProperty("user")));
        ObservableList<UsuarioDTV> usersData = FXCollections.observableArrayList(usuarios);
        System.out.println(usersData);
        
        clmNombre.addEventHandler(EventType.ROOT, event -> {System.out.println(clmNombre.cellValueFactoryProperty());});
        
        tblUsuarios.setItems(usersData);
        
        
        
    }
    
}
