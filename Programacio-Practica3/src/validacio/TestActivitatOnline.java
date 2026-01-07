package validacio;

import activitats.ActivitatOnline;
import packages.Data;

public class TestActivitatOnline {
    public static void main(String[] args) {
        System.out.println("Test de validació de activitat dia.");

        Data dataIniciInscripcio = new Data(1, 9, 2025);
        Data dataFiInscripcio = new Data(15, 9, 2025);
        Data dataIniciActivitat = new Data(18, 9, 2025);
        String[] col = { "Estudiants", "PDI" };

        
        System.out.println("\nProva 1: Creació d'activitat correcta (Matí)");
        ActivitatOnline taller = new ActivitatOnline(
                "Introducció a la IA",
                col,
                dataIniciInscripcio,
                dataFiInscripcio,
                dataIniciActivitat,
                15,
                "www.exemple.com/ia-course");
        System.out.println("Resultat toString(): " + taller.toString());

        
        System.out.println("\nProva 2: validació dels atributs");
        if (taller.getEnllaç().equals("www.exemple.com/ia-course")) {
            System.out.println("L'enllaç s'ha guardat correctament (www.example.com/ia-course).");
        } else {
            System.out.println("L'enllaç no coincideix.");
        }

        if (taller.getFiActivitat().esIgualA(new Data(3, 10, 2025))) {
            System.out.println("La data de fi d'activitat és correcta (dataIniciActivitat + 15 dies).");
        } else {
            System.out.println("La data de fi d'activitat no és correcta: " + taller.getFiActivitat());
        }

     
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