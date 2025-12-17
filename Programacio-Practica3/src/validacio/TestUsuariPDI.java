package validacio;

import usuaris.UsuariPDI;

public class TestUsuariPDI {
    public static void main(String[] args) {
        System.out.println("Test de validació de l'usuari PTGAS.");

        // 1. crear l'usuari
        System.out.println("\nProva 1: Creació d'usuari");
        UsuariPDI usuari = new UsuariPDI("EricG", "eric.garciac", "Sescelades", "ETSE");
        
        System.out.println("Usuari creat: " + usuari.toString());

        // 2. validar campus
        System.out.println("\nProva 2: validar campus");
        if (usuari.getCampus().equals("Sescelades")) {
            System.out.println("El campus s'ha guardat bé (Sescelades).");
        } else {
            System.out.println("El campus no coincideix.");
        }

        // 3. validar modificaió campus
        System.out.println("\nProva 3: canviar campus");
        usuari.setCampus("Catalunya");
        if (usuari.getCampus().equals("Catalunya")) {
            System.out.println("El setter funciona, campus canviat a Catalunya.");
        } else {
            System.out.println("No s'ha canviat el campus.");
        }

        // 4. validar departament
        System.out.println("\nProva 4: validar departament");
        if (usuari.getDepartament().equals("ETSE")) {
            System.out.println("El departament s'ha guardat bé (ETSE).");
        } else {
            System.out.println("El departament no coincideix.");
        }

        System.out.println("\nFi del test.");
    }
}