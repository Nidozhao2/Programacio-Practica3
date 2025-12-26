package validacio;

import Inscripcions.Inscripcions;
import Inscripcions.LlistaInscripcions;
import excepcions.ActivitatNoAcabada;
import excepcions.ForaDePeriode;
import usuaris.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

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

        System.out.println("4) Valorar inscripció vàlida");
        try {
            inscripcio1.valorar(5, new Data(27, 1, 2025), usuari1);
            if (inscripcio1.getValoracioDonada() == 5) {
                System.out.println("Correcte: Inscripcio valorada correctament");
            } else {
                System.out.println("Incorrecte: No s'ha valorat la inscripcio");
            }
        } catch (Exception e) {
            System.out.println("Incorrecte: " + e.getMessage());
        }

        System.out.println("5) Valorar inscripció fora de data");
        try {
            inscripcio1.valorar(5, new Data(5, 1, 2025), usuari1);
            if (inscripcio1.getValoracioDonada() == 5) {
                System.out.println("Incorrecte: S'ha permès la valoració");
            }
        } catch (ActivitatNoAcabada e) {
            System.out.println("Correcte: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Incorrecte: " + e.getMessage());
        }

        System.out.println("6) Guardar inscripcions en fitxer serialitzat");
        try {
            llistaInscripcions.afegirInscripcio(inscripcio1);
            llistaInscripcions.afegirInscripcio(inscripcio2);
            llistaInscripcions.afegirInscripcio(inscripcio3);
            escriureInscripcions(llistaInscripcions);
            System.out.println("Correcte: Inscripcions guardades correctament");
        } catch (Exception e) {
            System.out.println("Incorrecte: " + e.getMessage());
        }

        System.out.println("7) Llegir inscripcions guardades en fitxer serialitzat");
        try {
            llistaInscripcions = new LlistaInscripcions(1000);
            llegirInscripcions(llistaInscripcions);
            System.out.println("Correcte: Inscripcions llegides correctament");
            System.out.println(llistaInscripcions);
        } catch (Exception e) {
            System.out.println("Incorrecte: " + e.getMessage());
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

    private static void escriureInscripcions(LlistaInscripcions llistaInscripcions) throws Exception {
        ObjectOutputStream sortida = new ObjectOutputStream(
                new FileOutputStream("src/validacio/persistenciaTest/Inscripcions.bin"));
        for (int i = 0; i < llistaInscripcions.getNumeroInscripcions(); i++) {
            sortida.writeObject(llistaInscripcions.getInscripcio(i));
            System.out.println(llistaInscripcions.getInscripcio(i));
        }
        for (int i = 0; i < llistaInscripcions.getNumeroInscripcionsEspera(); i++) {
            sortida.writeObject(llistaInscripcions.getInscripcioEspera(i));
            System.out.println(llistaInscripcions.getInscripcioEspera(i));
        }
        sortida.flush();
        sortida.close();
    }

    private static void llegirInscripcions(LlistaInscripcions llistaInscripcions) throws Exception {
        ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream("src/validacio/persistenciaTest/Inscripcions.bin"));
        Inscripcions instancia;
        boolean llegit = false;

        while (!llegit) {
            try {
                instancia = (Inscripcions) entrada.readObject();
                llistaInscripcions.afegirInscripcio(instancia);
            } catch (Exception e) {
                llegit = true;
            }
        }
        entrada.close();
    }
}
