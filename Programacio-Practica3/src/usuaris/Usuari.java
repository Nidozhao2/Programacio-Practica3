package usuaris;

public abstract class Usuari {
    protected String alias;
    protected String adreça;

    public Usuari(String alias, String adreça) {
        this.alias = alias;
        this.adreça = adreça;
    }
}
