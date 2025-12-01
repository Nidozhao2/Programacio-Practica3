package validacio;

import Activitats.ActivitatDia;
import packages.Data;

public class TestActivitatDia {
    public static void main(String[] args) {
        System.out.println("Test de validació de activitat dia.");


        Data inici = new Data(1, 9, 2025);
        Data fi = new Data(15, 9, 2025);
        Data diaReale = new Data(20, 9, 2025);
        String[] col = {"PDI", "PTGAS"};

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
            20, 
            "Tarragona"
        );
        System.out.println("Resultat toString(): " + taller.toString());

        // 2. validar atributs especifics
        System.out.println("\nProva 2: validació dels atributs");
        if (taller.getHora() == 9 && taller.getMinuts() == 30) {
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
    }
}