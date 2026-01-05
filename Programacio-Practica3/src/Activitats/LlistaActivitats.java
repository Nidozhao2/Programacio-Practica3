package activitats;

import packages.*;

public class LlistaActivitats {

    private Activitat[] llista;
    private int nElems;

    public LlistaActivitats(int capacitat) {
        this.llista = new Activitat[capacitat];
        this.nElems = 0;
    }

    public void afegirActivitat(Activitat a) throws TaulaPlena {

        if (nElems >= llista.length) {
            throw new TaulaPlena("Error: La llista està plena. No es pot afegir: " + a.getNom());
        } else {
            Activitat existent = getActivitat(a.getNom());

            if (existent == null) {
                llista[nElems] = a;
                nElems++;
            } else {
                System.out.println("Error: Ja existeix una activitat amb el nom: " + a.getNom());
            }
        }
    }

    public Activitat getActivitat(String nom) {
        Activitat trobada = null;
        boolean trobat = false;
        int i = 0;

        while (i < nElems && !trobat) {
            if (llista[i].getNom().equalsIgnoreCase(nom)) {
                trobada = llista[i];
                trobat = true;
            } else {
                i++;
            }
        }

        return trobada;
    }

    public Activitat getActivitat(int index){
        if (index >= 0 && index < this.nElems) {
            return this.llista[index];
        } else {
            return null;
        }
    }

    public void esborrarActivitat(String nom) {
        boolean trobat = false;
        int i = 0;

        while (i < nElems && !trobat) {
            if (llista[i].getNom().equalsIgnoreCase(nom)) {
                trobat = true;
            } else {
                i++;
            }
        }

        if (trobat) {
            for (int j = i; j < nElems - 1; j++) {
                llista[j] = llista[j + 1];
            }

            llista[nElems - 1] = null;
            nElems--;
            System.out.println("Activitat '" + nom + "' esborrada correctament.");

        } else {
            System.out.println("Error: No s'ha trobat l'activitat per esborrar.");
        }
    }

    public int getNElems() {
        return nElems;
    }

    public Activitat[] getLlista() {
        return llista;
    }

    public int getCapacitat() {
        return llista.length;
    }

    public String toString() {
        String res = "Cataleg (" + nElems + "/" + llista.length + ") \n";
        for (int i = 0; i < nElems; i++) {
            res += (i + 1) + ". " + llista[i].toString() + "\n";
        }
        return res;
    }

    public String getDiaries() {
        String res = "Diaries (" + nElems + "/" + llista.length + ") \n";
        for (int i = 0; i < nElems; i++) {
            if (llista[i] instanceof ActivitatDia) {
                res += (i + 1) + ". " + llista[i].toString() + "\n";
            }
        }
        return res;
    }

    public String getPeriodiques() {
        String res = "Periodiques (" + nElems + "/" + llista.length + ") \n";
        for (int i = 0; i < nElems; i++) {
            if (llista[i] instanceof ActivitatPeriodiques) {
                res += (i + 1) + ". " + llista[i].toString() + "\n";
            }
        }
        return res;
    }

    public String getEnLinea() {
        String res = "EnLinea (" + nElems + "/" + llista.length + ") \n";
        for (int i = 0; i < nElems; i++) {
            if (llista[i] instanceof ActivitatOnline) {
                res += (i + 1) + ". " + llista[i].toString() + "\n";
            }
        }
        return res;
    }

    public LlistaActivitats getActivitatsPeriodeInscripcio(Data dataActual) {
        LlistaActivitats res = new LlistaActivitats(llista.length);

        for (int i = 0; i < nElems; i++) {
            if (llista[i].estaEnPeriodeInscripcio(dataActual)) {
                try {
                    res.afegirActivitat(llista[i]);
                } catch (TaulaPlena e) {
                    break;
                }
            }
        }
        return res;
    }

    public LlistaActivitats getActivitatsAcabades(Data dataActual) {
        LlistaActivitats llistaActivitatsAcabades = new LlistaActivitats(this.nElems);
        for (int i = 0; i < this.nElems; i++) {
            if (llista[i].haAcabat(dataActual)) {
                try {
                    llistaActivitatsAcabades.afegirActivitat(llista[i]);
                } catch (TaulaPlena e) {
                    System.out.println("Error: No es poden afegir més activitats acabades a la llista.");
                }
            }
        }
        return llistaActivitatsAcabades;

    }

    public LlistaActivitats getActivitatsActivesEnData(Data data) {
        LlistaActivitats llistaActivitatsActives = new LlistaActivitats(this.nElems);
        for (int i = 0; i < this.nElems; i++) {
            if (llista[i].activaEnDia(data)) {
                try {
                    llistaActivitatsActives.afegirActivitat(llista[i]);
                } catch (TaulaPlena e) {
                    System.out.println("Error: No es poden afegir més activitats actives a la llista.");
                }
            }
        }
        return llistaActivitatsActives;
    }
}