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
        
        // Treiem totes les paraules "<html>" i "</html>" del text primer
        String textNet = textBoto.replace("<html>", "").replace("</html>", "");

        String diaString = textBoto;
        
        int inici = textNet.indexOf("<b>") + 3;
        int fi = textNet.indexOf("</b>");
        if (fi != -1) {
            diaString = textNet.substring(inici, fi);
        }
        

        try {
            int dia = Integer.parseInt(diaString.trim());
            
            Data dataActualVista = vista.getDataActual();
            Data dataClicada = new Data(dia, dataActualVista.getMes(), dataActualVista.getAny());

            FinestraDetalls dialog = new FinestraDetalls(vista, dataClicada, vista.getLlistaActivitats());
            dialog.setVisible(true);

        } catch (NumberFormatException ex) {
            System.out.println("Error llegint el dia. Text rebut: " + diaString);
        }
    }
}