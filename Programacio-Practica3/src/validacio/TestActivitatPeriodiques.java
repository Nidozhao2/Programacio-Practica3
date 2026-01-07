package validacio;

import activitats.ActivitatPeriodiques;
import packages.Data;

public class TestActivitatPeriodiques {
    public static void main(String[] args) {
        System.out.println("Test activitat periodiques");


        Data iniciIns = new Data(1, 1, 2025);
        Data fiIns = new Data(15, 1, 2025);
        

        Data dataIniciActivitat = new Data(20, 1, 2025);
        
        String[] colectius = { "PDI", "Estudiant" };


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


        System.out.println("\n1) prova de dades i durada");

        String info = ap.toString();
        System.out.println(info);
        
        if (info.contains("Durada: 90 minuts")) {
            System.out.println("Correcte: la durada s'ha calculat be (90 min)");
        } else {
            System.out.println("Incorrecte: durada mal calculada");
        }

        
        System.out.println("\n2) Prova de dies actius");
        
      
        if (ap.activaEnDia(new Data(20, 1, 2025))) {
            System.out.println("Correcte: activa el dia d'inici");
        } else {
            System.out.println("Incorrecte: no reconeix el dia d'inici");
        }

     
        if (ap.activaEnDia(new Data(27, 1, 2025))) {
            System.out.println("Correcte: activa 1 setmana despues");
        } else {
            System.out.println("Incorrecte: no reconeix la setmana seguent");
        }

    
        if (!ap.activaEnDia(new Data(21, 1, 2025))) {
            System.out.println("correcte: no activa un dia random pel mig");
        } else {
            System.out.println("incorrecte: diu que es activa un dia que no toca");
        }

     
        if (!ap.activaEnDia(new Data(1, 5, 2025))) {
            System.out.println("Correcte: no activa mes enlla de les setmanes totals");
        } else {
            System.out.println("Incorrecte: segueix activa massa temps");
        }

        System.out.println("\n3) Prova de canvi d'horari");
        ap.setHoraFinal(12);
        ap.setMinutFinal(0);
        
        if (ap.toString().contains("Durada: 120 minuts")) {
            System.out.println("Correcte: durada actualitzada al canviar hora final");
        } else {
            System.out.println("Incorrecte: no s'ha actualitzat la durada");
        }

        System.out.println("\nFi del test");
    }
}