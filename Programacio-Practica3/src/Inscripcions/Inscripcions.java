package Inscripcions;

import packages.Data;
import Activitats.*;
import usuaris.Usuari;

/**
 * Classe Inscripcions
 * 
 * La classe Inscripcio gestiona les inscripcions dels usuaris a les activitats.
 * Cada instància representa una inscripció (relació) entre una activitat i diversos usuaris.
 * Té una llista d'usuaris inscrits i una llista d'usuaris en espera.
 * 
 */
public class Inscripcions {

    private Usuari[] usuarisInscrits;
    private Usuari[] usuarisEnEspera;
    private Activitat activitatInscripcio;
    private static final int MAX_ESPERA = 10;

    public Inscripcions(Activitat activitatInscripcio){
        if (activitatInscripcio instanceof ActivitatOnline) {
            this.usuarisInscrits = new Usuari[1000000]; // per a activitats online, posem un límit molt alt
        } else {
            this.usuarisInscrits = new Usuari[activitatInscripcio.getPlacesMaximes()];
        }
        this.usuarisEnEspera = new Usuari[MAX_ESPERA];
        this.activitatInscripcio = activitatInscripcio;
    }

    public void inscriureUsuari(Usuari usuariInscripcio, Data dataInscripcio) {
        if (!estaInscrit(usuariInscripcio)) {
            if (esPotInscriure(usuariInscripcio, activitatInscripcio, dataInscripcio)) {

                // si hi ha places disponibles, inscriure directament, si no, afegir a la llista d'espera
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
                throw new IllegalArgumentException("L'usuari no es pot inscriure a l'activitat. El tipus d'usuari no és vàlid o ja ha tancat el període d'inscripció.");
            }
        } else {
            throw new IllegalArgumentException("L'usuari ja està inscrit a l'activitat.");
        }
    }

    public void baixaUsuari(Usuari usuariBaixa) { // !! Posible reformateo para optimizar
        
        if (usuariEnLlista(usuariBaixa, usuarisInscrits)) { // L'usuari que es vol donar de baixa està inscrit a l'activitat

            for (int i = 0; i < usuarisInscrits.length; i++) {
                if (usuarisInscrits[i] != null && usuarisInscrits[i].getAlias().equals(usuariBaixa.getAlias())) {
    
                    // Eliminar l'usuari de la llista d'inscrits i desplaçar la resta
                    for (int j = i; j < usuarisInscrits.length - 1; j++) {
                        usuarisInscrits[j] = usuarisInscrits[j + 1];
                    }
    
                    // Si hi ha usuaris en espera, moure el primer a inscrits. Si no, alliberar plaça
                    if (usuarisEnEspera.length > 0) {
                        usuarisInscrits[usuarisInscrits.length - 1] = usuarisEnEspera[0];
                        // Desplaçar la llista d'espera
                        for (int k = 0; k < usuarisEnEspera.length - 1; k++) {
                            usuarisEnEspera[k] = usuarisEnEspera[k + 1];
                        }
                        usuarisEnEspera[usuarisEnEspera.length - 1] = null;
                    } else {
                        activitatInscripcio.alliberarPlaca();
                    }
                }
            }
        } else if (usuariEnLlista(usuariBaixa, usuarisEnEspera)) { // L'usuari que es vol donar de baixa està a la llista d'espera

            for (int i = 0; i < usuarisEnEspera.length; i++) {
                if (usuarisEnEspera[i] != null && usuarisEnEspera[i].getAlias().equals(usuariBaixa.getAlias())) {
    
                    // Eliminar l'usuari de la llista d'espera i desplaçar la resta
                    for (int j = i; j < usuarisEnEspera.length - 1; j++) {
                        usuarisEnEspera[j] = usuarisEnEspera[j + 1];
                    }
                    usuarisEnEspera[usuarisEnEspera.length - 1] = null;
                }
            }
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
        if (usuariEnLlista(usuariConsulta, usuarisInscrits) || usuariEnLlista(usuariConsulta, usuarisEnEspera)) {
            return true;
        }
        return false;
    }

    /**
     * Comprova si un usuari ja està inscrit a l'activitat.
     * @param usuariConsulta
     * @return boolean indicant si l'usuari està inscrit o no.
     */
    public boolean estaInscrit(Usuari usuariConsulta) {
        if (usuariEnLlista(usuariConsulta, usuarisInscrits)) {
            return true;
        }
        return false;
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

    private boolean usuariEnLlista(Usuari usuariConsulta, Usuari[] llistaUsuaris) {
        for (int i = 0; i < llistaUsuaris.length; i++) {
            if (llistaUsuaris[i] != null && llistaUsuaris[i].getAlias().equals(usuariConsulta.getAlias())) {
                return true;
            }
        }
        return false;
    }



    



}
