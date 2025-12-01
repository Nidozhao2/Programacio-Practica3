package usuaris;

public class UsuariPTGAS extends Usuari {
    private String campus;

    public UsuariPTGAS(String alias, String adreça, String campus) {
        super(alias, adreça);
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String toString() {
        return "PTGAS;" + alias + ";" + adreça + ";" + campus;
    }
}