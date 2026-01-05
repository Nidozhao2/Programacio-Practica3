package usuaris;

import java.io.Serializable;

import activitats.*;

public abstract class Usuari implements Serializable {
    protected String alias;
    protected String adreça;
    protected String tipus;

    public Usuari(String alias, String adreça, String tipus) {
        this.alias = alias;
        this.adreça = adreça;
        this.tipus = tipus;
        
    }

    public String toString() {

        return "Alias: " + alias + " Adreça:" + adreça + " Tipus:";

    }

    public String getTipus() {
        return tipus;
    }

    public String getAlias() {
        return alias;
    }

    public String getAdreça() {
        return adreça;
    }
}
