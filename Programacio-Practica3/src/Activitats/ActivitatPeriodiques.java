package Activitats;

import packages.*;

public class ActivitatPeriodiques extends Activitat {

    private int setmanes_totals;
    private int horaInici; // (0-24)

    private int minutInici;

    private int horaFinal;
    private int minutFinal;

    private int durada;

    private String centre;
    private String ciutat;

    public ActivitatPeriodiques(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio,
            int setmanes_totals, int horaInici, int minutInici, int horaFinal, int minutFinal, String centre,
            String ciutat, int placesMaximes, Data dataActivitat) {

        super(nom, 0, colectius, dataIniciInscripcio, dataFiInscripcio, placesMaximes, dataActivitat);

        this.setmanes_totals = setmanes_totals;
        this.horaInici = horaInici;
        this.minutInici = minutInici;
        this.horaFinal = horaFinal;
        this.minutFinal = minutFinal;
        this.centre = centre;
        this.ciutat = ciutat;
        placesOcupades = 0;
        setFiActivitat();
        setDurada();
    }

    public void setFiActivitat() {
        this.fiActivitat = dataActivitat.copia(); // copia bien implementado?
        for (int i = 0; i < setmanes_totals; i++) {
            this.fiActivitat = fiActivitat.setmanaSeguent();
        }
    }

    public int setDurada() {
        durada = 60 * (horaFinal - horaInici) + (minutFinal - minutInici);
        return durada;
    }

    public int getHoraInici() {
        return horaInici;
    }

    public int getMinutInici() {
        return minutInici;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public int getMinutFinal() {
        return minutFinal;
    }

    public int getSetmanes_totals() {
        return setmanes_totals;
    }

    public int gethoraInici() {
        return horaInici;
    }

    public int gethoraFinal() {
        return horaFinal;
    }

    public String getCiutat() {
        return ciutat;
    }

    @Override
    public float getPreu() {
        return preu;
    }

    public String getCentre() {
        return centre;
    }

    @Override
    public String[] getColectius() {
        return colectius;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Data getDataIniciInscripcio() {
        return dataIniciInscripcio;
    }

    @Override
    public Data getDataFiInscripcio() {
        return dataFiInscripcio;
    }

    public void setSetmanes_totals(int setmanes_totals) {
        this.setmanes_totals = setmanes_totals;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public void setHoraInici(int horaInici) {
        this.horaInici = horaInici;
        setDurada();
    }

    public void setMinutInici(int minutInici) {
        this.minutInici = minutInici;
        setDurada();
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
        setDurada();
    }

    public void setMinutFinal(int minutFinal) {
        this.minutFinal = minutFinal;
        setDurada();
    }

    @Override // cal cambiar aixo, punt 5 de E/S
    public boolean activaEnDia(Data data) {
        Data dataFiActivitat = dataActivitat.copia();

        boolean retornar = data.esIgualA(dataFiActivitat);
        int i = 0;

        while (i < setmanes_totals && !retornar) {
            dataFiActivitat = dataFiActivitat.setmanaSeguent();
            retornar = data.esIgualA(dataFiActivitat);
            i++;
        }
        return retornar;
    }

    @Override
    public boolean estaEnPeriodeInscripcio(Data dataActual) {
        return (dataActual.comparaAmb(this.dataIniciInscripcio) >= 0 &&
                dataActual.comparaAmb(this.dataFiInscripcio) <= 0);
    }

    @Override
    public String toString() {
        return "Activitat Periodica: " +
                "Setmanes totals: " + setmanes_totals +
                ", Hora inici: " + String.format("%02d:%02d", horaInici, minutInici) +
                ", Hora final: " + String.format("%02d:%02d", horaFinal, minutFinal) +
                ", Durada: " + durada + " minuts" +
                ", Centre: " + centre +
                ", Ciutat: " + ciutat +
                ", Places màximes: " + placesMaximes +
                ", Places ocupades: " + placesOcupades +
                ", Data inici activitat: " + dataActivitat.toString();
    }

}
