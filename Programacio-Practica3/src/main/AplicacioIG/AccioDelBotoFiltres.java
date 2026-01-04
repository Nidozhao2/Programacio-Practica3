package main.AplicacioIG;

import java.awt.event.*;
import javax.swing.*;
import packages.Data;

public class AccioDelBotoFiltres implements ActionListener {
    private vistaCalendari vista;

    public AccioDelBotoFiltres(vistaCalendari vista) {
        this.vista = vista;
    }

    public void actionPerformed(ActionEvent e) {
        String activitatFiltre = vista.getFiltreActivitat().getSelectedItem().toString().trim().toLowerCase();
        int mesFiltre = vista.getFiltreMes().getSelectedIndex();

        Data dataActual = vista.getDataActual();
        int anteriorMesMostrat = vista.getMesMostrat(); // agafem mes mostrat anterior per saber si hem de retrocedir o avançar la data
        
        if (mesFiltre < anteriorMesMostrat) {
            // hem de retrocedir la data actual
            for (int i = 0; i < anteriorMesMostrat - mesFiltre; i++) {
                dataActual = dataActual.mesAnterior();
            }
        } else if (mesFiltre > anteriorMesMostrat) {
            // hem d'avançar la data actual
            for (int i = 0; i < mesFiltre - anteriorMesMostrat; i++) {
                dataActual = dataActual.mesSeguent();
            }
        }
        vista.setDataActual(dataActual);
        vista.setMesMostrat(mesFiltre);
        vista.setActivitatMostrada(activitatFiltre);

        vista.actualitzarCalendari();
    }
}