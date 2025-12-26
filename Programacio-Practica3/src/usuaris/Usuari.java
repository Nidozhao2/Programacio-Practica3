package usuaris;

import Activitats.*;
import java.io.Serializable;

public abstract class Usuari implements Serializable {
    protected String alias;
    protected String adreça;
    protected String tipus;

    protected Activitat[] activitatsusuari;// se puede argumentar

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
}
