package activitats;

import packages.*;

public class ActivitatOnline extends Activitat {

    private String enllaç;
    private int periodeVisualitzacio; // en dies

    public ActivitatOnline(String nom, String[] colectius, Data dataIniciInscripcio, Data dataFiInscripcio,
            Data dataActivitat, int periodeVisualitzacio, String enllaç) {
        super(nom, 0, colectius, dataIniciInscripcio, dataFiInscripcio, Integer.MAX_VALUE, dataActivitat);
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllaç = enllaç;
        setFiActivitat();
    }

    public void setFiActivitat() {
        // calculem la data fi de l'activitat
        Data fiActivitat = dataActivitat.copia();
        for (int i = 0; i < periodeVisualitzacio; i++) {
            fiActivitat = fiActivitat.diaSeguent();
        }
        super.fiActivitat = fiActivitat;
    }

    public String getEnllaç() {
        return enllaç;
    }

    public void setEnllaç(String enllaç) {
        this.enllaç = enllaç;
    }

    public int getPeriodeVisualitzacio() {
        return periodeVisualitzacio;
    }
    public void setPeriodeVisualitzacio(int periodeVisualitzacio) {
        this.periodeVisualitzacio = periodeVisualitzacio;
    }

    public String toString() {
        return "Activitat Online: " + nom + ", Enllaç: " + enllaç + ", Preu: " + preu + "€, Data Inici Inscripció: "
                + dataIniciInscripcio.toString() + ", Data Fi Inscripció: " + dataFiInscripcio.toString();
    }

    public boolean activaEnDia(Data data) {
        Data dataFiActivitat = dataActivitat.copia();
        for (int i = 0; i < periodeVisualitzacio; i++) {
            dataFiActivitat = dataFiActivitat.diaSeguent();
        }
        return (data.comparaAmb(dataActivitat) > 0 && data.comparaAmb(dataFiActivitat) <= 0);
    }

    public boolean classeEnDia(Data data) {
        return activaEnDia(data);
    }

    // Sempre hi ha plaçes disponibles en activitats online
    @Override
    public boolean tePlacesDisponibles() {
        return true;
    }

}
