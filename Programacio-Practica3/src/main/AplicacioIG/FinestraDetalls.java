package main.AplicacioIG;

import javax.swing.*;
import java.awt.*;
import activitats.*;
import packages.Data;

public class FinestraDetalls extends JDialog { 
    private static final long serialVersionUID = 1L;

    public FinestraDetalls(JFrame parent, Data dataSeleccionada, LlistaActivitats llista) {
        super(parent, "Detalls del dia", true);
        
        this.setSize(500, 400); 
        this.setLayout(new BorderLayout()); 

        JTextArea areaText = new JTextArea();
        areaText.setEditable(false); // Perquè no es pugui escriure a sobre
        
        areaText.append("Dia: " + dataSeleccionada + "\n\n");
        
        LlistaActivitats actives = llista.getActivitatsActivesEnData(dataSeleccionada);
        
        if (actives.getNElems() > 0) {
            for (int i = 0; i < actives.getNElems(); i++) {
                Activitat act = actives.getActivitat(i);
                
                areaText.append("Nom: " + act.getNom() + "\n");
                areaText.append("Tipus: " + act.getClass().getSimpleName() + "\n");
                
                areaText.append("Places Ocupades: " + act.getPlacesOcupades() + "\n");

                // Si no és online, mostrem les màximes
                if (! (act instanceof ActivitatOnline)) {
                    areaText.append("Places Màximes: " + act.getPlacesMaximes() + "\n");
                }
                
                // Es pot inscriure?
                String esPot = "No";
                if (act.tePlacesDisponibles()) {
                    esPot = "Sí";
                }
                areaText.append("Es pot Inscriure: " + esPot + "\n");
            }
        } else {
            areaText.append("No hi ha activitats programades.");
        }

        this.add(areaText, BorderLayout.CENTER);

        JButton botoTancar = new JButton("Tancar");
        AccioBotoTancar accioTancar = new AccioBotoTancar(this);
        botoTancar.addActionListener(accioTancar);
        
        this.add(botoTancar, BorderLayout.SOUTH);
    }
}