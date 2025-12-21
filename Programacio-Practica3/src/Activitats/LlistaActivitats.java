package Activitats;

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
}