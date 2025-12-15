package Activitats;
import packages.*;

public abstract class Activitat {
    protected String nom;
    protected float preu;
    protected String[] colectius;
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;
    protected int placesMaximes;
    protected int placesOcupades;


    public Activitat(String nom, float preu, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio) {
        this.nom = nom;
        this.preu = preu;
        this.colectius = colectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }



    public abstract String toString(); 

    public boolean estaEnPeriodeInscripcio(Data dataActual){
        return !dataActual.dataPassada(dataFiInscripcio) && dataActual.dataPassada(dataIniciInscripcio);
    }  

    public abstract boolean activaEnDia(Data data);
    
    
    public String getNom(){return nom;}
    public float getPreu(){return preu;}
    public String[] getColectius(){return colectius;}
    public boolean tePlacesDisponibles(){return placesOcupades<placesMaximes;}//en online tenemos override de esto
    public Data getDataIniciInscripcio(){return dataIniciInscripcio;}
    public Data getDataFiInscripcio(){return dataFiInscripcio;}    
    public int getPlacesOcupades(){return placesOcupades;}




}
