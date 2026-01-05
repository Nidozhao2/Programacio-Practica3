package inscripcions;

import activitats.*;
import excepcions.InscripcioNoTrobada;
import excepcions.TaulaPlena;
import usuaris.Usuari;
import packages.*;

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
     * @param inscripcio
     * @throws TaulaPlena 
     */
    public void afegirInscripcio(Inscripcions inscripcio) throws TaulaPlena {
        if (inscripcio != null) {
            
            // Es comprova primer que no existeixi duplicat
            if (!this.existeixInscripcio(inscripcio)) {

                // Si no existeix, mirem si hi ha lloc a l'activitat
                if (inscripcio.getActivitatInscripcio().tePlacesDisponibles()) {
                    inscripcio.getActivitatInscripcio().ocuparPlaca();
                    this.inscripcions[this.nelems] = inscripcio;
                    this.nelems++;
                    ordenarInscripcions(this.inscripcions);
                } 
                
                // Si no hi ha lloc a l'activitat, mirem la llista d'espera
                else if (this.nelemsEspera < maxelems) {
                    this.inscripcionsEspera[this.nelemsEspera] = inscripcio;
                    this.nelemsEspera++;
                } 
                
                // Si tot està ple
                else {
                    throw new TaulaPlena("No queden places ni lloc a la llista d'espera.");
                }

            } else {
                // Si hi ha duplicat llencem l'excepcio
                throw new TaulaPlena("Error: L'usuari ja té una inscripció per aquesta activitat (Duplicat).");
            }
        }
    }

    /**
     * Mètode privat per ordenar les inscripcions alfabèticament per nom d'usuari
     * mitjançant l'algorisme de la bombolla.
     */
    private void ordenarInscripcions(Inscripcions[] inscripcions) { 
        for (int i = 0; i < this.nelems - 1; i++) {
            for (int j = 0; j < this.nelems - i - 1; j++) {
                if (inscripcions[j].getUsuariInscrit().getAlias()
                        .compareTo(inscripcions[j + 1].getUsuariInscrit().getAlias()) > 0) {
                    Inscripcions temp = inscripcions[j];
                    inscripcions[j] = inscripcions[j + 1];
                    inscripcions[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Mètode per comprovar si una inscripció ja existeix a la llista d'inscripcions o a la llista d'espera
     * @param inscripcioCercar
     * @return
     */
    public boolean existeixInscripcio(Inscripcions inscripcioCercar) {
        for (int i = 0; i < nelems; i++) {
            if (inscripcioCercar.equals(this.inscripcions[i])) {
                return true;
            }
        }
        for (int j = 0; j < nelemsEspera; j++) {
            if (inscripcioCercar.equals(this.inscripcionsEspera[j])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina una inscripció de la llista d'inscripcions o de la llista d'espera i
     * mou una inscripció de la llista d'espera a la llista d'inscripcions si escau.
     * @param inscripcioBorrar
     * @throws InscripcioNoTrobada
     */
    public void eliminarInscripcio(Inscripcions inscripcioBorrar) throws InscripcioNoTrobada {
        for (int i = 0; i < nelems; i++) { //buscar en inscripcions principals
            if (inscripcioBorrar.equals(this.inscripcions[i])) {
                
                    int k=0;
                    boolean trobat = false;
                    
                    while(!trobat && k < nelemsEspera) { //busque en la llista de espera
                        if (this.inscripcionsEspera[k].mateixaActivitat(inscripcioBorrar) ) {
                            this.inscripcions[i] = this.inscripcionsEspera[k];
                            trobat = true; 
                        }
                        k++;
                    }

                    if(trobat) {
                        for ( int j=k; j < nelemsEspera - 1; j++) { //moure llista d'espera si hem trobat el element
                            this.inscripcionsEspera[j] = this.inscripcionsEspera[j + 1];
                        }
                        this.inscripcionsEspera[nelemsEspera - 1] = null; //eliminar últim element repetit
                        nelemsEspera--;
                        ordenarInscripcions(this.inscripcions);
                    }
 
                    else {
                        for (k = i; k < nelems - 1; k++) {
                            this.inscripcions[k] = this.inscripcions[k + 1];
                        }
                        this.inscripcions[nelems - 1] = null;
                        nelems--;
                        }
            }
                return; 
            }
        
        
        for (int j = 0; j < nelemsEspera; j++) {//buscar en inscripcions en espera
            if (inscripcioBorrar.equals(this.inscripcionsEspera[j])) {
                for (int k = j; k < nelemsEspera - 1; k++) {
                    this.inscripcionsEspera[k] = this.inscripcionsEspera[k + 1];
                }
                this.inscripcionsEspera[nelemsEspera - 1] = null;
                nelemsEspera--;
                return; 
            }
        }
        
        //si no s'ha trobat en cap de les dues llistes, llençar excepció
        throw new InscripcioNoTrobada("Inscripció no trobada");
    }

    /**
     * Filtra la llista d'inscripcions per un usuari concret
     * @param usuariConsulta
     * @return llista filtrada
     * @throws TaulaPlena
     */
    public LlistaInscripcions inscripcionsUsuari(Usuari usuariConsulta) throws TaulaPlena {
        LlistaInscripcions llistaInscripcionsUsuari = new LlistaInscripcions(this.maxelems);
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(usuariConsulta.getAlias())) {
                llistaInscripcionsUsuari.afegirInscripcio(this.inscripcions[i]);
            }
        }
        return llistaInscripcionsUsuari;
    }

    /**
     * Filtra la llista d'inscripcions per una activitat concreta
     * @param nomActivitat
     * @return llista filtrada
     * @throws TaulaPlena
     */
    public LlistaInscripcions inscripcionsActivitat(String nomActivitat) throws TaulaPlena {
        LlistaInscripcions llistaInscripcionsActivitat = new LlistaInscripcions(this.maxelems);
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getActivitatInscripcio().getNom().equals(nomActivitat)) {
                llistaInscripcionsActivitat.afegirInscripcio(this.inscripcions[i]);
            }
        }
        return llistaInscripcionsActivitat;
    }
 
    /**
     * Resum de les valoracions d'una activitat concreta
     * @param nomActivitat
     * @return llista de valoracions
     */
    public int[] resumValoracionsActivitat(String nomActivitat) {
        int[] llistaValoracions = new int[this.nelems];
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getValoracioDonada() != 0 && this.inscripcions[i].getActivitatInscripcio().getNom().equals(nomActivitat)) {
                llistaValoracions[i] = this.inscripcions[i].getValoracioDonada();
            }
        }
        return llistaValoracions;
    }

    /**
     * Calcula la mitjana de les valoracions d'una activitat concreta
     * @param nomActivitat
     * @return mitjana de valoracions | 0 si no hi ha valoracions
     */
    public float getMitjanaValoracionsActivitat(String nomActivitat) {
        int sumaValoracions = 0;
        int numValoracions = 0;
        int[] valoracions = resumValoracionsActivitat(nomActivitat);
        for (int i = 0; i < valoracions.length; i++) {
            if (valoracions[i] != 0) {
                sumaValoracions += valoracions[i];
                numValoracions++;
            }
        }
        if (numValoracions == 0) {
            return 0;
        } else {
            return (float) sumaValoracions / numValoracions;
        }
    }

    /**
     * Calcula la mitjana de les valoracions d'un colectiu concret
     * @param collectiu
     * @return mitjana de valoracions | 0 si no hi ha valoracions
     */
    public float getMitjanaValoracionsCollectiu(String collectiu) {
        int sumaValoracions = 0;
        int nValoracions = 0;
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getTipus().equals(collectiu) && this.inscripcions[i].getValoracioDonada() != 0) {
                sumaValoracions += this.inscripcions[i].getValoracioDonada();
                nValoracions++;
            }
        }
        if (nValoracions == 0) {
            return 0;
        }
        float mitjana = (float) sumaValoracions / nValoracions;
        return mitjana;
    }

    /**
     * Resum de les valoracions d'un usuari concret
     * @param aliasUsuari
     * @return String amb una llista de les valoracions i la mitjana
     */
    public String getResumValoracionsUsuari(String aliasUsuari) {
        StringBuffer sb = new StringBuffer();
        int sumaValoracions = 0;
        int nValoracions = 0;
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getAlias().equals(aliasUsuari) && this.inscripcions[i].getValoracioDonada() != 0) {
                sb.append(this.inscripcions[i].getActivitatInscripcio().getNom() + ": " + this.inscripcions[i].getValoracioDonada() + "\n");
                sumaValoracions = sumaValoracions + this.inscripcions[i].getValoracioDonada();
                nValoracions++;
            }
        }
        float mitjana = (float) sumaValoracions / nValoracions;

        if (sb.length() == 0) {
            return "L'usuari " + aliasUsuari + " no té valoracions.";
        } else {
            sb.append("Mitjana de valoracions de l'usuari: " + mitjana + "\n");
            return sb.toString();
        }
    }

    /**
     * Filtra la llista d'inscripcions per el colectiu passat per paràmetre
     * @param collectiu
     * @return llista filtrada
     */
    public LlistaInscripcions filtrarPerCollectiu(String collectiu) {
        LlistaInscripcions novaLlista = new LlistaInscripcions(this.maxelems);
        for (int i = 0; i < this.nelems; i++) {
            if (this.inscripcions[i].getUsuariInscrit().getTipus().equals(collectiu)) {
                try {
                    novaLlista.afegirInscripcio(this.inscripcions[i]);
                } catch (TaulaPlena e) {
                    System.out.println(e);
                }
            }
        }
        return novaLlista;
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

    public String toString() {
        String s = "Inscripcions:\n";
        for (int i = 0; i < this.nelems; i++) {
            s += this.inscripcions[i] + "\n";
        }
        if (this.nelemsEspera > 0) {
            s += "Llista d'espera:\n";
            for (int i = 0; i < this.nelemsEspera; i++) {
                s += this.inscripcionsEspera[i];
            }
        }
        return s;
    }

}