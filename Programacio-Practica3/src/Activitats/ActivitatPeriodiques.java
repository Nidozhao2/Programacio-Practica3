package Activitats;
import packages.*;

public class ActivitatPeriodiques extends Activitat {
    private String dia;
    private int setmanes_totals;
    private int hora_inici; //(0-24)
    private int hora_final;
    private int durada;
    private String centre;
    private String ciutat;
    private int places;

    private int placesOcupades;


    private Data dataIniciActivitat;
    
    public ActivitatPeriodiques(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio,String dia, int setmanes_totals, int hora_inici, int hora_final, String centre, String ciutat, int places, Data dataIniciActivitat) {
        
        super(nom, 0, colectius, dataIniciInscripcio, dataFiInscripcio);
        this.dia=dia;
        this.setmanes_totals=setmanes_totals;
        this.hora_inici=hora_inici;
        this.hora_final=hora_final;
        durada=hora_final-hora_inici;
        this.centre=centre;
        this.ciutat=ciutat;
        this.places=places;
        placesOcupades=0;
        this.dataIniciActivitat=dataIniciActivitat;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getSetmanes_totals() {
        return setmanes_totals;
    }

    public void setSetmanes_totals(int setmanes_totals) {
        this.setmanes_totals = setmanes_totals;
    }

    public int getHora_inici() {
        return hora_inici;
    }

    public void setHora_inici(int hora_inici) {
        this.hora_inici = hora_inici;
    }

    public int getHora_final() {
        return hora_final;
    }

    public void setHora_final(int hora_final) {
        this.hora_final = hora_final;
    }

    public int getDurada() {
        return durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
    
















    public boolean activaEnDia(Data data) {
        Data dataFiActivitat = dataIniciActivitat.copia();
        for (int i = 0; i < setmanes_totals; i++) {
            dataFiActivitat = dataFiActivitat.setmanaSeguent();
        }
        return (data.comparaAmb(dataIniciActivitat) >= 0 && data.comparaAmb(dataFiActivitat) <= 0);
    }








    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }

    public boolean tePlacesDisponibles() {
        
        return placesOcupades==places;
    }

    public String toString() {
        return "Activitat Periodica: " + nom + "Preu: " + preu + "€, Data Inici Inscripció: " + dataIniciInscripcio.toString() + ", Data Fi Inscripció: " + dataFiInscripcio.toString();
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

















}
