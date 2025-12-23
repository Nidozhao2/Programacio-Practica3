package Inscripcions;

import Activitats.Activitat;
import Activitats.ActivitatOnline;
import usuaris.Usuari;
import packages.*;

/*TODO:
        - Método añadir elementos ordenados alfabéticamente por nombre de usuario (teniendo en cuenta límite de plazas de la actividad)
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
    private int nelems;
    private int maxelems;

    public LlistaInscripcions(int maxelems) {

        this.nelems = 0;
        this.maxelems = maxelems;
        this.inscripcions = new Inscripcions[maxelems];
        this.inscripcionsEspera = new Inscripcions[maxelems];

    }

    public void afegirInscripcio(Inscripcions inscripcio) {

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

    public int[] resumValoracions() {
        int[] llistaValoracions = new int[this.nelems];
        for (int i = 0; i < this.nelems; i++) {
            llistaValoracions[i] = this.inscripcions[i].getValoracioDonada();
        }
        return llistaValoracions;
    }

    public int[] resumValoracionsUsuari(Usuari usuariConsulta) {
        int[] llistaValoracionsUsuari = new int[this.nelems];
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.getAlias())) {
                llistaValoracionsUsuari[i] = this.inscripcions[i].getValoracioDonada();
            }
        }
        return llistaValoracionsUsuari;
    }

    public void valorarActivitat(Usuari usuariConsulta, Activitat activitatConsulta, int valoracio, Data dataActual) {
        int i = 0;
        boolean trobat = false;
        while (!trobat && i < this.nelems) {
            if (this.inscripcions[i].getActivitatInscripcio().haAcabat(dataActual)) {
                if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.getAlias()) && this.inscripcions[i].getActivitatInscripcio().getNom().equals(activitatConsulta.getNom())) {
                    this.inscripcions[i].valorar(valoracio);
                    trobat = true;
                }
            } else {

            }
            i++;
        }
    }

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

}