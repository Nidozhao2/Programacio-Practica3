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

        Activitat activitatProva1 = null;
        Activitat activitatProva2 = null;

        try {
            activitatProva1 = new ActivitatOnline("Curs Java", new String[] { "PDI", "Estudiant" },
                    new Data(1, 1, 2025), new Data(15, 1, 2025), new Data(16, 1, 2025), 10, "http://cursos.com/cursjava");
            activitatProva2 = new ActivitatPeriodiques("Classe particular de pintura",
                    new String[] { "PDI", "PTGAS" }, new Data(1, 1, 2025), new Data(10, 1, 2025), 2, 8, 30, 10, 30, "IAMF",
                    "Tarragona", 1, new Data(11, 1, 2025));
        } catch (Exception e) {
            System.out.println(e);
        }

        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(1000);

        Inscripcions inscripcio1 = null;
        Inscripcions inscripcio2 = null;
        Inscripcions inscripcio3 = null;
        System.out.println("1) Crear inscripcio correcta");
        try {
            inscripcio1 = crearInscripcio(usuari1, activitatProva1, new Data(1, 1, 2025));
            if (inscripcio1 != null) {
                System.out.println("Correcte: Inscripcio correcta: " + inscripcio1);
            } else {
                System.out.println("Incorrecte: Error al crear inscripcio");
            }
    
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("2) Crear inscripcio fora de data d'inscripcio");
        try {
            inscripcio2 = crearInscripcio(usuari1, activitatProva1, new Data(16, 1, 2025));
            if (inscripcio2 == null) {
                System.out.println("Correcte: No s'ha creat inscripcio");
            } else {
                System.out.println("Incorrecte: No s'ha controlat data d'inscripcio");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("3) Crear inscripció amb tipus d'usuari no vàlid");
        try {
            inscripcio3 = crearInscripcio(usuari2, activitatProva1, new Data(1, 1, 2025));
            if (inscripcio3 == null) {
                System.out.println("Correcte: No s'ha creat inscripcio");
            } else {
                System.out.println("Incorrecte: No s'ha controlat tipus d'usuari");
            }
        } catch (Exception e) {
            System.out.println(e);
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

        // noves proves per als TODOS (llista, capacitat i espera)
        
        System.out.println("\nProves de llista i cua d'espera");
        
        LlistaInscripcions llistaTest = new LlistaInscripcions(10);
        
        // es crea una activitat amb nomes 1 plaça per forçar la llista d'espera
        Activitat activitatPetita;
        Inscripcions insUsuari1 = null;
        Inscripcions insUsuari2 = null;
        try {
            activitatPetita = new ActivitatDia("curs intensiu", 3, new String[] { "PDI", "PTGAS" },
                    new Data(1, 1, 2025), new Data(15, 1, 2025), new Data(16, 1, 2025), 8, 30, 2, 1, "Tarragona");
            insUsuari1 = crearInscripcio(usuari1, activitatPetita, new Data(2, 1, 2025));
            insUsuari2 = crearInscripcio(usuari2, activitatPetita, new Data(2, 1, 2025));
        } catch (Exception e) {
            System.out.println(e);
        }


        // 8) validar inscripció normal
        System.out.println("8) Crear inscripcio correcta (hi ha lloc)");
        try {
            if (insUsuari1 != null) {
                llistaTest.afegirInscripcio(insUsuari1);
                if (llistaTest.getNumeroInscripcions() == 1) {
                    System.out.println("Correcte: usuari 1 inscrit a la llista principal");
                } else {
                    System.out.println("Incorrecte: no s'ha afegit");
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrecte: " + e.getMessage());
        }

        // 9) Validar que l'usuari ja està inscrit (duplicats)
        System.out.println("9) Intentar inscriure el mateix usuari a la mateixa activitat");
        try {
            // es torna a intentar afegir insUsuari1
            llistaTest.afegirInscripcio(insUsuari1); 
            
            // si la llista detecta duplicats, no hauria d'afegir-lo ni a inscrits ni a espera
            if (llistaTest.getNumeroInscripcions() == 1 && llistaTest.getNumeroInscripcionsEspera() == 0) {
                 System.out.println("Correcte: el sistema ha ignorat el duplicat");
            } else {
                 System.out.println("Incorrecte: s'ha duplicat l'usuari");
            }
        } catch (Exception e) {
            System.out.println("Correcte: ha saltat error -> " + e.getMessage());
        }

        // 10) Validar màxim de places -> cap a llista d'espera
        System.out.println("10) Afegir segon usuari quan nomes hi ha 1 placa (llista d'espera)");
        try {
            if (insUsuari2 != null) {
                llistaTest.afegirInscripcio(insUsuari2);
                
                // es comprova si esta a la llista d'espera
                if (llistaTest.getNumeroInscripcions() == 1 && llistaTest.getNumeroInscripcionsEspera() == 1) {
                    System.out.println("Correcte: l'usuari 2 ha anat a la llista d'espera");
                } else {
                    System.out.println("Incorrecte: no s'ha detectat a la llista d'espera");
                }
            }
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        // 11) Validar canvi de espera a inscrit
        System.out.println("11) Eliminar el primer usuari i veure si el segon entra");
        try {
            // s'esborra l'usuari 1 (que ocupava la plaça)
            llistaTest.eliminarInscripcio(insUsuari1); 

            // l'usuari 1 ha marxat -> queda lloc -> l'usuari 2 (espera) puja a inscrit automaticament
            if (llistaTest.getNumeroInscripcions() == 1 && llistaTest.getNumeroInscripcionsEspera() == 0) {
                 
                 // es comprova que sigui l'usuari 2 qui hi ha a la llista
                 if (llistaTest.getInscripcio(0).getUsuariInscrit().getAlias().equals(usuari2.getAlias())) {
                     System.out.println("Correcte: l'usuari 2 ha passat d'espera a inscrit automaticament");
                 } else {
                     System.out.println("Incorrecte: hi ha un usuari pero no es l'usuari 2");
                 }
            } else {
                System.out.println("Incorrecte: la promocio automatica no ha funcionat");
            }

        } catch (Exception e) {
            System.out.println("Error testejant promocio: " + e.getMessage());
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
