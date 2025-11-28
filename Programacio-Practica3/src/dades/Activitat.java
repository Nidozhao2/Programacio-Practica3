package dades;

public abstract class Activitat {
    protected String nom;
    protected float preu;
    protected String[] colectius;
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;

    public Activitat(String nom, float preu, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio) {
        this.nom = nom;
        this.preu = preu;
        this.colectius = colectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }

    public abstract String getNom();

    public abstract float getPreu();

    public abstract String[] getColectius();

    public abstract Data getDataIniciInscripcio();

    public abstract Data getDataFiInscripcio();

    public abstract String toString();

    public abstract boolean estaEnPeriodeInscripcio(Data dataActual);

    public abstract boolean tePlacesDisponibles();

    public abstract boolean activaEnDia(Data data);

    public abstract int getPlacesOcupades();
}
