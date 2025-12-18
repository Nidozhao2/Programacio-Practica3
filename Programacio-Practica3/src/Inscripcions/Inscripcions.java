package Inscripcions;


import packages.Data;
import Activitats.Activitat;
import usuaris.Usuari;

/**
 * Classe Inscripcions
 * 
 * La classe Inscripcio gestiona les inscripcions dels usuaris a les activitats.
 * Cada instància representa una inscripció (relació) entre un usuari i una activitat.
 * 
 * El constructor valida si l'usuari es pot inscriure a l'activitat i llença una excepció si no és possible.
 */
public class Inscripcions {

    private Usuari[] usuarisInscrits;
    private Usuari[] usuarisEnEspera;
    private Activitat activitatInscripcio;
    private static final int MAX_ESPERA = 10;

    public Inscripcions(Usuari usuariInscripcio, Activitat activitatInscripcio, Data dataInscripcio){
        this.usuarisInscrits = new Usuari[activitatInscripcio.getPlacesMaximes()];
        this.usuarisEnEspera = new Usuari[MAX_ESPERA];
        this.activitatInscripcio = activitatInscripcio;
    }

    public void inscriureUsuari(Usuari usuariInscripcio, Data dataInscripcio) {
        if (esPotInscriure(usuariInscripcio, activitatInscripcio, dataInscripcio)) {
            if (activitatInscripcio.tePlacesDisponibles()) {
                // Inscriure a l'usuari
                activitatInscripcio.ocuparPlaca();
                for (int i = 0; i < usuarisInscrits.length; i++) {
                    if (usuarisInscrits[i] == null) {
                        usuarisInscrits[i] = usuariInscripcio;
                        break;
                    }
                }
            } else if (usuarisEnEspera.length < MAX_ESPERA) {
                // Afegir a la llista d'espera
                for (int i = 0; i < MAX_ESPERA; i++) {
                    if (usuarisEnEspera[i] == null) {
                        usuarisEnEspera[i] = usuariInscripcio;
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("No hi ha places disponibles ni a la llista d'espera."); // possiblement no és la excepció més adequada
            }
        } else {
            throw new IllegalArgumentException("L'usuari no es pot inscriure a l'activitat.");
        }
    }
    /**
     * Verifica si un usuari es pot inscriure a una activitat en una data determinada. 
     * @param usuariInscripcio
     * @param activitatInscripcio
     * @param dataInscripcio
     * @return boolean indicant si l'usuari es pot inscriure a l'activitat en la data donada.
     */
    private boolean esPotInscriure(Usuari usuariInscripcio, Activitat activitatInscripcio, Data dataInscripcio){

        // veiem si el tipus d'usuari està dins dels col·lectius permesos a l'activitat
        boolean tipusUsuariPermes = activitatInscripcio.validarUsuari(usuariInscripcio.getTipus());

        // veiem si la data actual està dins del perídode d'inscripció
        boolean enPeriodeInscripcio = activitatInscripcio.estaEnPeriodeInscripcio(dataInscripcio);

        return tipusUsuariPermes && enPeriodeInscripcio;
    }





    



}
