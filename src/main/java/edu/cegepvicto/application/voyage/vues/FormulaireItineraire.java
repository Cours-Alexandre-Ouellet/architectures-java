package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.InterruptionSaisieException;
import edu.cegepvicto.application.services.SaisieConsoleService;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.mvc.AppelSysteme;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Crée un nouveau formulaire pour ajouter des itinéraires de voyage.
 */
@SuppressWarnings("unused")
public class FormulaireItineraire extends VueAbstraite {

    /**
     * Constructeur pour créer un nouveau formulaire.
     * @param contexte le contexte d'exécution de l'application.
     */
    @SuppressWarnings("unused")
    public FormulaireItineraire(Contexte contexte) {
        super(contexte);
    }

    /**
     * Affiche le formulaire pour saisir un nouvel itinéraire.
     * @param parametres les paramètres de la vue.
     * @return le prochain appel à faire au système.
     */
    @Override
    public AppelSysteme afficher(HashMap<String, Object> parametres) {
        SaisieConsoleService consoleService = (SaisieConsoleService) contexte.getLocalisateurService().
                obtenirService(SaisieConsoleService.class);

        System.out.println("Saisie d'un nouvel itinéaire\n=======================");

        System.out.println(parametres.get("message"));

        try {
            // Affichage du formulaire
            Destination depart = (Destination) consoleService.choisirListe("Choisissez la destination de depart.",
                    (ArrayList<Object>) parametres.get("destinations"));

            Destination arrivee = (Destination) consoleService.choisirListe("Choisissez la destination d'arrivée.",
                    (ArrayList<Object>) parametres.get("destinations"));

            MoyenTransport moyenTransport  = (MoyenTransport) consoleService.choisirListe("Choisissez le moyen de transport " +
                            "que vous voulez employer.",
                    (ArrayList<Object>) parametres.get("moyensTransport"));

            // Création des paramètres de retour
            HashMap<String, Object> parametresControleur = new HashMap<>();

            parametresControleur.put("depart", depart);
            parametresControleur.put("arrivee", arrivee);
            parametresControleur.put("moyenTransport", moyenTransport);

            // Appel du contrôleur
            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "enregistrerItineraire",
                    parametresControleur);

        } catch (InterruptionSaisieException interruptionException) {
            System.out.println(interruptionException.getMessage());
            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "menuVoyage",
                    null);
        }
    }
}
