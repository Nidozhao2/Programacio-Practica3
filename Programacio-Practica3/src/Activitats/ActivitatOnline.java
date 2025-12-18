package Activitats;

import packages.*;

public class ActivitatOnline extends Activitat {

    private String enllaç;

    //private Data dataIniciInscripcio;   
    //private Data dataFiInscripcio;
    private int periodeVisualitzacio; // en dies
    private Data dataFiActivitat;

    public ActivitatOnline(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio, int periodeVisualitzacio, String enllaç,Data dataActivitat) {
        super(nom, 0, colectius, dataIniciInscripcio, dataFiInscripcio,Integer.MAX_VALUE,dataActivitat);

        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllaç = enllaç;
        

    }
    @Override
    public void setfiActivitat(){ 
            // calculem la data fi de l'activitat
        this.dataFiActivitat = dataActivitat.copia();
        for (int i = 0; i < periodeVisualitzacio; i++) {
            dataFiActivitat = dataFiActivitat.diaSeguent();
        }
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

    public Data getDataFiActivitat() {
        return this.dataFiActivitat;
    }

    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }


    public String toString() {
        return "Activitat Online: " + nom + ", Enllaç: " + enllaç + ", Preu: " + preu + "€, Data Inici Inscripció: " + dataIniciInscripcio.toString() + ", Data Fi Inscripció: " + dataFiInscripcio.toString();
    }

    @Override
    public boolean activaEnDia(Data data) {
        Data dataFiActivitat = dataActivitat.copia();
        for (int i = 0; i < periodeVisualitzacio; i++) {
            dataFiActivitat = dataFiActivitat.diaSeguent();
        }
        return (data.comparaAmb(dataActivitat) >= 0 && data.comparaAmb(dataFiActivitat) <= 0);
    }

    public boolean classeEnDia(Data data) {
        return activaEnDia(data);
    }


    @Override
    public boolean tePlacesDisponibles(){
        return true;
    }




}
