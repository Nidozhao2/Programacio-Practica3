package Inscripcions;

import usuaris.Usuari;

public class Inscripcions {

    private Usuari usuariInscripcio;
    private Inscripcions seguentInscripcio;
    private int iusuari; //index de llista inscripcions

    public Inscripcions(Usuari usuariInscripcio,int iusuari) {
        this.usuariInscripcio=usuariInscripcio;
        this.iusuari=iusuari;
        seguentInscripcio=null;
    }

    



}
