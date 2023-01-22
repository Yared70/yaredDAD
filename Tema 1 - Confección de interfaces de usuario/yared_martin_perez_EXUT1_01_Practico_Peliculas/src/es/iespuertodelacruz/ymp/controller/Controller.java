/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.model.GestorFichero;
import es.iespuertodelacruz.ymp.model.GestorPeliculas;
import es.iespuertodelacruz.ymp.model.Pelicula;
import es.iespuertodelacruz.ymp.view.View;
import es.iespuertodelacruz.ymp.view.ViewAdd;
import es.iespuertodelacruz.ymp.view.ViewModificar;
import es.iespuertodelacruz.ymp.model.Actor;
import es.iespuertodelacruz.ymp.model.Premio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yared
 */
public class Controller implements ActionListener {

    View vistaPrincipal;
    ViewAdd vistaAdd;
    ViewModificar vistaModificar;
    GestorFichero gf;
    GestorPeliculas gp;

    public Controller(View vista) {
        this.vistaPrincipal = vista;
        this.vistaAdd = new ViewAdd(vista, true);
        this.vistaModificar = new ViewModificar(vista, true);
        this.gf = new GestorFichero();
        this.gp = new GestorPeliculas();

        this.vistaPrincipal.btnAdd.addActionListener(this);
        this.vistaPrincipal.btnModificar.addActionListener(this);
        this.vistaPrincipal.btnEliminar.addActionListener(this);
        this.vistaPrincipal.btnCerrar.addActionListener(this);

        vistaPrincipal.tblPeliculas.setFocusable(true);
        vistaPrincipal.tblPeliculas.setDefaultEditor(Object.class, null);

        vistaPrincipal.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                rellenarTabla();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
            }
        });
        
        leerArchivo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaPrincipal.btnAdd) {
            addPelicula();
        } else if (e.getSource() == this.vistaPrincipal.btnModificar) {
            Object valueAt = vistaPrincipal.tblPeliculas.getValueAt(vistaPrincipal.tblPeliculas.getSelectedRow(),
                    0);

            Pelicula findPelicula = gp.findPelicula(valueAt.toString());
           
            modificarPelicula(findPelicula);

        } else if (e.getSource() == this.vistaPrincipal.btnEliminar) {
            eliminarPelicula();
        } else if (e.getSource() == this.vistaPrincipal.btnCerrar) {
            cerrarAplicacion();
        }
    }

    private void addPelicula() {

        this.vistaAdd = new ViewAdd(vistaPrincipal, true);

        this.vistaAdd.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (vistaAdd.txtTitulo.getText().length() > 1) {
                    vistaAdd.txtMinutos.setEnabled(true);
                } else {
                    vistaAdd.txtMinutos.setEnabled(false);

                }
            }
        });

        this.vistaAdd.btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Actor> actores = new ArrayList<Actor>();
                ArrayList<Premio> premios = new ArrayList<Premio>();
                String titulo = vistaAdd.txtTitulo.getText();
                String director = vistaAdd.boxDirector.getSelectedItem().toString();
                String[] split = vistaAdd.txaActores.getText().split("\n");
                for (String string : split) {
                    actores.add(new Actor(string));
                }
                String[] split1 = vistaAdd.txaPremios.getText().split("\n");
                for (String string : split1) {
                    premios.add(new Premio(string));
                }
                int numeroSalas = vistaAdd.sliSalas.getValue();
                String duracion = vistaAdd.txtMinutos.getText();
                gp.addPelicula(new Pelicula(titulo, director, actores, premios, numeroSalas, duracion));
                vistaAdd.setVisible(false);
               
               
            }

        });

        this.vistaAdd.btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaAdd.sliSalas.setValue(6);
                vistaAdd.txaActores.setText("");
                vistaAdd.txaPremios.setText("");
                vistaAdd.txtMinutos.setText("");
                vistaAdd.txtTitulo.setText("");

            }

        });

        this.vistaAdd.setVisible(true);

    }

    private void modificarPelicula(Pelicula findPelicula) {

        
        this.vistaModificar = new ViewModificar(vistaPrincipal, true);

        this.vistaModificar.txtTitulo.setText(findPelicula.getTitulo());
        this.vistaModificar.txaActores.setText(findPelicula.getActoresStr());
        this.vistaModificar.txtMinutos.setText(findPelicula.getDuracion());
        this.vistaModificar.txaPremios.setText(findPelicula.getPremiosStr());
        this.vistaModificar.sliSalas.setValue(findPelicula.getNumeroSalas());
        this.vistaModificar.boxDirector.setSelectedItem(findPelicula.getDirector());

        this.vistaModificar.btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Actor> actores = new ArrayList<Actor>();
                ArrayList<Premio> premios = new ArrayList<Premio>();
                String titulo = vistaModificar.txtTitulo.getText();
                String director = vistaModificar.boxDirector.getSelectedItem().toString();
                String[] split = vistaModificar.txaActores.getText().split("\n");
                for (String string : split) {
                    actores.add(new Actor(string));
                }
                String[] split1 = vistaModificar.txaPremios.getText().split("\n");
                for (String string : split1) {
                    premios.add(new Premio(string));
                }
                int numeroSalas = vistaModificar.sliSalas.getValue();
                String duracion = vistaModificar.txtMinutos.getText();
                
                gp.modPelicula(findPelicula, new Pelicula(titulo, director, actores, premios, numeroSalas, duracion));

                vistaModificar.setVisible(false);


            }

        });

        this.vistaModificar.btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaAdd.sliSalas.setValue(6);
                vistaAdd.txaActores.setText("");
                vistaAdd.txaPremios.setText("");
                vistaAdd.txtMinutos.setText("");
                vistaAdd.txtTitulo.setText("");

            }

        });

        this.vistaModificar.setVisible(true);

    }

    private void eliminarPelicula() {

        Object valueAt = this.vistaPrincipal.tblPeliculas.getValueAt(vistaPrincipal.tblPeliculas.getSelectedRow(),
                vistaPrincipal.tblPeliculas.getSelectedColumn());
        Pelicula findPelicula = gp.findPelicula(valueAt.toString());
        gp.eliminarPelicula(findPelicula);
        rellenarTabla();
    }

    private void cerrarAplicacion() {

        ArrayList<Pelicula> peliculas = gp.getPeliculas();
        String strPeliculas = "";
        for (Pelicula pelicula : peliculas) {
            strPeliculas += pelicula.getTitulo() + ";"
                    + pelicula.getDirector() + ";" + pelicula.getActoresArchivo() + ";"
                    + pelicula.getPremiosArchivo() + ";" + String.valueOf(pelicula.getNumeroSalas()) + ";"
                    + pelicula.getDuracion() + "\n";
        }

        gf.escribirFichero(strPeliculas);

        this.vistaPrincipal.dispose();

    }

    private void rellenarTabla() {

        String nombresColumnas[] = {"Titulo", "Tiempo", "Director"};
        String datos[][] = new String[gp.getPeliculas().size()][3];
        int i = 0;
        int j = 0;
        for (Pelicula pelicula : gp.getPeliculas()) {
            datos[i][j] = pelicula.getTitulo();
            j += 1;
            datos[i][j] = pelicula.getDuracion();
            j += 1;
            datos[i][j] = pelicula.getDirector();
            j = 0;
            i += 1;
        }
        DefaultTableModel model = new DefaultTableModel(datos, nombresColumnas);

        vistaPrincipal.tblPeliculas.setModel(model);

    }

    private void leerArchivo() {
     
        String leerFichero = gf.leerFichero();
        String[] split = leerFichero.split(";");
        for (String string : split) {
            System.out.println(string);
        }
        /*
        String titulo = split[0];
        String director = split[1];
        String[] splitActores = split[3].split(",");
        ArrayList<Actor> actores = new ArrayList<>();
        for (String Actor : splitActores) {
            actores.add(new Actor(Actor));
        }
        String[] splitPremios = split[4].split(",");
        ArrayList<Premio> premios = new ArrayList<>();
        for (String premio : splitPremios) {
            premios.add(new Premio(premio));
        }
        int numeroSalas = Integer.parseInt(split[5]);
        */
    }

}
