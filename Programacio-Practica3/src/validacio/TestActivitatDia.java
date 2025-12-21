package validacio;

import Activitats.ActivitatDia;
import Activitats.HoraIncorrecta;
import packages.Data;

public class TestActivitatDia {
    public static void main(String[] args) {
        System.out.println("Test de validació de activitat dia.");


        Data inici = new Data(1, 9, 2025);
        Data fi = new Data(15, 9, 2025);
        Data diaReale = new Data(20, 9, 2025);
        String[] col = {"PDI", "PTGAS"};

        try{
            // 1. crear objecte correcte
            System.out.println("\nProva 1: Creació d'activitat correcta (Matí)");
            ActivitatDia taller = new ActivitatDia(
                "Taller Basquet", 
                10.0f, 
                col, 
                inici, 
                fi, 
                diaReale, 
                9, 30,
                120,
                20,
                "Tarragona"
            );
            System.out.println("Resultat toString(): " + taller.toString());

            // 2. validar atributs especifics
            System.out.println("\nProva 2: validació dels atributs");
            if (taller.getHora() == 9 && taller.getMinut() == 30) {
                System.out.println("L'hora s'ha guardat correctament (9:30).");
            } else {
                System.out.println("L'hora no coincideix.");
            }

            if (taller.getCiutat().equals("Tarragona")) {
                System.out.println("La ciutat és correcta.");
            } else {
                System.out.println("La ciutat no és correcta.");
            }

            // 3. validar places i inscripció
            System.out.println("\nProva 3: Control de places.");
            System.out.println("Places ocupades inicials: " + taller.getPlacesOcupades());
            
            taller.ocuparPlaca();
            
            if (taller.getPlacesOcupades() == 1) {
                System.out.println("S'ha incrementat el comptador de places.");
            } else {
                System.out.println("No s'ha incrementat bé.");
            }
        } catch (HoraIncorrecta e) {
            System.out.println("Error: ha saltat l'excepció en una hora correcta");
            System.out.println(e.getMessage());
        }

        System.out.println("\nProva 4: Intentar crear una hora dolenta");
        
        try {
            // 4. validar excepcions
            ActivitatDia activitatDolenta = new ActivitatDia(
                "Activitat Impossible", 10, col, inici, fi, diaReale, 
                25, 0, 60, 20, "Reus"
            );
            
            System.out.println("Error: S'ha creat l'activitat");

        } catch (HoraIncorrecta e) {
            System.out.println("S'ha capturat l'excepció correctament.");
            System.out.println("Missatge de l'error rebut: " + e.getMessage());
        }
    }
}