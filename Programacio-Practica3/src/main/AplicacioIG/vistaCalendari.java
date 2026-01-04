package main.AplicacioIG;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;


public class vistaCalendari extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel panellCalendari;
    private JPanel panellFiltres;
    private JPanel panellBotoAplicarFiltres;
    private JTextArea filtreActivitat;
    private JTextArea filtreMes;
    private JButton[][] dies;
    private JLabel labelFiltreAct;
    private JLabel labelFiltreMes;
    private JButton botoAplicarFiltres;

    private String mesMostrat;
    private String activitatMostrada;

    public vistaCalendari() {
        setTitle("Calendari");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initContingutFinestra();
        setVisible(true);
    }

    private void initContingutFinestra() {
        panellFiltres = new JPanel();
        GridLayout gridLayoutPanellFiltres = new GridLayout(2, 2);
        gridLayoutPanellFiltres.setHgap(10);
        panellFiltres.setLayout(gridLayoutPanellFiltres);
        panellFiltres.setBorder(BorderFactory.createTitledBorder("Filtres"));
        add(panellFiltres, BorderLayout.NORTH);

        labelFiltreAct = new JLabel("Filtre Activitat");
        panellFiltres.add(labelFiltreAct);
        labelFiltreMes = new JLabel("Filtre Mes");
        panellFiltres.add(labelFiltreMes);

        filtreActivitat = new JTextArea();
        panellFiltres.add(filtreActivitat);
        filtreMes = new JTextArea();
        panellFiltres.add(filtreMes);

        panellBotoAplicarFiltres = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoAplicarFiltres = new JButton("Aplicar Filtres");
        panellBotoAplicarFiltres.add(botoAplicarFiltres);

        panellCalendari = new JPanel();
        panellCalendari.setLayout(new GridLayout(4, 10));
        panellCalendari.setBorder(BorderFactory.createTitledBorder("Calendari"));

        JPanel panellCentralContent = new JPanel();
        panellCentralContent.setLayout(new BorderLayout());
        panellCentralContent.add(panellBotoAplicarFiltres, BorderLayout.NORTH);
        panellCentralContent.add(panellCalendari, BorderLayout.CENTER);
        add(panellCentralContent, BorderLayout.CENTER);

        dies = new JButton[4][10];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j + 1 > 31) {
                    break;
                } else {
                    dies[i][j] = new JButton("" + (i * 10 + j + 1));
                    dies[i][j].setVerticalAlignment(SwingConstants.TOP);
                    dies[i][j].setBackground(Color.GRAY);
                    panellCalendari.add(dies[i][j]);
                }
            }
        }
    }

    /**
     * Mètode per actualitzar el calendari segons els filtres
     */
    private void actualitzarCalendari() {
        

    }

    public static void main(String[] args) {
        new vistaCalendari();
    }
}
