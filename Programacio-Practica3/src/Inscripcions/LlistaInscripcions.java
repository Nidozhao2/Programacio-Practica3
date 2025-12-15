package Inscripcions;

import Activitats.Activitat;
import Activitats.ActivitatOnline;
import usuaris.Usuari;

public class LlistaInscripcions {

    private Inscripcions inscripcions;
    private int nelems;
    private int maxelems;

    public LlistaInscripcions(Activitat activitat, Usuari usuari){

        this.nelems=0;

        if(activitat instanceof ActivitatOnline){
            
            this.maxelems=Integer.MAX_VALUE;
            
        }
        else{
            this.maxelems=activitat.getPlacesOcupades();
        }
    }
    
    
    inscripcions=new Inscripcions(usuari,nelems);

}