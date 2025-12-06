package main;

import usuaris.Usuari;
import packages.*;
import Activitats.*;
import Inscripcions.*;

public class mainprograma {
    public static void main(String[] args) throws Exception {
        
        ActivitatPeriodiques activitat=new ActivitatPeriodiques("test", ["Usuari"], new Data(1, 1, 2000), new Data(1, 2, 2000),2 , 15,0 ,16,0, "CentreTGN", "TGN", 5,new  dataIniciActivitat(1,3,2000 ));
        System.out.println(ActivitatPeriodiques.getDurada());
    }
}
