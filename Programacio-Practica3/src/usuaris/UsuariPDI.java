package usuaris;

public class UsuariPDI extends Usuari {
    private String departament;
    private String campus;

    public UsuariPDI(String alias, String adreça, String campus, String departament) {
        super(alias, adreça, "PDI");
        this.departament = departament;
        this.campus = campus;
    }

    public String getDepartament() {
        return departament;
    }

    public String getCampus() {
        return campus;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String toString() {
        return "PDI;" + alias + ";" + adreça + ";" + departament + ";" + campus;
    }
}
