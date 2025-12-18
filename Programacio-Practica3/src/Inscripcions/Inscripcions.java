package Inscripcions;

import packages.Data;
import Activitats.*;
import usuaris.Usuari;
import usuaris.LlistaUsuaris;

/**
 * Classe Inscripcions
 * 
 * La classe Inscripcio gestiona les inscripcions dels usuaris a les activitats.
 * Cada instància representa una inscripció (relació) entre una activitat i diversos usuaris.
 * Té una llista d'usuaris inscrits i una llista d'usuaris en espera.
 * 
 */
public class Inscripcions {

    private LlistaUsuaris usuarisInscrits;
    private LlistaUsuaris usuarisEnEspera;
    private Activitat activitatInscripcio;
    private static final int MAX_ESPERA = 10;

    public Inscripcions(Activitat activitatInscripcio){
        if (activitatInscripcio instanceof ActivitatOnline) {
            this.usuarisInscrits = new LlistaUsuaris(1000000); // per a activitats online, posem un límit molt alt
        } else {
            this.usuarisInscrits = new LlistaUsuaris(activitatInscripcio.getPlacesMaximes());
        }
        this.usuarisEnEspera = new LlistaUsuaris(MAX_ESPERA);
        this.activitatInscripcio = activitatInscripcio;
    }

    public void inscriureUsuari(Usuari usuariInscripcio, Data dataInscripcio) {
        if (!estaInscrit(usuariInscripcio)) {
            if (esPotInscriure(usuariInscripcio, activitatInscripcio, dataInscripcio)) {

                // si hi ha places disponibles, inscriure directament, si no, afegir a la llista d'espera
                if (activitatInscripcio.tePlacesDisponibles()) {
                    // Inscriure a l'usuari
                    activitatInscripcio.ocuparPlaca();
                    usuarisInscrits.afegirUsuari(usuariInscripcio);
                } else if (usuarisEnEspera.length() < MAX_ESPERA) {
                    // Afegir a la llista d'espera
                    usuarisEnEspera.afegirUsuari(usuariInscripcio);
                } else {
                    throw new IllegalArgumentException("No hi ha places disponibles ni a la llista d'espera."); // possiblement no és la excepció més adequada
                }
            } else {
                throw new IllegalArgumentException("L'usuari no es pot inscriure a l'activitat. El tipus d'usuari no és vàlid o ja ha tancat el període d'inscripció.");
            }
        } else {
            throw new IllegalArgumentException("L'usuari ja està inscrit a l'activitat.");
        }
    }

    
    public void baixaUsuari(Usuari usuariBaixa) { // !! Posible reformateo para optimizar

        if (usuarisInscrits.usuariExisteix(usuariBaixa)) { // L'usuari que es vol donar de baixa està inscrit a l'activitat

            usuarisInscrits.eliminarUsuari(usuariBaixa);
            if (usuarisEnEspera.length() > 0) {
                // Moure el primer usuari de la llista d'espera a la llista d'inscrits
                Usuari usuariAInscriure = usuarisEnEspera.getUsuari(0);
                usuarisInscrits.afegirUsuari(usuariAInscriure);
                usuarisEnEspera.eliminarUsuari(usuariAInscriure);
            } else { 
                activitatInscripcio.alliberarPlaca(); // Si no hi ha ningú a la llista d'espera, alliberem la plaça
            }
        } else if (usuarisEnEspera.usuariExisteix(usuariBaixa)) { // L'usuari que es vol donar de baixa està a la llista d'espera

            usuarisEnEspera.eliminarUsuari(usuariBaixa);
        } else {
            throw new IllegalArgumentException("L'usuari no està inscrit ni a la llista d'espera.");
        }
    } 

    /**
     * Comprova si un usuari ja està inscrit a l'activitat o està en llista d'espera.
     * @param usuariConsulta
     * @return boolean indicant si l'usuari està inscrit o no.
     */
    public boolean estaInscritOEsperant(Usuari usuariConsulta) {
        return usuarisInscrits.usuariExisteix(usuariConsulta) || usuarisEnEspera.usuariExisteix(usuariConsulta);
    }

    /**
     * Comprova si un usuari ja està inscrit a l'activitat.
     * @param usuariConsulta
     * @return boolean indicant si l'usuari està inscrit o no.
     */
    public boolean estaInscrit(Usuari usuariConsulta) {
        return usuarisInscrits.usuariExisteix(usuariConsulta);
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

    public LlistaUsuaris getUsuarisInscrits() {
        return usuarisInscrits;
    }

    public LlistaUsuaris getUsuarisEnEspera() {
        return usuarisEnEspera;
    }



    



}
