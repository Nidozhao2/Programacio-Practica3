package Inscripcions;

import packages.Data;
import Activitats.*;
import usuaris.Usuari;
import excepcions.*;
import java.io.Serializable;

/**
 * Classe Inscripcions
 * 
 * La classe Inscripcio gestiona les inscripcions dels usuaris a les activitats.
 * Cada instància representa una inscripció (relació) entre una activitat i un
 * usuari,
 * amb un espai per la valoració de l'usuari a la seva activitat inscrita.
 * 
 */
public class Inscripcions implements Serializable {

    private Usuari usuariInscrit;
    private Activitat activitatInscripcio;
    private float valoracioDonada;
    private Data dataInscripcio;

    public Inscripcions(Activitat activitatInscripcio, Usuari usuariInscrit, Data dataInscripcio)
            throws ForaDePeriode, TipusUsuariNoValid {
        if (activitatInscripcio.estaEnPeriodeInscripcio(dataInscripcio)) {
            if (activitatInscripcio.validarUsuari(usuariInscrit.getTipus())) {
                this.usuariInscrit = usuariInscrit;
                this.activitatInscripcio = activitatInscripcio;
                this.dataInscripcio = dataInscripcio;
            } else {
                throw new TipusUsuariNoValid("El tipus d'usuari no és valid per a la activitat");
            }
        } else {
            throw new ForaDePeriode("La activitat es troba fora del període d'inscripció");

        }
    }

    public void valorar(float valoracio, Data dataInscripcio, Usuari usuariInscrit)
            throws ActivitatNoAcabada, UsuariExtrany {
        if (!this.usuariInscrit.getAlias().equals(usuariInscrit.getAlias())) {
            throw new UsuariExtrany("L'usuari que vol valorar la inscripcio no és el mateix que està inscrit");
        }
        if (this.activitatInscripcio.haAcabat(dataInscripcio)) {
            this.valoracioDonada = valoracio;
        } else {
            throw new ActivitatNoAcabada("La activitat no ha acabat encara");
        }
    }

    public Usuari getUsuariInscrit() {
        return usuariInscrit;
    }

    public Activitat getActivitatInscripcio() {
        return activitatInscripcio;
    }

    public float getValoracioDonada() {
        return valoracioDonada;
    }

    public Data getDataInscripcio() {
        return dataInscripcio;
    }

    public String toString() {
        return "Inscripcions [usuariInscrit=" + usuariInscrit + ", activitatInscripcio=" + activitatInscripcio
                + ", valoracioDonada=" + valoracioDonada + ", dataInscripcio=" + dataInscripcio + "]";
    }

    public boolean mateixaActivitat(Inscripcions altraInscripcio) {
        return this.activitatInscripcio.equals(altraInscripcio.getActivitatInscripcio());
    }

}
