package validacio;

import activitats.*;
import excepcions.*;
import inscripcions.Inscripcions;
import inscripcions.LlistaInscripcions;
import packages.Data;
import usuaris.Estudiant;
import usuaris.UsuariPTGAS;

public class TestLlistaInscripcions {

    
    public static void main(String[] args) throws Exception {
        LlistaInscripcions llista=new LlistaInscripcions(10);
        

        String [] colectius=new String[2];
        colectius[0]="PDI";
        colectius[1]="PTGAS";

        Data iniciIns=new Data(1, 1, 2000);
        Data fiIns=new Data(3,1,2000);
        Data dataIniciActivitat=new Data(10,1,2000);

        ActivitatPeriodiques ap = new ActivitatPeriodiques(
                "Curs de Ioga", 
                30.0f, 
                colectius, 
                iniciIns, 
                fiIns,
                20, 
                dataIniciActivitat, 
                3,
                10, 0, // 10:00
                11, 30, // 11:30
                "Gimnàs URV", 
                "Tarragona"
        );

UsuariPTGAS usuari = new UsuariPTGAS("pau.segarrae", "pau.segarrae", "Sescelades");

        Estudiant es=new Estudiant(null, null, 0, null);

        System.out.println("GetNumeroInscripcions:    Esperat:0 Obtingut:"+llista.getNumeroInscripcions());
        
        llista.afegirInscripcio(new Inscripcions(ap,usuari,new Data(2,1,2000)));
        
        System.out.println("GetNumeroInscripcions:    Esperat:1 Obtingut:"+llista.getNumeroInscripcions());
        
        
    }


    


}
