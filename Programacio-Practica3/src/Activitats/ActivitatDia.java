package Activitats;

import packages.Data;

public class ActivitatDia extends Activitat {
    private Data dataActivitat;
    private int hora;
    private int minut;
    private int placesMaximes;
    private String ciutat;
    
    private int placesOcupades; 

    public ActivitatDia(String nom, float preu, String[] colectius, 
                        Data dataIniciInscripcio, Data dataFiInscripcio,
                        Data dataActivitat, int hora, int minut, int placesMaximes, String ciutat) {
        
        super(nom, preu, colectius, dataIniciInscripcio, dataFiInscripcio);
        
        this.dataActivitat = dataActivitat;

        if (hora >= 0 && hora <= 23 && minut >= 0 && minut <= 59) {
            this.hora = hora;
            this.minut = minut;
        } else {
            this.hora = 0;
            this.minut = 0;
            System.out.println("Error: Hora incorrecta. S'ha posat a 00:00.");
        }

        this.placesMaximes = placesMaximes;
        this.ciutat = ciutat;
        this.placesOcupades = 0;
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

    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }

    public boolean tePlacesDisponibles() {
        return placesMaximes>placesOcupades;
    }

    public boolean activaEnDia(Data data) {
        return this.dataActivitat.esIgualA(data);
    }

    public int getPlacesOcupades() {
        return placesOcupades;
    }

    public String toString() {
        String horari = hora + ":" + minut;
        return "ActivitatDia;" + nom + ";" + preu + ";" + dataActivitat.toString() + ";" + horari + ";" + ciutat;
    }


    // Mètode per incrementar inscrits
    public void ocuparPlaca() {
        if (tePlacesDisponibles()) {
            this.placesOcupades++;
        }
    }

    public String getCiutat() { 
        return ciutat; 
    }
    public Data getDataActivitat() { 
        return dataActivitat; 
    }
    public int getHora() {
        return hora;
    }
    public int getminut() {
        return minut;
    }
}