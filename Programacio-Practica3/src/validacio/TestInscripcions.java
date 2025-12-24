package validacio;

import Inscripcions.Inscripcions;
import Inscripcions.LlistaInscripcions;
import usuaris.*;
import Activitats.*;
import packages.Data;

/* TODO:
    - Validar màxim de plaçes
    - Validar que l'usuari ja està inscrit
    - Validar cambi de espera a inscrit */
public class TestInscripcions {
    public static void main(String[] args) {
        System.out.println("--- Test de validació d'inscripcions ---");

        // Crear usuaris de prova
        Usuari usuari1 = new UsuariPDI("Joan", "joan@example.com", "sescelades", "ETSE");
        Usuari usuari2 = new UsuariPTGAS("Maria", "maria@example.com", "sescelades");

        Activitat activitatProva1 = new ActivitatOnline("Curs Java", new String[] { "PDI", "Estudiant" },
                new Data(1, 1, 2025), new Data(15, 1, 2025), new Data(16, 1, 2025), 10, "http://cursos.com/cursjava");
        Activitat activitatProva2 = new ActivitatPeriodiques("Classe particular de pintura",
                new String[] { "PDI", "PTGAS" }, new Data(1, 1, 2025), new Data(10, 1, 2025), 2, 8, 30, 10, 30, "IAMF",
                "Tarragona", 1, new Data(11, 1, 2025));

        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(1000);

        System.out.println("1) Crear inscripcio correcta");
        Inscripcions inscripcio1 = crearInscripcio(usuari1, activitatProva1, new Data(1, 1, 2025));
        if (inscripcio1 != null) {
            System.out.println("Correcte: Inscripcio correcta: " + inscripcio1);
        } else {
            System.out.println("Incorrecte: Error al crear inscripcio");
        }

        System.out.println("2) Crear inscripcio fora de data d'inscripcio");
        Inscripcions inscripcio2 = crearInscripcio(usuari1, activitatProva1, new Data(16, 1, 2025));
        if (inscripcio2 == null) {
            System.out.println("Correcte: No s'ha creat inscripcio");
        } else {
            System.out.println("Incorrecte: No s'ha controlat data d'inscripcio");
        }

        System.out.println("3) Crear inscripció amb tipus d'usuari no vàlid");
        Inscripcions inscripcio3 = crearInscripcio(usuari2, activitatProva1, new Data(1, 1, 2025));
        if (inscripcio3 == null) {
            System.out.println("Correcte: No s'ha creat inscripcio");
        } else {
            System.out.println("Incorrecte: No s'ha controlat tipus d'usuari");
        }

        System.out.println("\n--- Fi del test de validació d'inscripcions ---");
    }

    private static Inscripcions crearInscripcio(Usuari usuari, Activitat activitat, Data dataInscripcio) {
        try {
            return new Inscripcions(activitat, usuari, dataInscripcio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
