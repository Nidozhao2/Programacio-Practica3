package Inscripcions;

import Activitats.Activitat;
import usuaris.Usuari;
import packages.*;

/*TODO:
        - Método añadir elementos ordenados alfabéticamente por nombre de usuario (teniendo en cuenta límite de plazas de la actividad y inscripción repetida)
        - Método eliminar elemento y mover de inscripcionsEspera a inscripcions
        - Método recorrido para obtener las actividades a las que está apuntado un usuari (hecho)
        - Método recorrido para obtener resumen de valoraciones de las inscripciones (hecho)
        - Método recorrido para obtener resumen de valoraciones de un usuario en concreto (hecho)
        - Método recorrido para calcular media de valoraciones de cada colectivo
        - Método recorrido para obtener usuario con más inscripciones (hecho)
        - Método cerca para valorar una actividad por un usuario*/
public class LlistaInscripcions {

    private Inscripcions[] inscripcions;
    private Inscripcions[] inscripcionsEspera;
    private int nelemsEspera; // número de inscripcions en espera
    private int nelems; // número de inscripcions
    private int maxelems;

    public LlistaInscripcions(int maxelems) {

        this.nelems = 0;
        this.nelemsEspera = 0;
        this.maxelems = maxelems;
        this.inscripcions = new Inscripcions[maxelems];
        this.inscripcionsEspera = new Inscripcions[maxelems];

    }

    public int getNumeroInscripcions() {
        return this.nelems;
    }

    public int getNumeroInscripcionsEspera() {
        return this.nelemsEspera;
    }

    public Inscripcions getInscripcio(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.nelems) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.inscripcions[i];
    }

    public Inscripcions getInscripcioEspera(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.nelemsEspera) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return this.inscripcionsEspera[i];
    }

    /**
     * métode privat per contar les inscripcions d'una activitat al moment de la
     * inserció d'una nova inscripcio,
     * amb l'objectiu de controlar que no s'excedeixi el límit de plaçes de
     * l'activitat
     * 
     * @param nomActivitat
     * @return
     */
    private int contarInscripcionsActivitat(String nomActivitat) {
        int contador = 0;
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getActivitatInscripcio().getNom().equals(nomActivitat)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Afegeix una inscripcio a la llista d'inscripcions o d'inscripcions en espera.
     * !! PROVISIONAL !!
     * Falta ordenació per nom d'usuari, control de limit de plaçes i control de
     * repetició.
     * 
     * @param inscripcio
     */
    public void afegirInscripcio(Inscripcions inscripcio) {
        if (inscripcio != null) {
            if (this.nelems < this.maxelems) {
                this.inscripcions[this.nelems] = inscripcio;
                this.nelems++;
            } else if (this.nelemsEspera < this.maxelems) {
                this.inscripcionsEspera[this.nelemsEspera] = inscripcio;
                this.nelemsEspera++;
            }
        }
    }

    public void eliminarInscripcio(Inscripcions inscripcio) {

    }

    public LlistaInscripcions inscripcionsUsuari(Usuari usuariConsulta) {
        LlistaInscripcions llistaInscripcionsUsuari = new LlistaInscripcions(this.maxelems);
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.getAlias())) {
                llistaInscripcionsUsuari.afegirInscripcio(this.inscripcions[i]);
            }
        }
        return llistaInscripcionsUsuari;
    }

    public float[] resumValoracions() {
        float[] llistaValoracions = new float[this.nelems];
        for (int i = 0; i < this.nelems; i++) {
            llistaValoracions[i] = this.inscripcions[i].getValoracioDonada();
        }
        return llistaValoracions;
    }

    public float[] resumValoracionsUsuari(Usuari usuariConsulta) {
        float[] llistaValoracionsUsuari = new float[this.nelems];
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.getAlias())) {
                llistaValoracionsUsuari[i] = this.inscripcions[i].getValoracioDonada();
            }
        }
        return llistaValoracionsUsuari;
    }

    /*
     * public void valorarActivitat(Usuari usuariConsulta, Activitat
     * activitatConsulta, int valoracio, Data dataActual) {
     * int i = 0;
     * boolean trobat = false;
     * while (!trobat && i < this.nelems) {
     * if (this.inscripcions[i].getActivitatInscripcio().haAcabat(dataActual)) {
     * if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.
     * getAlias())
     * && this.inscripcions[i].getActivitatInscripcio().getNom().equals(
     * activitatConsulta.getNom())) {
     * this.inscripcions[i].valorar(valoracio);
     * trobat = true;
     * }
     * } else {
     * 
     * }
     * i++;
     * }
     * }
     */

    /**
     * Retorna l'usuari amb més inscripcions.
     * IMPORTANT: És imprescindible que la llista estigui ordenada per nom de usuari
     * per a que aquest mètode funcioni correctament.
     * 
     * @return
     */
    public Usuari usuariAmbMesInscripcions() {
        Usuari usuariAmbMesInscripcions = this.inscripcions[0].getUsuariInscrit();
        Usuari usuariActual = this.inscripcions[0].getUsuariInscrit();
        int maxInscripcions = 0;
        int inscripcionsUsuari = 0;

        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariActual.getAlias())) {
                inscripcionsUsuari++;

                if (inscripcionsUsuari > maxInscripcions) {
                    maxInscripcions = inscripcionsUsuari;
                    usuariAmbMesInscripcions = usuariActual;
                }

            } else {
                usuariActual = this.inscripcions[i].getUsuariInscrit();
                inscripcionsUsuari = 1;
            }
        }
        return usuariAmbMesInscripcions;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.nelems; i++) {
            s += this.inscripcions[i] + "\n";
        }
        return s;
    }

}