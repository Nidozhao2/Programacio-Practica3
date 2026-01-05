package main.AplicacioIG;

import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import activitats.*;
import packages.Data;

public class VistaCalendari extends JFrame{
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

    private int mesMostrat = 0; // El mes es guarda com a índex (0-11) per facilitar la gestió de la classe Data
    private int activitatMostrada = 0;
    private LlistaActivitats llistaActivitats;
    private Data dataActual = new Data(1,1,2026);

    public VistaCalendari() {
        this.llistaActivitats = new LlistaActivitats(1000);
        try {
            llegirActivitats(llistaActivitats);
        } catch (Exception e) {
            System.out.println("Error en llegir les activitats: " + e.getMessage());
        }
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
        Data diaCalendari = dataActual.copia(); 

        AccioBotoDia accioDia = new AccioBotoDia(this);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int numeroDia = i * 10 + j + 1;

                if (numeroDia <= 31) {
                    dies[i][j] = new JButton(String.valueOf(numeroDia));
                    panellCalendari.add(dies[i][j]);
                    dies[i][j].setBackground(color_dia);
                    dies[i][j].setVerticalAlignment(SwingConstants.TOP);

                    dies[i][j].addActionListener(accioDia);

                    mostrarActivitatsActivesEnDia(diaCalendari, dies[i][j]);
                    diaCalendari = diaCalendari.diaSeguent();
                }
            }
        }
    }

    /**
     * Actualitza el calendari mostrant les activitats segons els filtres seleccionats
     */
    public void actualitzarCalendari() {
        int diesMaxims = 31;
        System.out.println(mesMostrat);
        if (mesMostrat == 1) {
            diesMaxims = 28;
        } else if (mesMostrat == 3 || mesMostrat == 5 || mesMostrat == 8 || mesMostrat == 10) { // Abril, Juny, Setembre, Novembre
            diesMaxims = 30;
        } else { // Altres mesos amb 31 dies
            diesMaxims = 31;
        }
        
        Data diaCalendari = dataActual.copia();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j + 1 > diesMaxims) {
                    if (dies != null && dies[i][j] != null) {
                        dies[i][j].setVisible(false);
                    }
                } else {
                    dies[i][j].setVisible(true);
                    dies[i][j].setBackground(color_dia);
                    dies[i][j].setText(String.valueOf(i * 10 + j + 1));
                    mostrarActivitatsActivesEnDia(diaCalendari, dies[i][j]);
                    diaCalendari = diaCalendari.diaSeguent();
                }
            }
        }
    }

    /**
     * Afegeix al text de dins d'un botó que representa un dia del calendari les activitats actives en aquesta data
     * @param data
     * @param botoDia
     */
    private void mostrarActivitatsActivesEnDia(Data data, JButton botoDia) {
        LlistaActivitats activitatsActives = this.llistaActivitats.getActivitatsActivesEnData(data);
        String textOriginal = botoDia.getText(); 
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<b>").append(textOriginal).append("</b>");

        if (activitatMostrada != 0) {
            // filtrem les activitats segons el tipus del filtre
            for (int i = 0; i < activitatsActives.getNElems(); ) {
                Activitat activitat = activitatsActives.getActivitat(i);

                boolean eliminar = false;
                if (activitatMostrada == 1 && !(activitat instanceof ActivitatDia)) {
                    eliminar = true;
                } else if (activitatMostrada == 2 && !(activitat instanceof ActivitatOnline)) {
                    eliminar = true;
                } else if (activitatMostrada == 3 && !(activitat instanceof ActivitatPeriodiques)) {
                    eliminar = true;
                }

                if (eliminar) {
                    activitatsActives.esborrarActivitat(activitat.getNom());
                } else {
                    i++; // només incrementem l'índex si no hem eliminat l'activitat
                }
            }
        }

        if (activitatsActives.getNElems() > 0) {
            for (int i = 0; i < activitatsActives.getNElems(); i++) {
                Activitat activitat = activitatsActives.getActivitat(i);

                if (activitatMostrada == 0 || // Totes
                    (activitatMostrada == 1 && activitat instanceof ActivitatDia) ||
                    (activitatMostrada == 2 && activitat instanceof ActivitatOnline) ||
                    (activitatMostrada == 3 && activitat instanceof ActivitatPeriodiques)) {
                        // L'activitat compleix el filtre, l'afegim al text
                        sb.append("<br>· ").append(activitat.getNom());
                } else {
                    continue; // Saltem aquesta activitat ja que no compleix el filtre
                }
            }
        } else {
            sb.append("<br><br><i>No hi ha activitats</i>");
        }
        sb.append("</html>");
        botoDia.setText(sb.toString());
    }

    public static void main(String[] args) {
        new VistaCalendari();
    }

    public int getMesMostrat() {
        return mesMostrat;
    }

    public int getActivitatMostrada() {
        return activitatMostrada;
    }

    public void setMesMostrat(int mesMostrat) {
        this.mesMostrat = mesMostrat;
    }

    public void setActivitatMostrada(int activitatMostrada) {
        this.activitatMostrada = activitatMostrada;
    }

    public void setDataActual(Data dataActual) {
        this.dataActual = dataActual;
    }

    public Data getDataActual() {
        return this.dataActual;
    }

    public JComboBox getFiltreActivitat() {
        return filtreActivitat;
    }

    public JComboBox getFiltreMes() {
        return filtreMes;
    }

    public LlistaActivitats getLlistaActivitats() {
        return this.llistaActivitats;
    }

    /**
     * Mètode per llegir les activitats des d'un fitxer de text i enmagatzemar-les a la llista
     * @param llistaActivitats
     * @throws Exception
     */
    public static void llegirActivitats(LlistaActivitats llistaActivitats) throws Exception {
       
    BufferedReader br = new BufferedReader(new FileReader("Activitats.txt"));
    String linia;

    while ((linia = br.readLine()) != null) {

        String[] parts = linia.split(";");

        char tipus = parts[0].charAt(0);

        String nom = parts[1];

        float preu = Float.parseFloat(parts[2]);

        String[] colectius = parts[3].split(",");
        Data dataIniciInsc = parseData(parts[4]);
        Data dataFiInsc = parseData(parts[5]);
        Data dataIniciAct = parseData(parts[6]);
        int placesMaximes=Integer.parseInt(parts[7]);
        int placesOcupades=Integer.parseInt(parts[8]);

        switch (tipus) {

            case 'D':
                
                int hora = Integer.parseInt(parts[9]);
                int minut = Integer.parseInt(parts[10]);
                String ciutat = parts[11];
                int durada = Integer.parseInt(parts[12]);
                
               

                ActivitatDia ad = new ActivitatDia(
                        nom, preu, colectius, dataIniciInsc, dataFiInsc, placesMaximes,
                        dataIniciAct, hora, minut, durada, ciutat
                );
                ad.setPlacesOcupades(placesOcupades);
                llistaActivitats.afegirActivitat(ad);
                break;

            case 'O':

                String enllac = parts[9];
                int periode = Integer.parseInt(parts[10]);

                ActivitatOnline ao = new ActivitatOnline(
                        nom, colectius, dataIniciInsc, dataFiInsc,
                        dataIniciAct, periode, enllac
                );
                
                llistaActivitats.afegirActivitat(ao);
                
                break;

            case 'P':
                int setmanes = Integer.parseInt(parts[9]);
                int horaInici = Integer.parseInt(parts[10]);
                int minutInici = Integer.parseInt(parts[11]);
                int horaFinal = Integer.parseInt(parts[12]);
                int minutFinal = Integer.parseInt(parts[13]);


                String centre = parts[14];
                String ciutatP = parts[15];
                
                

                ActivitatPeriodiques ap = new ActivitatPeriodiques(
                        nom, preu, colectius, dataIniciInsc, dataFiInsc,
                        placesMaximes, dataIniciAct, setmanes, horaInici,
                        minutInici, horaFinal, minutFinal, centre, ciutatP
                );
                ap.setPlacesOcupades(placesOcupades);
                llistaActivitats.afegirActivitat(ap);
                break;
        }
    }

        br.close();
    }

    private static Data parseData(String text) {
        String[] aux = text.split("/");
        return new Data(
                Integer.parseInt(aux[0]),
                Integer.parseInt(aux[1]),
                Integer.parseInt(aux[2])
        );
    }
}
