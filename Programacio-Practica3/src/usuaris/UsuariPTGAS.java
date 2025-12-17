package usuaris;

public class UsuariPTGAS extends Usuari {
    private String campus;

    public UsuariPTGAS(String alias, String adreça, String campus) {
        super(alias, adreça, "PTGAS");
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+" Tipus:PTGAS";
    }


}