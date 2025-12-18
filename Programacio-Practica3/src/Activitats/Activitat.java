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
    protected Data dataActivitat;
    protected Data fiActivitat;
    
    public Activitat(String nom, float preu, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio,int placesMaximes,Data dataActivitat) {
        this.nom = nom;
        this.preu = preu;
        this.colectius = colectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
        this.placesMaximes=placesMaximes;
        placesOcupades=0;
        this.dataActivitat=dataActivitat;
        setfiActivitat();
    }

    public abstract void setfiActivitat();
    public Data getfiActivitat(){return fiActivitat;}

    public abstract String toString(); 

    public boolean estaEnPeriodeInscripcio(Data dataActual){
        return !dataActual.dataPassada(dataFiInscripcio) && dataActual.dataPassada(dataIniciInscripcio);
    }  

    public abstract boolean activaEnDia(Data data);
    
    public Data getDataInici(){return dataActivitat;}
    public String getNom(){return nom;}
    public float getPreu(){return preu;}
    public String[] getColectius(){return colectius;}
    public boolean tePlacesDisponibles(){return placesOcupades<placesMaximes;}//en online tenemos override de esto, sin embargo places maximas en online es integrer MAX_VALUE
    public Data getDataIniciInscripcio(){return dataIniciInscripcio;}
    public Data getDataFiInscripcio(){return dataFiInscripcio;}    

    public int getPlacesOcupades(){return placesOcupades;}
    public int getPlacesMaximes(){return placesMaximes;}


    public void setPlacesMaximes(int placesMaximes){
        if(!(this instanceof ActivitatOnline)){
            this.placesMaximes=placesMaximes;
        }
    }

    public void ocuparPlaca(){
        placesOcupades++;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public void setPreu(float preu) {
        this.preu = preu;
    }



    public void setColectius(String[] colectius) {
        this.colectius = colectius;
    }



    public void setDataIniciInscripcio(Data dataIniciInscripcio) {
        this.dataIniciInscripcio = dataIniciInscripcio;
    }



    public void setDataFiInscripcio(Data dataFiInscripcio) {
        this.dataFiInscripcio = dataFiInscripcio;
    }



    public void setPlacesOcupades(int placesOcupades) {
        this.placesOcupades = placesOcupades;
    }



    public void setDataActivitat(Data dataActivitat) {
        this.dataActivitat = dataActivitat;
    }











}
