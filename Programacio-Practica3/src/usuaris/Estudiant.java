package usuaris;

public class Estudiant extends Usuari{



    private int any;
    private String grau;



    
    public Estudiant(String alias, String adreça,int any, String grau) {
        super(alias,adreça, "Estudiant");
        this.any = any;
        this.grau = grau;
    }







    public int getAny() {
        return any;
    }
    public String getGrau() {
        return grau;
    }
    public void setAny(int any) {
        this.any = any;
    }
    public void setGrau(String grau) {
        this.grau = grau;
    }




    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+" Tipus:Estudiant";
    }

}
