package edu.cegepvicto.application.services;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Offre des méthodes de saisie à la console et redemande l'entrée tant que celle-ci n'est pas valide.
 */
public class SaisieConsoleService implements IService {

    private final int ESCAPE_CHAR = 33;

    private final int ELEMENT_PAR_PAGE = 8;

    private final Scanner lecteur;

    public SaisieConsoleService() {
        lecteur = new Scanner(System.in);
    }

    @Override
    public void initialiser(LocalisateurService localisateurService) {

    }

    public String saisirChaine(String message) throws InterruptionSaisieException {
        System.out.println(message + " [appuyez sur ESC + ENTER pour quitter]");
        String chaine = lecteur.nextLine();

        if(chaine.length() > 1 && chaine.charAt(0) == ESCAPE_CHAR) {
            throw new InterruptionSaisieException("Utilisateur demande de quitter");
        }

        return chaine;
    }

    public int saisirInt(String message) throws InterruptionSaisieException {
        do {
            // On récupère une chaîne de caractère, qu'on valide comme int par la suite
            String chaine = saisirChaine(message);

            try {
                return Integer.parseInt(chaine);
            } catch (NumberFormatException conversionException) {
                System.out.println("L'entier entré n'est pas valide");
            }

        } while (true);     // L'instruction return permet d'arrêter la méthode
    }

    public Object choisirListe(String message, ArrayList<Object> liste) throws InterruptionSaisieException {
        int page = 0;
        int nombrePage = (liste.size() / 8) + 1;
        boolean pagePrecedente;
        boolean pageSuivante;


        do {
            pagePrecedente = page > 0;
            pageSuivante = page < nombrePage - 1;

            // On récupère une chaîne de caractère, qu'on valide comme int par la suite
            int choix = saisirInt(construireMessageListe(message, liste, page, pagePrecedente, pageSuivante));

            if(pagePrecedente && choix == 0) {
                page--;
            }
            if(pageSuivante && choix == 9) {
                page++;
            }
            if(choix >= 1 && choix <= 8) {
                int indice = choix - 1 + page * ELEMENT_PAR_PAGE;

                if(liste.size() < indice) {
                    return liste.get(indice);
                }
            }

            System.out.println("L'option choisie n'est pas valide.");
        } while (true);     // L'instruction return permet d'arrêter la méthode
    }

    private String construireMessageListe(String message, ArrayList<Object> liste, int page,
                                          boolean pagePrecedente, boolean pageSuivante) {
        int premierIndice = page * ELEMENT_PAR_PAGE;
        int dernierIndice = Math.min(((page + 1) * ELEMENT_PAR_PAGE), liste.size()) - 1;

        if(pagePrecedente) {
            message += "\n0) Page precedente";
        }

        for(int i = premierIndice; i < dernierIndice; i++) {
            message += "\n" + (i - premierIndice + 1) + ") " + liste.get(i).toString();
        }

        if(pageSuivante) {
            message += "\n9) Page suivante";
        }

        return message;
    }


}
