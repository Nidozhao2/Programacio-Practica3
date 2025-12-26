package Activitats;

import packages.Data;

public class ActivitatDia extends Activitat {

    private int hora;
    private int minut;
    private String ciutat;
    private int durada;

    public ActivitatDia(String nom, float preu, String[] colectius,
            Data dataIniciInscripcio, Data dataFiInscripcio,
            Data dataActivitat, int hora, int minut, int durada, int placesMaximes, String ciutat)
            throws HoraIncorrecta {

        super(nom, preu, colectius, dataIniciInscripcio, dataFiInscripcio, placesMaximes, dataActivitat);

        if (hora < 0 || hora > 23 || minut < 0 || minut > 59) {
            throw new HoraIncorrecta("Error: L'hora " + hora + ":" + minut + " no és vàlida.");
        }

        this.hora = hora;
        this.minut = minut;
        this.ciutat = ciutat;
        this.durada = durada;
        this.dataActivitat = dataActivitat;
        setFiActivitat();
    }

    public void setFiActivitat() {
        int minutsInici = (this.hora * 60) + this.minut;
        int minutsFinal = minutsInici + this.durada;
        // Un dia té 1440 minuts (24h * 60m).
        int diesDeMes = minutsFinal / 1440;

        Data dataFinal = this.dataActivitat.copia();

        for (int i = 0; i < diesDeMes; i++) {
            dataFinal = dataFinal.diaSeguent();
        }
        super.fiActivitat = dataFinal;
    }

    public String getNom() {
        return this.nom;
    }

    public float getPreu() {
        return this.preu;
    }

    public String[] getColectius() {
        return this.colectius;
    }

    public Data getDataIniciInscripcio() {
        return this.dataIniciInscripcio;
    }

    public Data getDataFiInscripcio() {
        return this.dataFiInscripcio;
    }

    public int getHora() {
        return hora;
    }

    public int getMinut() {
        return minut;
    }

    public int getDurada() {
        return this.durada;
    }

    public String getCiutat() {
        return ciutat;
    }

    public Data getDataActivitat() {
        return dataActivitat;
    }

    public void setHora(int hora) throws HoraIncorrecta {
        if (hora < 0 || hora > 23)
            throw new HoraIncorrecta("Hora incorrecta: " + hora);
        this.hora = hora;
    }

    public void setMinut(int minut) throws HoraIncorrecta {
        if (minut < 0 || minut > 59)
            throw new HoraIncorrecta("Minuts incorrectes: " + minut);
        this.minut = minut;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public void setDataActivitat(Data dataActivitat) {
        this.dataActivitat = dataActivitat;
        super.dataActivitat = dataActivitat;
    }

    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }

    public boolean activaEnDia(Data data) {
        return this.dataActivitat.esIgualA(data);
    }

    public String toString() {
        String textMinuts;

        if (minut < 10) {
            textMinuts = "0" + minut;
        } else {
            textMinuts = "" + minut;
        }

        String textHora;
        if (hora < 10) {
            textHora = "0" + hora;
        } else {
            textHora = "" + hora;
        }

        String horari = textHora + ":" + textMinuts;

        return "ActivitatDia;" + nom + ";" + preu + ";" + dataActivitat.toString() + ";" + horari + ";" + ciutat;
    }
}