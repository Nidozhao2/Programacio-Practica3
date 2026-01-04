package main.AplicacioIG;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class vistaCalendari extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
    private static final String[] tipusActivitats = {"Totes", "Diaries", "Online", "Periòdiques"};
    private static final Color color_dia = new Color(52, 117, 155);
    private static final Color color_fons = new Color(57,57,77);
    private static final Color color_text = Color.WHITE;
    
    private JPanel panellCalendari;
    private JPanel panellFiltres;
    private JPanel panellBotoAplicarFiltres;
    private JComboBox filtreActivitat;
    private JComboBox filtreMes;
    private JButton[][] dies;
    private JLabel labelFiltreAct;
    private JLabel labelFiltreMes;
    private JButton botoAplicarFiltres;

    private String mesMostrat = "gener";
    private String activitatMostrada = "Totes";

    public vistaCalendari() {
        setTitle("Calendari");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initContingutFinestra();
        setVisible(true);
        setBackground(color_fons);
    }

    private void initContingutFinestra() {
        panellFiltres = new JPanel();
        GridLayout gridLayoutPanellFiltres = new GridLayout(2, 2);
        gridLayoutPanellFiltres.setHgap(10);
        panellFiltres.setLayout(gridLayoutPanellFiltres);
        TitledBorder titolPanellFiltres = new TitledBorder("Filtres");
        titolPanellFiltres.setTitleColor(color_text);
        panellFiltres.setBorder(titolPanellFiltres);
        panellFiltres.setForeground(color_text);
        panellFiltres.setBackground(color_fons);
        add(panellFiltres, BorderLayout.NORTH);

        labelFiltreAct = new JLabel("Filtre Activitat");
        labelFiltreAct.setForeground(color_text);
        panellFiltres.add(labelFiltreAct);
        labelFiltreMes = new JLabel("Filtre Mes");
        labelFiltreMes.setForeground(color_text);
        panellFiltres.add(labelFiltreMes);

        filtreActivitat = new JComboBox(tipusActivitats);
        panellFiltres.add(filtreActivitat);
        filtreMes = new JComboBox(mesos);
        panellFiltres.add(filtreMes);

        panellBotoAplicarFiltres = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoAplicarFiltres = new JButton("Aplicar Filtres");
        panellBotoAplicarFiltres.add(botoAplicarFiltres);
        panellBotoAplicarFiltres.setBackground(color_fons);
        
        AccioDelBotoFiltres accioBotoFiltres = new AccioDelBotoFiltres(this);
        botoAplicarFiltres.addActionListener(accioBotoFiltres);
        botoAplicarFiltres.setBackground(new Color(110,110,190));

        panellCalendari = new JPanel();
        panellCalendari.setLayout(new GridLayout(4, 10));
        TitledBorder titolPanellCalendari = new TitledBorder("Calendari");
        titolPanellCalendari.setTitleColor(Color.WHITE);
        panellCalendari.setBorder(titolPanellCalendari);
        panellCalendari.setBackground(color_fons);

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
                    dies[i][j] = new JButton(String.valueOf(i * 10 + j + 1));
                    panellCalendari.add(dies[i][j]);
                    dies[i][j].setBackground(color_dia);
                    dies[i][j].setVerticalAlignment(SwingConstants.TOP);
                }
            }
        }
    }

    /**
     * Mètode per actualitzar el calendari segons els filtres
     */
    public void actualitzarCalendari() {
        int diesMaxims = 31;
        System.out.println(mesMostrat);
        for (int i = 0; i < mesos.length; i++) {
            if (mesMostrat.equalsIgnoreCase(mesos[i])) {
                if (i == 1) {
                    diesMaxims = 28;
                } else if (i == 3 || i == 5 || i == 8 || i == 10) { // Abril, Juny, Setembre, Novembre
                    diesMaxims = 30;
                } else { // Altres mesos amb 31 dies
                    diesMaxims = 31;
                }
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j + 1 > diesMaxims) {
                    if (dies != null && dies[i][j] != null) {
                        dies[i][j].setVisible(false);
                    }
                } else {
                    dies[i][j].setVisible(true);
                    dies[i][j].setBackground(color_dia);
                }
            }
        }
    }

    public static void main(String[] args) {
        new vistaCalendari();
    }

    public String getMesMostrat() {
        return mesMostrat;
    }

    public String getActivitatMostrada() {
        return activitatMostrada;
    }

    public void setMesMostrat(String mesMostrat) {
        this.mesMostrat = mesMostrat;
    }

    public void setActivitatMostrada(String activitatMostrada) {
        this.activitatMostrada = activitatMostrada;
    }

    public JComboBox getFiltreActivitat() {
        return filtreActivitat;
    }

    public JComboBox getFiltreMes() {
        return filtreMes;
    }
}
