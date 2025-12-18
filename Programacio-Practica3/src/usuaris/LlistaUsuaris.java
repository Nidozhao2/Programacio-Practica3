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

    public int length() {
        return nUsuaris;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Llista d'usuaris:\n");
        for (int i = 0; i < nUsuaris; i++) {
            sb.append(usuaris[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
