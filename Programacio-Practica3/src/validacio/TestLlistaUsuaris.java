package validacio;

import usuaris.*; 

public class TestLlistaUsuaris {
    public static void main(String[] args) {
        System.out.println("Test de llista d'usuaris");

        // dades
        Estudiant est = new Estudiant("Pep", "a@urv.cat", 2, "Informatica");
        UsuariPDI pdi = new UsuariPDI("Marc", "b@urv.cat", "Sescelades", "Medicina");
        UsuariPTGAS ptgas = new UsuariPTGAS("Maria",  "c@urv.cat", "Catalunya");

       
        System.out.println("\nProva 1: filtres per tipus");
        LlistaUsuaris llistaMix = new LlistaUsuaris(10);
        llistaMix.afegirUsuari(est);
        llistaMix.afegirUsuari(pdi);
        llistaMix.afegirUsuari(ptgas);

      
        LlistaUsuaris llistaNomesEstudiants = llistaMix.getEstudiants();
        if (llistaNomesEstudiants.getNusuaris() == 1) {
            System.out.println("Correcte: getEstudiants ha retornat 1 element.");
           
            if (llistaNomesEstudiants.getUsuari(0).getAlias().equals("Pep")) {
                System.out.println("Correcte: L'estudiant és en Pep.");
            }
        } else {
            System.out.println("Error: getEstudiants ha fallat.");
        }

        
        if (llistaMix.getPdi().getNusuaris() == 1) {
            System.out.println("Correcte: getPdi ha trobat 1 PDI.");
        } else {
            System.out.println("Error: getPdi ha fallat.");
        }

        
        System.out.println("\nProva 2: excepcions");
        
       
        System.out.println("Intentant omplir una llista petita...");
        LlistaUsuaris llistaPetita = new LlistaUsuaris(1); // nomes espai poer 1
        
        try {
            llistaPetita.afegirUsuari(est); // 1r (Correcte)
            llistaPetita.afegirUsuari(pdi); // 2n (Error)
            System.out.println("Error: Ha deixat afegir més usuaris de la capacitat");
        } catch (IllegalStateException e) {
            System.out.println("Correcte: Excepció capturada -> " + e.getMessage());
        }

        // prova index incorrecte
        System.out.println("Intentant accedir a un índex dolent...");
        try {
            llistaMix.getUsuari(99); // no existeix
            System.out.println("Error: Ha deixat accedir a l'índex 99");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Correcte: Excepció capturada -> " + e.getMessage());
        }

        // 3. cerca i esborrat
        System.out.println("\nProva 3: usuariExisteix i eliminarUsuari");
        
        // usuariExisteix
        if (llistaMix.usuariExisteix(est)) {
            System.out.println("OK: usuariExisteix diu que en Pep hi és.");
        } else {
            System.out.println("ERROR: No troba en Pep.");
        }

        // eliminarUsuari
        llistaMix.eliminarUsuari(est); // esborrar en Pep
        
        if (!llistaMix.usuariExisteix(est)) {
            System.out.println("Correcte: En Pep s'ha esborrat correctament.");
        } else {
            System.out.println("Error: En Pep encara hi és!");
        }
        
        // comprovar que s'ha mogut tot
        if (llistaMix.getNusuaris() == 2) {
             System.out.println("Correcte: Queden 2 usuaris a la llista.");
        }
    }
}