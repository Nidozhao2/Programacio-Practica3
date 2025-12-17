package usuaris;

public abstract class Usuari {
    protected String alias;
    protected String adreça;
    protected String tipus;

    public Usuari(String alias, String adreça, String tipus) {
        this.alias = alias;
        this.adreça = adreça;
        this.tipus = tipus;
    }

    public String toString(){

        return "Alias: "+alias+" Adreça:" + adreça+" Tipus:";

    }

}
