package main.AplicacioIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import activitats.*;
import packages.Data;

public class FinestraDetalls extends JDialog { 
    private static final long serialVersionUID = 1L;

    public FinestraDetalls(JFrame parent, Data dataSeleccionada, LlistaActivitats llista) {
        super(parent, "Detalls del dia", true);
        
        this.setSize(500, 400); 
        this.setLayout(new BorderLayout()); 

        JTextArea areaText = new JTextArea();
        
        String textFinal = "Dia: " + dataSeleccionada + "\n\n";
        
        LlistaActivitats actives = llista.getActivitatsActivesEnData(dataSeleccionada);
        if (actives.getNElems() > 0) {
            for (int i = 0; i < actives.getNElems(); i++) {
                Activitat act = actives.getActivitat(i);
                areaText.append("Nom: " + act.getNom() + "\n");
                areaText.append("Tipus: " + act.getClass().getSimpleName() + "\n");
                
                String places = "No";
                if (act.tePlacesDisponibles()) {
                    places = "Sí";
                }
                areaText.append("Places: " + places + "\n\n");
            }
        } else {
            areaText.setText("No hi ha activitats.");
        }

        this.add(areaText, BorderLayout.CENTER);

        JButton botoTancar = new JButton("Tancar");
        
        botoTancar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Ocultar finestra en lloc de dispose() que no surt
            }
        });
        
        this.add(botoTancar, BorderLayout.SOUTH);
    }
}