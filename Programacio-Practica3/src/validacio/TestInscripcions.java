package validacio;

import Inscripcions.Inscripcions;
import Inscripcions.LlistaInscripcions;
import usuaris.*;
import Activitats.*;
import packages.Data;

/* TODO */
public class TestInscripcions {
    public static void main(String[] args) {
        System.out.println("--- Test de validació d'inscripcions ---");

        // Crear usuari de prova
        Usuari usuari1 = new UsuariPDI("Joan", "joan@example.com", "sescelades", "ETSE");
        Usuari usuari2 = new UsuariPTGAS("Maria", "maria@example.com", "sescelades");

        Activitat activitatProva1 = new ActivitatOnline("Curs Java", new String[] { "PDI", "Estudiant" },
                new Data(1, 1, 2025), new Data(15, 1, 2025), new Data(16, 1, 2025), 10, "http://cursos.com/cursjava");
        Activitat activitatProva2 = new ActivitatPeriodiques("Classe particular de pintura",
                new String[] { "PDI", "PTGAS" }, new Data(1, 1, 2025), new Data(10, 1, 2025), 2, 8, 30, 10, 30, "IAMF",
                "Tarragona", 1, new Data(11, 1, 2025));

        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(100);
        LlistaInscripcions llistaInscripcionsEspera = new LlistaInscripcions(100);

        System.out.println("\n--- Fi del test de validació d'inscripcions ---");
    }
}