package edu.cegepvicto.application.services;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Offre des méthodes de saisie à la console et redemande l'entrée tant que celle-ci n'est pas valide.
 */
public class SaisieConsoleService implements IService {

    /**
     * Caractère d'échappement
     */
    private final int ESCAPE_CHAR = 33;

    /**
     * Nombre d'éléments présentés en cas d'affichage multipage
     */
    private final int ELEMENT_PAR_PAGE = 8;

    /**
     * Permet de lire dans la console
     */
    private final Scanner lecteur;

    /**
     * Crée un nouveau service en initialisant l'entrée de la console.
     */
    public SaisieConsoleService() {
        lecteur = new Scanner(System.in);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {
        // Sans effet
    }

    /**
     * Fait la lecture d'une chaîne de caractère à l'écran. La touche escape permet de quitter la saisie
     * @param message le message accompagnant la saisie
     * @return la chaîne de caractère saisie
     * @throws InterruptionSaisieException une demande d'interruption a été formulée.
     */
    public String saisirChaine(String message) throws InterruptionSaisieException {
        System.out.println(message + " [appuyez sur ESC + ENTER pour quitter]");
        String chaine = lecteur.nextLine();

        if(chaine.length() > 1 && chaine.charAt(0) == ESCAPE_CHAR) {
            throw new InterruptionSaisieException("Utilisateur demande de quitter");
        }

        return chaine;
    }

    /**
     * Fait la lecture d'un entier dans la console. Si l'entier n'est pas valide, alors il est redemandé tant qu'il n'est
     * pas valide.
     * @param message le message accompagnant la saisie.
     * @return l'entier saisi à la console.
     * @throws InterruptionSaisieException une demande d'interruption a été formulée.
     */
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

    /**
     * Permet de choisir un item dans un liste d'objets. La méthode toString de chaque objet est utilisé pour l'afficher.
     * @param message le message accompagnant la saisie.
     * @param liste la liste dans laquelle choisir.
     * @return l'item sélectionné dans la liste.
     * @throws InterruptionSaisieException une demande d'interruption a été formulée.
     */
    public Object choisirListe(String message, ArrayList<Object> liste) throws InterruptionSaisieException {
        int page = 0;
        int nombrePage = (liste.size() / 8) + 1;
        boolean pagePrecedente;
        boolean pageSuivante;

        if(liste.isEmpty()) {
            System.out.println("Aucun élément à afficher");
            return null;
        }

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

                if(indice < liste.size() ) {
                    return liste.get(indice);
                }
            }

            System.out.println("L'option choisie n'est pas valide.");
        } while (true);     // L'instruction return permet d'arrêter la méthode
    }

    /**
     * Construit le message pour manipuler la liste dans la console.
     * @param message le message d'indication sur l'action.
     * @param liste la liste à afficher.
     * @param page la page à afficher.
     * @param pagePrecedente est-ce qu'il y a une page précédente de disponible.
     * @param pageSuivante est-ce qu'il y a une page suivante de disponible.
     * @return le message formatté qui affiche toutes les options de la page.
     */
    private String construireMessageListe(String message, ArrayList<Object> liste, int page,
                                          boolean pagePrecedente, boolean pageSuivante) {
        int premierIndice = page * ELEMENT_PAR_PAGE;
        int dernierIndice = Math.min(((page + 1) * ELEMENT_PAR_PAGE), liste.size()) - 1;

        if(pagePrecedente) {
            message += "\n0) Page precedente";
        }

        for(int i = premierIndice; i <= dernierIndice; i++) {
            message += "\n" + (i - premierIndice + 1) + ") " + liste.get(i).toString();
        }

        if(pageSuivante) {
            message += "\n9) Page suivante";
        }

        return message;
    }


}
