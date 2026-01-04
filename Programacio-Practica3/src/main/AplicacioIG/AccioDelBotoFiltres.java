package main.AplicacioIG;

import java.awt.event.*;
import javax.swing.*;

public class AccioDelBotoFiltres implements ActionListener {
    private vistaCalendari vista;

    public AccioDelBotoFiltres(vistaCalendari vista) {
        this.vista = vista;
    }

    public void actionPerformed(ActionEvent e) {
        String activitatFiltre = vista.getFiltreActivitat().getSelectedItem().toString().trim().toLowerCase();
        String mesFiltre = vista.getFiltreMes().getSelectedItem().toString().trim().toLowerCase();

        vista.setActivitatMostrada(activitatFiltre);
        vista.setMesMostrat(mesFiltre);

        vista.actualitzarCalendari();
    }
}