package validacio;
import Inscripcions.Inscripcions;
import usuaris.*;
import Activitats.*;
import packages.Data;

public class TestInscripcions {
    public static void main(String[] args) {
        System.out.println("--- Test de validació d'inscripcions ---");

        // Crear usuari de prova
        Usuari usuari1 = new UsuariPDI("Joan", "joan@example.com", "sescelades", "ETSE");
        Usuari usuari2 = new UsuariPTGAS("Maria", "maria@example.com", "sescelades");
        
        Activitat activitatProva1 = new ActivitatOnline("Curs Java", new String[]{"PDI", "Estudiant"}, new Data(1, 1, 2025), new Data(15, 1, 2025), new Data(16, 1, 2025), 10, "http://cursos.com/cursjava");
        Inscripcions inscripcionsActivitat1 = new Inscripcions(activitatProva1);
    
        System.out.println("Prova 1: Inscripció en data fora de termini d'inscripció.");
        try {
            inscripcionsActivitat1.inscriureUsuari(usuari1, new Data(20, 1, 2025));
            System.out.println("Error: S'ha permès una inscripció fora de termini.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcte: " + e.getMessage());
        }

        System.out.println("\nProva 2: Inscripció correcta dins del termini.");
        try {
            inscripcionsActivitat1.inscriureUsuari(usuari1, new Data(10, 1, 2025));
            System.out.println("Inscripció realitzada correctament per " + usuari1.getAlias() + " a l'activitat " + activitatProva1.getNom() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        System.out.println("\nProva 3: Inscripció d'usuari no permès per l'activitat.");
        try {
            inscripcionsActivitat1.inscriureUsuari(usuari2, new Data(10, 1, 2025));
            System.out.println("Error: S'ha permès una inscripció d'usuari d'un tipus no permès per l'activitat.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcte: " + e.getMessage());
        }

        
    }
}