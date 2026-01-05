package main.AplicacioIG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class AccioBotoTancar implements ActionListener {
    
    private JDialog finestra;

    public AccioBotoTancar(JDialog finestra) {
        this.finestra = finestra;
    }

    public void actionPerformed(ActionEvent e) {
        finestra.setVisible(false);
    }
}