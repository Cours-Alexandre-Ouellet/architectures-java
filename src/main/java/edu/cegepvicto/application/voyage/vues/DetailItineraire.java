package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.InterruptionSaisieException;
import edu.cegepvicto.application.services.SaisieConsoleService;
import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.mvc.AppelSysteme;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Affiche les détails d'un itinéraire.
 */
public class DetailItineraire extends VueAbstraite {

    /**
     * Constructeur pour créer une nouvelle vue détaillée de l'itinéraire.
     * @param contexte le contexte d'exécution de l'application.
     */
    public DetailItineraire(Contexte contexte) {
        super(contexte);
    }

    /**
     * Affiche le choix des itinéraire et permet d'en sélectionner un pour l'afficher en détail.
     * @param parametres les paramètres de la vue.
     * @return le prochain appel à faire au système.
     */
    @Override
    public AppelSysteme afficher(HashMap<String, Object> parametres) {
        String messageErreur = (String) parametres.get("message");
        if(messageErreur != null) {
            System.out.println(messageErreur);
            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "menuVoyage", null);
        }

        SaisieConsoleService consoleService = (SaisieConsoleService) contexte.getLocalisateurService().obtenirService(SaisieConsoleService.class);

        try {
            // Choix de l'itinéraire
            Itineraire itineraire = (Itineraire) consoleService.choisirListe("Quel itineraire doit etre affiche ?",
                    (ArrayList<Object>) parametres.get("itineraires"));


            // Affichage en détails
            System.out.println(itineraire);
        } catch (InterruptionSaisieException exception) {
            System.out.println(exception.getMessage());

        } finally {
            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "menuVoyage",
                    null);
        }
    }
}
