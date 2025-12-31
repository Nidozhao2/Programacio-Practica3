package usuaris;

/**
 * Versió bàsica de la classe LlistaUsuaris
 */
public class LlistaUsuaris {
    private Usuari[] usuaris;
    private int nUsuaris;
    private int capacitat;

    public LlistaUsuaris(int capacitat) {
        this.capacitat = capacitat;
        this.usuaris = new Usuari[capacitat];
        this.nUsuaris = 0;
    }

    public void afegirUsuari(Usuari nouUsuari) {
        if (nUsuaris < capacitat) {
            usuaris[nUsuaris] = nouUsuari;
            nUsuaris++;
        } else {
            throw new IllegalStateException("No es poden afegir més usuaris, capacitat màxima assolida.");
        }
    }

    public void eliminarUsuari(Usuari usuariAEliminar) {
        for (int i = 0; i < nUsuaris; i++) {
            if (usuaris[i].getAlias().equals(usuariAEliminar.getAlias())) {
                // Desplaçar els usuaris restants cap a l'esquerra
                for (int j = i; j < nUsuaris - 1; j++) {
                    usuaris[j] = usuaris[j + 1];
                }
                usuaris[nUsuaris - 1] = null; // Netejar l'última posició
                nUsuaris--;
            }
        }
    }

    public int getNusuaris() {
        return nUsuaris;
    }

    public Usuari[] getLlista() {
        return usuaris;
    }

    public Usuari getUsuari(int index) {
        if (index >= 0 && index < nUsuaris) {
            return usuaris[index];
        } else {
            throw new IndexOutOfBoundsException("Índex fora de límits.");
        }
    }

    public boolean usuariExisteix(Usuari usuariConsulta) {
        for (int i = 0; i < nUsuaris; i++) {
            if (usuaris[i].getAlias().equals(usuariConsulta.getAlias())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un usuari pel seu àlies.
     * Retorna l'objecte Usuari si el troba, o null si no existeix.
     */
    public Usuari getUsuari(String alias) {
        Usuari usuariTrobat = null;
        int i = 0;
        boolean trobat = false;

        while (i < nUsuaris && !trobat) {
            if (usuaris[i].getAlias().equals(alias)) {
                usuariTrobat = usuaris[i];
                trobat = true;
            }
            i++;
        }
        
        return usuariTrobat;
    }

    public LlistaUsuaris getEstudiants() {
        LlistaUsuaris llistaEstudiants = new LlistaUsuaris(capacitat);
        for (int i = 0; i < nUsuaris; i++) {
            if (usuaris[i] instanceof Estudiant) {
                llistaEstudiants.afegirUsuari(usuaris[i]);
            }
        }
        return llistaEstudiants;
    }

    public LlistaUsuaris getPdi() {
        LlistaUsuaris llistaPdi = new LlistaUsuaris(capacitat);
        for (int i = 0; i < nUsuaris; i++) {
            if (usuaris[i] instanceof UsuariPDI) {
                llistaPdi.afegirUsuari(usuaris[i]);
            }
        }
        return llistaPdi;
    }

    public LlistaUsuaris getPtgas() {
        LlistaUsuaris llistaPtgas = new LlistaUsuaris(capacitat);
        for (int i = 0; i < nUsuaris; i++) {
            if (usuaris[i] instanceof UsuariPTGAS) {
                llistaPtgas.afegirUsuari(usuaris[i]);
            }
        }
        return llistaPtgas;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Llista d'usuaris:\n");
        for (int i = 0; i < nUsuaris; i++) {
            sb.append(usuaris[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
