package main;

import usuaris.Usuari;
import packages.*;
import Activitats.*;
import Inscripcions.*;

public class mainprograma {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Inscripcions prova=new Inscripcions("algo");

        System.out.println(prova.prova);
    }
}
