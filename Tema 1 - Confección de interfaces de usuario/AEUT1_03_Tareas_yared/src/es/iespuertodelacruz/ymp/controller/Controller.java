/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.GestorTarea;
import es.iespuertodelacruz.ymp.model.Tarea;
import es.iespuertodelacruz.ymp.view.View;
import es.iespuertodelacruz.ymp.view.ViewHistorial;
import es.iespuertodelacruz.ymp.view.ViewModTarea;
import es.iespuertodelacruz.ymp.view.ViewTarea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yared
 */
public class Controller implements ActionListener {

    GestorTarea gt;
    GestorFichero gf;
    View vista;
    ViewHistorial historial;
    ViewTarea tareas;
    ViewModTarea viewModTarea;

    public Controller(View vista) {

        gt = new GestorTarea();
        gf = new GestorFichero();
        this.vista = vista;
        this.tareas = new ViewTarea(vista, true);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnHistorial.addActionListener(this);
        this.tareas.btnCrearTarea.addActionListener(this);
        this.vista.menuAbrir.addActionListener(this);
        this.vista.menuGuardar.addActionListener(this);
        this.viewModTarea = new ViewModTarea(vista, true);


        vista.tableInfo.setFocusable(true);
        vista.tableInfo.setDefaultEditor(Object.class, null);

        this.vista.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                rellenarTabla();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        this.vista.tableInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    modTarea();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        this.vista.btnCompletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                completarTarea();
            }
       
            
            
        });
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vista.btnAgregar) {
            crearTarea();
        } else if (e.getSource() == this.vista.btnHistorial) {
            verHistorial();
        } else if (e.getSource() == this.tareas.btnCrearTarea) {
            addTarea();
        } else if (e.getSource() == this.vista.menuAbrir) {
            abrirArchivo();
        } else if (e.getSource() == this.vista.menuGuardar) {
            guardarArchivo();
        }

    }

    private void crearTarea() {

        tareas.setVisible(true);

    }

    private void addTarea() {

        if (tareas.txpDescripcion.getText().length() > 1) {
            String tarea = tareas.txpDescripcion.getText();
            Date fecha = (Date) tareas.spinFecha.getValue();
            gt.addTarea(new Tarea(tarea, fecha));
            tareas.txpDescripcion.setText("");
            JOptionPane.showMessageDialog(tareas, "Tarea creada correctamente", "Crear tarea", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(tareas, "Error. Introduce un nombre para la tarea", "Crear tarea", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void verHistorial() {
        ViewHistorial vistaHistorial = new ViewHistorial(vista, true);

        vistaHistorial.txaHistorial.setText(gt.showHistorial());
        vistaHistorial.setVisible(true);

    }

    private void abrirArchivo() {

        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);
        File archivo = fileChooser.getSelectedFile();
        GestorTarea gestorTareas = gf.leerFichero(archivo);
        this.gt = gestorTareas;
        

    }

    private void guardarArchivo() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Guardar como...");
        fileChooser.showOpenDialog(null);
        File archivo = new File(fileChooser.getSelectedFile() + "");
        gf.escribirFichero(archivo, gt);

    }

    public void modTarea() {

        Object valueAt;
        Tarea findTask;
        int selectedRow = vista.tableInfo.getSelectedRow();
        int selectedcolumn = vista.tableInfo.getSelectedColumn();
        if(selectedcolumn == 0){
            valueAt = vista.tableInfo.getValueAt(selectedRow, selectedcolumn);
            findTask = gt.findTask(valueAt.toString());
            
        }else{
            valueAt = vista.tableInfo.getValueAt(selectedRow, selectedcolumn);
            System.out.println(valueAt.toString());
            findTask = gt.findTaskDate(valueAt.toString());
            
            
        }
        
        viewModTarea.lblTarea.setText(findTask.toString());
            viewModTarea.txfDesc.setText(findTask.getDescription());
            viewModTarea.spinFechaMod.setValue(findTask.getDate());

        
        viewModTarea.btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gt.modTarea(findTask, viewModTarea.txfDesc.getText(),
                        (Date)viewModTarea.spinFechaMod.getValue());
                viewModTarea.setVisible(false);
            }

           
               
            });
            
        viewModTarea.btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gt.eliminarTarea(findTask.getDescription());
                viewModTarea.setVisible(false);
            }

           
               
            });
        
        
        viewModTarea.setVisible(true);

    }
    
    public void rellenarTabla(){
        
        String nombresColumnas[] = {"Descripcion", "Fecha"};
                String datos[][] = new String[gt.tareasSinFinalizar().size()][2];
                int i = 0;
                int j = 0;
                for (Tarea tarea : gt.tareasSinFinalizar()) {
                    datos[i][j] = tarea.getDescription();
                    j += 1;
                    datos[i][j] = tarea.getFormatDate();
                    j = 0;
                    i += 1;
                }
                DefaultTableModel model = new DefaultTableModel(datos, nombresColumnas);

                vista.tableInfo.setModel(model);
        
    }
    
    public void completarTarea(){
        
        int[] selectedColumns = vista.tableInfo.getSelectedColumns();
        int[] selectedRows = vista.tableInfo.getSelectedRows();
        
        for (int i = 0; i < selectedRows.length; i++) {
            
            Object valueAt = vista.tableInfo.getValueAt(i, 0);
            Tarea findTask = gt.findTask(valueAt.toString());
            gt.completarTarea(findTask);
            
            
        }
        
        rellenarTabla();
        
    }
    
    
    
    

}
