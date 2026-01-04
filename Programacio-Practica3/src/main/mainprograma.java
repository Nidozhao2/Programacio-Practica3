package main;

import usuaris.*;
import packages.*;
import Activitats.*;
import Inscripcions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class mainprograma {
    public static void main(String[] args) throws Exception {
        // Falta carregar dades de fitxers si n'hi ha
        Data dataActual = new Data(26, 12, 2025);
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(100);
        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(10000);
        LlistaActivitats llistaActivitats = new LlistaActivitats(100);
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;
        boolean inputInvalid = false;

        // Carregar dades de fitxers si n'hi ha
        try {
            // llegirUsuaris(llistaUsuaris); TODO
            // llegirActivitats(llistaActivitats); TODO
            llegirInscripcions(llistaInscripcions);
        } catch (FileNotFoundException e) {
            System.out.println("Falta algun fitxer per llegir.");
        } catch (Exception e) {
            System.out.println("Error al carregar dades de fitxers: " + e.getMessage());
        }
        menu();
        while (!sortir) {
            do {
                try {
                    System.out.println("\nIntrodueix una opcio: ");
                    opcio = Integer.parseInt(teclat.nextLine());
                    switch (opcio) {
                        case 0:
                            menu();
                            break;
                        case 1:
                            modificarDadaSistema(dataActual, teclat);
                            break;

                        case 2:
                            mostrarDadesLlistes(llistaUsuaris, llistaInscripcions, llistaActivitats, teclat);
                            break;
                        case 3:
                            mostrarActivitatsPeriodeInscripcio(llistaActivitats, dataActual);
                            break;
                        case 4:
                            mostrarActivitatsClasseAvui(llistaActivitats, dataActual);
                            break;
                        case 5:
                            mostrarActivitatsActivesAvui(llistaActivitats, dataActual);
                            break;
                        case 6:
                            mostrarActivitatsPlacesDisponibles(llistaActivitats);
                            break;
                        case 7:
                            System.out.println("Introdueix el nom de l'activitat: ");
                            String nomActivitat = teclat.nextLine();
                            mostrarDetallsActivitat(nomActivitat, llistaActivitats);
                            break;
                        case 8:
                            System.out.println("Introdueix el nom de l'usuari: ");
                            String nomUsuari = teclat.nextLine();
                            mostrarDetallsUsuari(nomUsuari, llistaUsuaris);
                            break;
                        case 9:
                            System.out.println("Introdueix el nom de l'usuari: ");
                            String nomUsuari2 = teclat.nextLine();
                            mostrarActivitatsUsuari(nomUsuari2, llistaInscripcions);
                            break;
                        case 10:
                            inscriureUsuari(llistaUsuaris, llistaActivitats, llistaInscripcions, dataActual, teclat);
                            break;

                        case 11:
                            mostrarInscritsActivitat(llistaInscripcions, teclat);
                            break;

                        case 12:
                            eliminarInscripcio(llistaUsuaris, llistaActivitats, llistaInscripcions, teclat);
                            break;

                        case 13:
                            afegirActivitatDia(llistaActivitats, teclat);
                            break;

                        case 14:
                            afegirActivitatPeriodica(llistaActivitats, teclat);
                            break;

                        case 15:
                            afegirActivitatOnline(llistaActivitats, teclat);
                            break;
                        
                        case 16:
                            String aliasUsuari;
                            System.out.println("alias de l'usuari que valora:");
                            aliasUsuari = teclat.nextLine();
                            valorarActivitat(llistaInscripcions, aliasUsuari, dataActual, teclat);
                            break;
                        case 17:
                            mostrarResumValoracionsActivitatsAcabades(llistaInscripcions, llistaActivitats, dataActual);
                            break;
                        case 18:
                            mostrarResumValoracionsUsuari(llistaInscripcions, teclat);
                            break;
                        case 19:
                            mostrarMitjanaValoracionsCollectiu(llistaInscripcions);
                            break;
                        case 20:
                            mostrarUsuariMesActiuDeCollectiu(llistaInscripcions, teclat);
                            break;
                        case 21:
                            donarDeBaixaActivitatsSegonsOcupacio(llistaActivitats, llistaInscripcions, dataActual);
                            break;
                        case 22:
                            sortir = true;
                            break;
                        default:
                            System.out.println("Error: Opcio no valida.");
                            inputInvalid = true;
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Has d'introduir un número.");
                    inputInvalid = true;
                }
            } while (inputInvalid);
        }
        System.out.println("Sortint del programa. Vols guardar les dades? (S/N)");
        String resposta = teclat.nextLine();
        if (resposta.toLowerCase().equals("s")) {
            // escriureUsuaris(llistaUsuaris); TODO
            // escriureActivitats(llistaActivitats); TODO
            escriureInscripcions(llistaInscripcions);
        } else if (resposta.toLowerCase().equals("n")) {
            System.out.println("Sortint del programa sense guardar les dades.");
        } else {
            System.out.println("Error: Opcio no valida. Sortint del programa sense guardar les dades."); // Per
                                                                                                         // simplificar
                                                                                                         // no es torna
                                                                                                         // a demanar
                                                                                                         // una opció
        }
        teclat.close();

    }

    public static void menu() {
        System.out.println("--- MENU D'OPCIONS ---");
        System.out.println("0. Mostrar menu");
        System.out.println("1. Indicar data actual");
        System.out.println("2. Mostrar dades de les llistes");
        System.out.println("3. Mostrar activitats en periode d'inscripcio");
        System.out.println("4. Mostrar activitats amb classe avui");
        System.out.println("5. Mostrar activitats actives avui");
        System.out.println("6. Mostrar activitats amb plaçes disponibles");
        System.out.println("7. Mostrar detalls d'una activitat");
        System.out.println("8. Mostrar detalls d'un usuari");
        System.out.println("9. Mostrar activitats a les quals està apuntat un usuari");
        System.out.println("10. Inscriure usuari a activitat");
        System.out.println("11. Mostrar usuaris apuntats a una activitat i usuaris en espera");
        System.out.println("12. Eliminar usuari d'activitat");
        System.out.println("13. Afegir nova activitat d'un dia");
        System.out.println("14. Afegir nova activitat periòdica");
        System.out.println("15. Afegir nova activitat en línia");
        System.out.println("16. Valorar activitat per part d'un assistent");
        System.out.println("17. Mostrar resum de valoracions de les activitats acabades");
        System.out.println("18. Mostrar resum de valoracions d'un usuari");
        System.out.println("19. Mostrar mitjana valoracions de cada col·lectiu");
        System.out.println("20. Mostrar usuari més actiu d'un col·lectiu");
        System.out.println("21. Donar de baixa activitats segons plaçes ocupades");
        System.out.println("22. Sortir");
    }

    public static void modificarDadaSistema(Data data, Scanner teclat) {
        System.out.println("Data actual: "  + data);

        System.out.println("Vols modificar la data? (S/n):");
        boolean modificar = teclat.nextLine().toLowerCase().equals("s");
        if (modificar) {
            try {
                System.out.println("Nou dia:");
                int dia = Integer.parseInt(teclat.nextLine());
                System.out.println("Nou mes:");
                int mes = Integer.parseInt(teclat.nextLine());
                System.out.println("Nou any:");
                int any = Integer.parseInt(teclat.nextLine());
                data.setData(dia, mes, any);
                System.out.println("Nova data: " + data);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void mostrarDadesLlistes(LlistaUsuaris llistaUsuaris, LlistaInscripcions llistaInscripcions,
            LlistaActivitats llistaActivitats, Scanner teclat) {

        int opcio;
        boolean inputInvalid = false;
        do {
            try {
                System.out.println("(1) Usuaris");
                System.out.println("(2) Inscripcions");
                System.out.println("(3) Activitats");
                System.out.println("(4) Tots");
                System.out.println("\nIntrodueix una opcio: ");
                opcio = Integer.parseInt(teclat.nextLine());
                switch (opcio) {
                    case 1:
                        System.out.println("\n(1) Estudiants");
                        System.out.println("(2) PDI");
                        System.out.println("(3) PTGAS");
                        System.out.println("(4) Tots");
                        inputInvalid = false;
                        switch (Integer.parseInt(teclat.nextLine())) {
                            case 1:
                                System.out.println(llistaUsuaris.getEstudiants());
                                inputInvalid = false;
                                break;
                            case 2:
                                System.out.println(llistaUsuaris.getPdi());
                                inputInvalid = false;
                                break;
                            case 3:
                                System.out.println(llistaUsuaris.getPtgas());
                                inputInvalid = false;
                                break;
                            case 4:
                                System.out.println(llistaUsuaris);
                                inputInvalid = false;
                                break;
                            default:
                                System.out.println("Error: Opcio no valida de Usuaris.");
                                inputInvalid = true;
                                break;
                        }
                        break;
                    case 2:
                        System.out.println(llistaInscripcions.toString());
                        inputInvalid = false;
                        break;
                    case 3:
                        System.out.println("\n(1) Diaries");
                        System.out.println("(2) Periodiques");
                        System.out.println("(3) En linia");
                        System.out.println("(4) Totes");
                        switch (Integer.parseInt(teclat.nextLine())) {
                            case 1:
                                System.out.println(llistaActivitats.getDiaries());
                                inputInvalid = false;
                                break;
                            case 2:
                                System.out.println(llistaActivitats.getPeriodiques());
                                inputInvalid = false;
                                break;
                            case 3:
                                System.out.println(llistaActivitats.getEnLinea());
                                inputInvalid = false;
                                break;
                            case 4:
                                System.out.println(llistaActivitats);
                                inputInvalid = false;
                                break;
                            default:
                                System.out.println("Error: Opcio no valida de Activitats.");
                                inputInvalid = true;
                                break;
                        }
                        break;
                    case 4:
                        System.out.println(llistaUsuaris.toString() + "\n" + llistaInscripcions.toString() + "\n"
                                + llistaActivitats.toString());
                        break;
                    default:
                        System.out.println("Error: Opcio no valida.");
                        inputInvalid = true;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Has d'introduir un número.");
                inputInvalid = true;
            }
        } while (inputInvalid);
    }

    public static void mostrarActivitatsPeriodeInscripcio(LlistaActivitats llistaActivitats, Data dataActual) {
        System.out.println("Activitats en periode d'inscripcio: \n");
        System.out.println(llistaActivitats.getActivitatsPeriodeInscripcio(dataActual));
    }

    public static void mostrarActivitatsClasseAvui(LlistaActivitats llistaActivitats, Data dataActual) {
        System.out.println("Activitats amb classe avui: \n");
        for (int i = 0; i < llistaActivitats.getNElems(); i++) {
            if (llistaActivitats.getLlista()[i].activaEnDia(dataActual)) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            }
        }
    }

    public static void mostrarActivitatsActivesAvui(LlistaActivitats llistaActivitats, Data dataActual) { // punt 5
        System.out.println("Activitats actives avui: \n");
        for (int i = 0; i < llistaActivitats.getNElems(); i++) {
            if (llistaActivitats.getLlista()[i].getDataInici().comparaAmb(dataActual) <= 0 &&
                    llistaActivitats.getLlista()[i].getFiActivitat().comparaAmb(dataActual) >= 0) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            }
        }
    }

    public static void mostrarActivitatsPlacesDisponibles(LlistaActivitats llistaActivitats) { // punt 6
        System.out.println("Activitats amb places disponibles: \n");
        for (int i = 0; i < llistaActivitats.getNElems(); i++) {
            if (llistaActivitats.getLlista()[i].tePlacesDisponibles()) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            }
        }
    }

    public static void mostrarDetallsActivitat(String nom, LlistaActivitats llistaActivitats) { // punt 7

        int i = 0;
        boolean trobat = false;
        while (!trobat && i < llistaActivitats.getNElems()) {
            if (llistaActivitats.getLlista()[i].getNom().equals(nom)) {
                System.out.println(llistaActivitats.getLlista()[i].toString());
                trobat = true;
            }
            i++;
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap activitat amb el nom " + nom);
        }
    }

    public static void mostrarDetallsUsuari(String nom, LlistaUsuaris usuaris) { // punt 8
        int i = 0;
        boolean trobat = false;
        while (!trobat && i < usuaris.getNusuaris()) {
            if (usuaris.getUsuari(i).getAlias().equals(nom)) {
                System.out.println(usuaris.getUsuari(i).toString());
                trobat = true;
            }
            i++;
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap usuari amb el nom " + nom);
        }
    }

    public static void mostrarActivitatsUsuari(String nom, LlistaInscripcions inscripcions) { // punt 9
        for (int i = 0; i < inscripcions.getNumeroInscripcions(); i++) {
            if (inscripcions.getInscripcio(i).getUsuariInscrit().getAlias().equals(nom)) {
                System.out.println(inscripcions.getInscripcio(i).getActivitatInscripcio().toString() + "\n");
            }
        }
    }

    private static void llegirInscripcions(LlistaInscripcions llistaInscripcions) throws Exception {
        ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream("src/main/dadesGuardades/Inscripcions.bin"));
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

    private static void escriureInscripcions(LlistaInscripcions llistaInscripcions) throws Exception {
        ObjectOutputStream sortida = new ObjectOutputStream(
                new FileOutputStream("src/main/dadesGuardades/Inscripcions.bin"));
        for (int i = 0; i < llistaInscripcions.getNumeroInscripcions(); i++) {
            sortida.writeObject(llistaInscripcions.getInscripcio(i));
        }
        for (int i = 0; i < llistaInscripcions.getNumeroInscripcionsEspera(); i++) {
            sortida.writeObject(llistaInscripcions.getInscripcioEspera(i));
        }
        sortida.flush();
        sortida.close();
    }

    public static void inscriureUsuari(LlistaUsuaris usuaris, LlistaActivitats activitats, 
                                       LlistaInscripcions inscripcions, Data dataActual, Scanner teclat) {
        System.out.println("Nova inscripcio");
        System.out.print("Alias de l'usuari: ");
        String alias = teclat.nextLine();
        Usuari u = usuaris.getUsuari(alias);

        if (u == null) { // Si l'usuari no es troba a la llista, li demanem les dades
            System.out.println("No hem trobat cap usuari registrat amb aquest alias. Registra un nou usuari.");
            u = donarDaltaUsuari(usuaris, teclat, alias);
        }

        System.out.print("Nom de l'activitat: ");
        String nomAct = teclat.nextLine();
        // es busca l'activitat
        Activitat a = null;
        for(int i=0; i<activitats.getNElems(); i++) {
            if(activitats.getLlista()[i].getNom().equals(nomAct)) a = activitats.getLlista()[i]; // activitats.getActivitat() ??
        }

        if (u != null && a != null) {
            try {
                // Creem la inscripció amb la data actual del sistema
                Inscripcions nova = new Inscripcions(a, u, dataActual);
                inscripcions.afegirInscripcio(nova);
                System.out.println("L'inscripcio s'ha fet correctament");
                
                
            } catch (Exception e) {
                System.out.println("Error en la inscripció: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Usuari o Activitat no trobats.");
        }
    }

    public static void mostrarInscritsActivitat(LlistaInscripcions llista, Scanner teclat) {
        System.out.print("Nom de l'activitat a consultar: ");
        String nomAct = teclat.nextLine();

        System.out.println("\nLlista d'inscrits");
        boolean trobat = false;
        for (int i = 0; i < llista.getNumeroInscripcions(); i++) {
            if (llista.getInscripcio(i).getActivitatInscripcio().getNom().equals(nomAct)) {
                System.out.println("- " + llista.getInscripcio(i).getUsuariInscrit().getAlias());
                trobat = true;
            }
        }
        if (!trobat) System.out.println("(Cap inscrit)");

        System.out.println("\nLlista d'espera");
        trobat = false;
        for (int i = 0; i < llista.getNumeroInscripcionsEspera(); i++) {
            if (llista.getInscripcioEspera(i).getActivitatInscripcio().getNom().equals(nomAct)) {
                System.out.println("- " + llista.getInscripcioEspera(i).getUsuariInscrit().getAlias());
                trobat = true;
            }
        }
        if (!trobat) System.out.println("(Cap en espera)");
    }

    public static void eliminarInscripcio(LlistaUsuaris usuaris, LlistaActivitats activitats, 
                                          LlistaInscripcions inscripcions, Scanner teclat) {
        System.out.println("Eliminar inscripcio");
        System.out.print("Alias de l'usuari: ");
        String alias = teclat.nextLine();
        System.out.print("Nom de l'activitat: ");
        String nomAct = teclat.nextLine();

        Inscripcions aEsborrar = null;
        
        // es busca a la llista principal
        for(int i=0; i<inscripcions.getNumeroInscripcions(); i++) {
            Inscripcions ins = inscripcions.getInscripcio(i);
            if(ins.getUsuariInscrit().getAlias().equals(alias) && 
               ins.getActivitatInscripcio().getNom().equals(nomAct)) {
                aEsborrar = ins;
            }
        }
        
        // Si no, es busca a la llista d'espera (per si es vol esborrar d'allà)
        if (aEsborrar == null) {
             for(int i=0; i<inscripcions.getNumeroInscripcionsEspera(); i++) {
                Inscripcions ins = inscripcions.getInscripcioEspera(i);
                if(ins.getUsuariInscrit().getAlias().equals(alias) && 
                   ins.getActivitatInscripcio().getNom().equals(nomAct)) {
                    aEsborrar = ins;
                }
            }
        }

        if (aEsborrar != null) {
            try {
                inscripcions.eliminarInscripcio(aEsborrar);
                System.out.println("Inscripció eliminada correctament.");
            } catch (Exception e) {
                System.out.println("Error al eliminar: " + e.getMessage());
            }
        } else {
            System.out.println("No s'ha trobat cap inscripció amb aquestes dades.");
        }
    }

    public static void afegirActivitatDia(LlistaActivitats llista, Scanner teclat) {
        try {
            System.out.println("\nNova activitat del dia");
            System.out.print("Nom de l'activitat: ");
            String nom = teclat.nextLine();

            System.out.print("Preu: ");
            float preu = Float.parseFloat(teclat.nextLine());

            System.out.print("Places Màximes: ");
            int places = Integer.parseInt(teclat.nextLine());

            System.out.println("Data de l'activitat:");
            System.out.print("Dia (dd): ");
            int d = Integer.parseInt(teclat.nextLine());
            System.out.print("Mes: (mm)");
            int m = Integer.parseInt(teclat.nextLine());
            System.out.print("Any: (aaaa)");
            int a = Integer.parseInt(teclat.nextLine());
            Data dataAct = new Data(d, m, a);

            // calcul de dades d'inscripcio
            // es vol que la inscripció comenci 10 dies abans ( per exemple )
            int diaInici = 1;
            if (d > 10) {
                diaInici = d - 10;
            } else {
                diaInici = 1; // si estem a principis de mes, posem dia 1
            }
            
            // es vol que la inscripció acabi 1 dia abans
            int diaFi = 1;
            if (d > 1) {
                diaFi = d - 1;
            } else {
                diaFi = 1;
            }

            Data iniciIns = new Data(diaInici, m, a);
            Data fiIns = new Data(diaFi, m, a);

            System.out.print("Hora inici (0-23): ");
            int h = Integer.parseInt(teclat.nextLine());
            
            System.out.print("Minuts inici (0-59): ");
            int min = Integer.parseInt(teclat.nextLine());
            
            System.out.print("Durada (minuts): ");
            int dur = Integer.parseInt(teclat.nextLine());
            
            System.out.print("Ciutat: ");
            String ciutat = teclat.nextLine();

            String[] col = { "Estudiants", "PDI", "PTGAS" };

            ActivitatDia ad = new ActivitatDia(nom, preu, col, iniciIns, fiIns, dataAct, h, min, dur, places, ciutat);
            llista.afegirActivitat(ad);
            System.out.println("L'Activitat de Dia s'ha creat correctament");

        } catch (Exception e) {
            System.out.println("Error creant activitat: " + e.getMessage());
        }
    }

    public static void afegirActivitatPeriodica(LlistaActivitats llista, Scanner teclat) {
        try {
            System.out.println("\nNova activitat periodica");
            System.out.print("Nom de l'activitat: ");
            String nom = teclat.nextLine();

            System.out.print("Places Màximes: ");
            int places = Integer.parseInt(teclat.nextLine());

            System.out.println("Data d'inici de l'activitat");
            System.out.print("Dia (dd): ");
            int d = Integer.parseInt(teclat.nextLine());
            System.out.print("Mes (mm): ");
            int m = Integer.parseInt(teclat.nextLine());
            System.out.print("Any: (aaaa)");
            int a = Integer.parseInt(teclat.nextLine());
            Data dataInici = new Data(d, m, a);

            int diaInici = 1;
            if (d > 10) {
                diaInici = d - 10;
            } else {
                diaInici = 1;
            }

            int diaFi = 1;
            if (d > 1) {
                diaFi = d - 1;
            } else {
                diaFi = 1;
            }

            Data iniciIns = new Data(diaInici, m, a);
            Data fiIns = new Data(diaFi, m, a);

            System.out.print("Setmanes totals de durada: ");
            int setmanes = Integer.parseInt(teclat.nextLine());

            System.out.print("Hora inici (0-23): ");
            int hIni = Integer.parseInt(teclat.nextLine());
            System.out.print("Minuts inici: ");
            int mIni = Integer.parseInt(teclat.nextLine());

            System.out.print("Hora final (0-23): ");
            int hFi = Integer.parseInt(teclat.nextLine());
            System.out.print("Minuts final: ");
            int mFi = Integer.parseInt(teclat.nextLine());

            System.out.print("Nom del Centre: ");
            String centre = teclat.nextLine();

            System.out.print("Ciutat: ");
            String ciutat = teclat.nextLine();

            String[] col = { "Estudiants", "PDI", "PTGAS" };

            ActivitatPeriodiques ap = new ActivitatPeriodiques(nom, col, iniciIns, fiIns, 
                    setmanes, hIni, mIni, hFi, mFi, centre, ciutat, places, dataInici);
            
            llista.afegirActivitat(ap);
            System.out.println("Activitat Periòdica creada correctament!");

        } catch (Exception e) {
            System.out.println("Error creant activitat periòdica: " + e.getMessage());
        }
    }

    public static void afegirActivitatOnline(LlistaActivitats llista, Scanner teclat) {
        try {
            System.out.println("\nNova activitat online");
            System.out.print("Nom de l'activitat: ");
            String nom = teclat.nextLine();

            System.out.print("Enllaç : ");
            String url = teclat.nextLine();

            System.out.print("Període d'activitat: ");
            int periode = Integer.parseInt(teclat.nextLine());

            System.out.println("Data de l'activitat:");
            System.out.print("Dia (dd): ");
            int d = Integer.parseInt(teclat.nextLine());
            System.out.print("Mes (mm): ");
            int m = Integer.parseInt(teclat.nextLine());
            System.out.print("Any (aaaa): ");
            int a = Integer.parseInt(teclat.nextLine());
            Data dataAct = new Data(d, m, a);
            Data iniciIns = dataAct.copia();
            Data fiIns = dataAct.copia();

            for (int i = 0; i < 10; i++) {
                iniciIns = iniciIns.diaAnterior();
            }

            fiIns = dataAct.diaAnterior();

            String[] col = {"Estudiants", "PDI", "PTGAS" };

            ActivitatOnline ao = new ActivitatOnline(nom, col, iniciIns, fiIns, dataAct, periode, url);
            
            llista.afegirActivitat(ao);
            System.out.println("Activitat Online s'ha creat correctament");

        } catch (Exception e) {
            System.out.println("Error creant activitat online: " + e.getMessage());
        }
    }

    public static Usuari donarDaltaUsuari(LlistaUsuaris llistaUsuaris, Scanner teclat, String alias) {
        try {
            System.out.println("Tipus d'usuari:");
            System.out.println("(1) Estudiant");
            System.out.println("(2) PDI");
            System.out.println("(3) PTGAS");
            int tipus = Integer.parseInt(teclat.nextLine());
            Usuari nouUsuari = null;
            String adreca;
            String campus;
            switch (tipus) {
                case 1: // Estudiant
                    System.out.println("Adreça:");
                    adreca = teclat.nextLine();
                    System.out.println("Any d'estudi:");
                    int any = Integer.parseInt(teclat.nextLine());
                    System.out.println("Grau:");
                    String grau = teclat.nextLine();
                    nouUsuari = new Estudiant(alias, adreca, any, grau);
                    break;
                case 2: // PDI
                    System.out.println("Adreça:");
                    adreca = teclat.nextLine();
                    System.out.println("Campus:");
                    campus = teclat.nextLine();
                    System.out.println("Departament:");
                    String dept = teclat.nextLine();
                    nouUsuari = new UsuariPDI(alias, adreca, campus, dept);
                    break;
                case 3: // PTGAS
                    System.out.println("Adreça:");
                    adreca = teclat.nextLine();
                    System.out.println("Campus:");
                    campus = teclat.nextLine();
                    nouUsuari = new UsuariPTGAS(alias, adreca, campus);
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
            llistaUsuaris.afegirUsuari(nouUsuari);
            return nouUsuari;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void valorarActivitat(LlistaInscripcions llistaInscripcions, String aliasUsuari, 
                                        Data dataActual, Scanner teclat) {
        System.out.print("Nom de l'activitat a valorar: ");
        String nomAct = teclat.nextLine();

        boolean trobat = false;
        for (int i = 0; i < llistaInscripcions.getNumeroInscripcions(); i++) {
            Inscripcions ins = llistaInscripcions.getInscripcio(i);
            Usuari usuari = ins.getUsuariInscrit();
            if (ins.getActivitatInscripcio().getNom().equals(nomAct) &&
                usuari.getAlias().equals(aliasUsuari)) {
                
                try {
                    System.out.print("Introdueix la valoració (1-5): ");
                    int valoracio = Integer.parseInt(teclat.nextLine());
                    ins.valorar(valoracio, dataActual, usuari);
                    System.out.println("Valoració afegida correctament.");
                } catch (Exception e) {
                    System.out.println("Error afegint valoració: " + e.getMessage());
                }
            }
            trobat = true;
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap inscripció per aquest usuari i activitat.");
        }
    }

    public static void mostrarResumValoracionsActivitatsAcabades(LlistaInscripcions llistaInscripcions, LlistaActivitats llistaActivitats, Data dataActual) {
        System.out.println("Resum de valoracions de les activitats acabades:");
        LlistaActivitats activitatsAcabades = llistaActivitats.getActivitatsAcabades(dataActual);
        for (int i = 0; i < activitatsAcabades.getNElems(); i++) {
            Activitat act = activitatsAcabades.getLlista()[i];
            float mitjana = llistaInscripcions.getMitjanaValoracionsActivitat(act.getNom());
            System.out.println("Activitat: " + act.getNom() + " - Mitjana valoracions: " + mitjana);
        }
    }

    public static void mostrarResumValoracionsUsuari(LlistaInscripcions llistaInscripcions, Scanner teclat) {
        System.out.print("Alias de l'usuari a consultar: ");
        String aliasUsuari = teclat.nextLine();
        String resum = llistaInscripcions.getResumValoracionsUsuari(aliasUsuari);
        System.out.println(resum);
    }

    public static void mostrarMitjanaValoracionsCollectiu(LlistaInscripcions llistaInscripcions) {
        System.out.println("Mitjana de valoracions per col·lectiu:");
        String[] collectius = {"Estudiants", "PDI", "PTGAS"};
        for (int i = 0; i < collectius.length; i++) {
            float mitjana = llistaInscripcions.getMitjanaValoracionsCollectiu(collectius[i]);
            System.out.println("Col·lectiu: " + collectius[i] + " - Mitjana valoracions: " + mitjana);
        }
    }

    public static void mostrarUsuariMesActiuDeCollectiu(LlistaInscripcions llistaInscripcions, Scanner teclat) {
        System.out.println("Introdueix col·lectiu:");
        System.out.println("(1) Estudiants");
        System.out.println("(2) PDI");
        System.out.println("(3) PTGAS");
        int opcio = Integer.parseInt(teclat.nextLine());
        String collectiu = "";
        switch (opcio) {
            case 1:
                collectiu = "Estudiants";
                break;
            case 2:
                collectiu = "PDI";
                break;
            case 3:
                collectiu = "PTGAS";
                break;
            default:
                System.out.println("Opció no vàlida.");
                break;
        }
        LlistaInscripcions novaLlista = llistaInscripcions.filtrarPerCollectiu(collectiu);
        if (novaLlista.getNumeroInscripcions() == 0) {
            System.out.println("No hi ha inscripcions per al col·lectiu " + collectiu);
        } else {
            Usuari usuariMesActiu = novaLlista.usuariAmbMesInscripcions();
            System.out.println("L'usuari més actiu del col·lectiu " + collectiu + " és: " + usuariMesActiu.getAlias());
        }

    }

    public static void donarDeBaixaActivitatsSegonsOcupacio(LlistaActivitats llistaActivitats, LlistaInscripcions llistaInscripcions, Data dataActual) {
        try {
            
            for (int i = 0; i < llistaActivitats.getNElems(); i++) {
                Activitat activitat = llistaActivitats.getActivitat(i);
                boolean eliminar = false;
                if (!activitat.estaEnPeriodeInscripcio(dataActual)) {
                    if (activitat instanceof ActivitatOnline) {
                        eliminar = (activitat.getPlacesOcupades() < 20);
                    } else {
                        float percentatgeOcupacio = (float) activitat.getPlacesOcupades() / activitat.getPlacesMaximes();
                        eliminar = (percentatgeOcupacio < 0.10);
                    }
                    if (eliminar) {
                        System.out.println("Donant de baixa l'activitat: " + activitat.getNom());
                        llistaActivitats.esborrarActivitat(activitat.getNom());
                        for (int j = 0; j < llistaInscripcions.getNumeroInscripcions(); j++) {
                            Inscripcions inscripcio = llistaInscripcions.getInscripcio(j);
                            if (inscripcio.getActivitatInscripcio().getNom().equals(activitat.getNom())) {
                                try 
                                {
                                    llistaInscripcions.eliminarInscripcio(inscripcio);
                                    j--; // Ajustar l'índex després d'eliminar una inscripció
                                } catch (Exception e) {
                                    System.out.println("Error eliminant inscripció: " + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error donant de baixa activitats: " + e.getMessage());
        }
    }
}