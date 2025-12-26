package main;

import usuaris.*;
import packages.*;
import Activitats.*;
import Inscripcions.*;
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
                            System.out.println("Data actual: " + dataActual.toString());
                            break;

                        case 2:
                            mostrarDadesLlistes(llistaUsuaris, llistaInscripcions, llistaActivitats);
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
            dataActual = dataActual.diaSeguent();
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

    // Me he rendido haciendo esto, que alguien lo arregle por mí por favor.
    public static void mostrarDadesLlistes(LlistaUsuaris llistaUsuaris, LlistaInscripcions llistaInscripcions,
            LlistaActivitats llistaActivitats) {


        Scanner teclat = new Scanner(System.in);
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
        teclat.close();
    }

    public static void mostrarActivitatsPeriodeInscripcio(LlistaActivitats llistaActivitats, Data dataActual) {
        System.out.println("Activitats en periode d'inscripcio: \n");
        System.out.println(llistaActivitats.getActivitatsPeriodeInscripcio(dataActual));
    }


    public static void mostrarActivitatsClasseAvui(LlistaActivitats llistaActivitats, Data dataActual) { //punt 4, places ocupades? llista espera?
        System.out.println("Activitats amb classe avui: \n");
        for(int i=0; i<llistaActivitats.getNElems(); i++) {
            if(llistaActivitats.getLlista()[i].activaEnDia(dataActual)) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            } 
        }
    }

    public static void mostrarActivitatsActivesAvui(LlistaActivitats llistaActivitats, Data dataActual) { //punt 5
        System.out.println("Activitats actives avui: \n");
        for(int i=0; i<llistaActivitats.getNElems(); i++) {
            if(llistaActivitats.getLlista()[i].getDataInici().comparaAmb(dataActual) <= 0 && 
               llistaActivitats.getLlista()[i].getFiActivitat().comparaAmb(dataActual) >= 0) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            } 
        }
    }


    public static void mostrarActivitatsPlacesDisponibles(LlistaActivitats llistaActivitats) { //punt 6
        System.out.println("Activitats amb places disponibles: \n");
        for(int i=0; i<llistaActivitats.getNElems(); i++) {
            if(llistaActivitats.getLlista()[i].tePlacesDisponibles()) {
                System.out.println(llistaActivitats.getLlista()[i].toString() + "\n");
            } 
        }
    }

    public static void mostrarDetallsActivitat(String nom,LlistaActivitats llistaActivitats) { //punt 7
        
        int i=0;
        boolean trobat=false;
        while (!trobat && i<llistaActivitats.getNElems()) {
            if(llistaActivitats.getLlista()[i].getNom().equals(nom)) {
                System.out.println(llistaActivitats.getLlista()[i].toString());
                trobat=true;
            }
            i++;
        }
    }      


    public static void mostrarDetallsUsuari(String nom,LlistaUsuaris usuaris) { //punt 8
        int i=0;
        boolean trobat=false;
        while (!trobat && i<usuaris.getNusuaris()) {
            if(usuaris.getUsuari(i).getAlias().equals(nom)) {
                System.out.println(usuaris.getUsuari(i).toString());
                trobat=true;
            }
            i++;
        }
    }

    public static void mostrarActivitatsUsuari(String nom, LlistaInscripcions inscripcions) { //punt 9
        for(int i=0;i<inscripcions.getNumeroInscripcions();i++) {
            if(inscripcions.getInscripcio(i).getUsuariInscrit().equals(nom)) {
                System.out.println(inscripcions.getInscripcio(i).getActivitatInscripcio().toString() + "\n");
            }
        }
    }


    

}