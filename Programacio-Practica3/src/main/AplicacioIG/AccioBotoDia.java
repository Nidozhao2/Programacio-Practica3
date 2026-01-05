package main.AplicacioIG;

import java.awt.event.*;
import javax.swing.JButton;
import packages.Data;

public class AccioBotoDia implements ActionListener {
    private VistaCalendari vista;

    public AccioBotoDia(VistaCalendari vista) {
        this.vista = vista;
    }

    public void actionPerformed(ActionEvent e) {
        JButton botoPremut = (JButton) e.getSource();
        
        String textBoto = botoPremut.getText();

        try {

            int dia = Integer.parseInt(textBoto.trim());
            
            Data dataActualVista = vista.getDataActual();
            Data dataClicada = new Data(dia, dataActualVista.getMes(), dataActualVista.getAny());

            FinestraDetalls dialog = new FinestraDetalls(vista, dataClicada, vista.getLlistaActivitats());
            dialog.setVisible(true);

        } catch (NumberFormatException ex) {
            // Si el text del botó no és un número, ignorem el clic i no fem res.
        }
    }
}