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
        Activitat activitatProva2 = new ActivitatPeriodiques("Classe particular de pintura", new String[]{"PDI", "PTGAS"}, new Data(1, 1, 2025), new Data(10, 1, 2025), 2, 8, 30, 10, 30, "IAMF", "Tarragona", 1, new Data(11, 1, 2025));
        Inscripcions inscripcionsActivitat1 = new Inscripcions(activitatProva1);
        Inscripcions inscripcionsActivitat2 = new Inscripcions(activitatProva2);
    
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
            System.out.println("Correcte: Inscripció realitzada correctament per " + usuari1.getAlias() + " a l'activitat " + activitatProva1.getNom() + ".");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        System.out.println("\nProva 3: Inscripció d'usuari no permès per l'activitat.");
        try {
            inscripcionsActivitat1.inscriureUsuari(usuari2, new Data(10, 1, 2025));
            System.out.println("Error: S'ha permès una inscripció d'usuari d'un tipus no permès per l'activitat.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcte: " + e.getMessage());
        }

        System.out.println("\nProva 4: Inscripció en llista de espera.");
        try {
            inscripcionsActivitat2.inscriureUsuari(usuari1, new Data(10, 1, 2025));
            inscripcionsActivitat2.inscriureUsuari(usuari2, new Data(10, 1, 2025));

            if (inscripcionsActivitat2.getUsuarisEnEspera().getUsuari(0).getAlias().equals(usuari2.getAlias())) {
                System.out.println("Correcte: " + usuari2.getAlias() + " està a la llista d'espera.");
            } else {
                System.out.println("Error: " + usuari2.getAlias() + " no està a la llista d'espera com s'esperava.");
            }
            System.out.println("Usuaris inscrits:\n" + inscripcionsActivitat2.getUsuarisInscrits());
            System.out.println("Usuaris en espera:\n" + inscripcionsActivitat2.getUsuarisEnEspera());

        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        System.out.println("\nProva 5: Baixa d'usuari inscrit i promoció de llista d'espera.");
        try {
            inscripcionsActivitat2.baixaUsuari(usuari1);
            if (inscripcionsActivitat2.getUsuarisInscrits().getUsuari(0).getAlias().equals(usuari2.getAlias())) {
                System.out.println("Correcte: " + usuari2.getAlias() + " ha estat promogut de la llista d'espera a inscrit.");
            } else {
                System.out.println("Error: " + usuari2.getAlias() + " no ha estat promogut correctament.");
            }
            System.out.println("Usuaris inscrits:\n" + inscripcionsActivitat2.getUsuarisInscrits());
            System.out.println("Usuaris en espera:\n" + inscripcionsActivitat2.getUsuarisEnEspera());
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        System.out.println("\nProva 6: Inscripció d'usuari ja inscrit.");
        try {
            inscripcionsActivitat2.inscriureUsuari(usuari2, new Data(10, 1, 2025));
            System.out.println("Error: S'ha permès una inscripció d'usuari ja inscrit.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcte: " + e.getMessage());
        }

        System.out.println("\nProva 7: Baixa d'usuari no inscrit ni en llista d'espera.");
        try {
            inscripcionsActivitat2.baixaUsuari(usuari1);
            System.out.println("Error: S'ha permès una baixa d'usuari no inscrit ni en llista d'espera.");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcte: " + e.getMessage());  
        }

        System.out.println("\n--- Fi del test de validació d'inscripcions ---");
    }
}