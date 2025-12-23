package Inscripcions;

import packages.Data;
import Activitats.*;
import usuaris.Usuari;
import usuaris.LlistaUsuaris;
import excepcions.*;

/**
 * Classe Inscripcions
 * 
 * La classe Inscripcio gestiona les inscripcions dels usuaris a les activitats.
 * Cada instància representa una inscripció (relació) entre una activitat i un
 * usuari,
 * amb un espai per la valoració de l'usuari a la seva activitat inscrita.
 * 
 */
public class Inscripcions {

    private Usuari usuariInscrit;
    private Activitat activitatInscripcio;
    private int valoracioDonada;
    private Data dataInscripcio;

    public Inscripcions(Activitat activitatInscripcio, Usuari usuariInscrit, Data dataInscripcio)
            throws ForaDePeriode, TipusUsuariNoValid {
        if (activitatInscripcio.estaEnPeriodeInscripcio(dataInscripcio)) {
            if (activitatInscripcio.validarUsuari(usuariInscrit.getTipus())) {
                this.usuariInscrit = usuariInscrit;
                this.activitatInscripcio = activitatInscripcio;
            } else {
                throw new TipusUsuariNoValid("El tipus d'usuari no és valid per a la activitat");
            }
        } else {
            throw new ForaDePeriode("La activitat es troba fora del període d'inscripció");

        }
    }

    public void valorar(int valoracio) {
        this.valoracioDonada = valoracio;
    }

    public Usuari getUsuariInscrit() {
        return usuariInscrit;
    }

    public Activitat getActivitatInscripcio() {
        return activitatInscripcio;
    }

    public int getValoracioDonada() {
        return valoracioDonada;
    }

    public Data getDataInscripcio() {
        return dataInscripcio;
    }

}
