/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ymp.controller;

import es.iespuertodelacruz.ymp.view.JdialogReserva;
import es.iespuertodelacruz.ymp.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Yared
 */
public class Controller implements ActionListener {

    View vista;
    JdialogReserva vistaReserva;

    public Controller(View vista) {

        this.vista = vista;

        this.vista.btnHabana.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vista.btnHabana) {
            elegirHabana();
        }

    }

    private void elegirHabana() {

        vistaReserva = new JdialogReserva(vista, true);

        vistaReserva.chTipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (vistaReserva.chTipo.getSelectedItem().equals("Congreso")) {
                    vistaReserva.txfNumeroJornadas.setEnabled(true);
                    vistaReserva.cbHabitaciones.setEnabled(true);
                    vistaReserva.lblNumeroJornadas.setEnabled(true);

                } else {
                    vistaReserva.txfNumeroJornadas.setEnabled(false);
                    vistaReserva.cbHabitaciones.setEnabled(false);
                    vistaReserva.lblNumeroJornadas.setEnabled(false);
                }
            }
        });

        vistaReserva.cbHabitaciones.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (vistaReserva.cbHabitaciones.isSelected()) {
                    vistaReserva.txfNumeroHabitaciones.setEnabled(true);
                    vistaReserva.lblNumeroHabitaciones.setEnabled(true);
                } else {
                    vistaReserva.txfNumeroHabitaciones.setEnabled(false);
                    vistaReserva.lblNumeroHabitaciones.setEnabled(false);
                }
            }
        });

        vistaReserva.setVisible(true);

    }

}
