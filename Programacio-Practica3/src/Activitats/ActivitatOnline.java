package Activitats;

import packages.*;

public class ActivitatOnline extends Activitat {
    private int placesOcupades = 0;
    private String enllaç;
    private Data dataIniciActivitat;
    //private Data dataIniciInscripcio;   
    //private Data dataFiInscripcio;
    private int periodeVisualitzacio; // en dies

    public ActivitatOnline(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, String enllaç) {
        super(nom, 0, colectius, dataIniciInscripcio, dataFiInscripcio);
        this.enllaç = enllaç;
    }

    
    public void sumaPlacaOcupada() {
        placesOcupades++;
    }

    public int getPlacesOcupades() {
        return placesOcupades;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getEnllaç() {
        return enllaç;
    }

    public void setEnllaç(String enllaç) {
        this.enllaç = enllaç;
    }

    public String[] getColectius() {
        return colectius;
    }

    public String getNom() {
        return nom;
    }

    public Data getDataIniciInscripcio() {
        return dataIniciInscripcio;
    }

    public Data getDataFiInscripcio() {
        return dataFiInscripcio;
    }

    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }

    public boolean tePlacesDisponibles() {
        return true; // Les activitats online no tenen límit de places
    }

    public String toString() {
        return "Activitat Online: " + nom + ", Enllaç: " + enllaç + ", Preu: " + preu + "€, Data Inici Inscripció: " + dataIniciInscripcio.toString() + ", Data Fi Inscripció: " + dataFiInscripcio.toString();
    }

    public boolean activaEnDia(Data data) {
        Data dataFiActivitat = dataIniciActivitat.copia();
        for (int i = 0; i < periodeVisualitzacio; i++) {
            dataFiActivitat = dataFiActivitat.diaSeguent();
        }
        return (data.comparaAmb(dataIniciActivitat) >= 0 && data.comparaAmb(dataFiActivitat) <= 0);
    }

    public boolean classeEnDia(Data data) {
        return activaEnDia(data);
    }


}
