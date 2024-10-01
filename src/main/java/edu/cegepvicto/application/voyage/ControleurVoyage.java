package edu.cegepvicto.application.voyage;

import edu.cegepvicto.application.services.*;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.mvc.AppelSysteme;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.ControleurAbstrait;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contrôleur des voyages. Permet de définir et de modifier des itinéraires.
 */
public class ControleurVoyage extends ControleurAbstrait {

    /**
     * Crée un nouveau contrôleur de voyages.
     *
     * @param contexte le contexte associé au contrôleur.
     */
    @SuppressWarnings("unused")
    public ControleurVoyage(Contexte contexte) {
        super(contexte);
    }

    /**
     * Affiche le menu principal du voyage.
     *
     * @param parametres les paramètres du menu. Contient un message optionnel.
     * @return le prochain appel aux fonctionnalités du système.
     */
    @SuppressWarnings("unused")
    public AppelSysteme menuVoyage(HashMap<String, Object> parametres) {
        return rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", new HashMap<>());
    }

    /**
     * Gère la création d'un itinéraire nouveau
     * @param parametres les paramètres de la création. Paramètre inutilisé.
     * @return le prochain appel aux fonctionnalités du système.
     */
    @SuppressWarnings("unused")
    public AppelSysteme creeItineraire(HashMap<String, Object> parametres) {

        // Les paramètres qui seront passés à la vue
        HashMap<String, Object> parametresVue = new HashMap<>();

        try {
            // On essaie de charger la liste des destinations et des moyens de transport possibles avec les DAO
            ArrayList<Destination> destinations = ((DestinationDAO) contexte.getLocalisateurService().obtenirService(DestinationDAO_SQL.class)).chargerTout();
            ArrayList<MoyenTransport> moyensTransport = ((MoyenTransportDAO) contexte.getLocalisateurService().obtenirService(MoyenTransportDAO_SQL.class)).chargerTout();

            // Arrimage des paramètres
            parametresVue.put("destinations", destinations);
            parametresVue.put("moyensTransport", moyensTransport);
        } catch (Exception exception) {
            //
            parametresVue.put("message", exception.getMessage());
        }

        return rendreVue("edu.cegepvicto.application.voyage.vues.FormulaireItineraire", parametresVue);
    }

    /**
     * Permet d'enregistrer un nouvel itinéaire dans la base de données.
     * @param parametres les paramètres de l'itinéraire à enregistrer.
     * @return le prochain appel aux fonctionnalités du système.
     */
    @SuppressWarnings("unused")
    public AppelSysteme enregistrerItineraire(HashMap<String, Object> parametres) {

        // Récupération des paramètres
        Destination depart = (Destination) parametres.get("depart");
        Destination arrivee = (Destination) parametres.get("arrivee");
        MoyenTransport moyenTransport = (MoyenTransport) parametres.get("moyenTransport");

        // Paramètres de la vue générée.
        HashMap<String, Object> parametresVue = new HashMap<>();

        // Si l'un des paramètres ne peut être lu, alors on échoue et on affiche un message d'erreur à l'utilisateur.
        if (depart == null || arrivee == null || moyenTransport == null) {
            parametresVue.put("message", "Une valeur de l'itineraire n'a pu etre chargee.");
        }

        try {
            // Création de l'itinéraire
            Itineraire itineraire = new Itineraire(depart, arrivee, moyenTransport);
            ItineraireDAO daoItineraire = (ItineraireDAO) contexte.getLocalisateurService().obtenirService(ItineraireDAO_SQL.class);

            // Sauvegarde de l'itinéraire
            daoItineraire.enregistrer(itineraire);
            parametresVue.put("message", "Itineraire enregistre avec succes.");

        } catch (Exception exception) {
            parametresVue.put("message", exception.getMessage());
        }

        // Dans tous les cas, on retourne au menu des voyages
        return rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", parametresVue);
    }

    public void ajouterDeplacement(HashMap<String, Object> parametres) {

    }

    /**
     * Permet de sélectionner un itineraire parmi la liste des itinéraires disponibles
     *
     * @param parametres les paramètre la vue - inutilisé.
     */
    @SuppressWarnings("unused")
    public AppelSysteme detailItineraire(HashMap<String, Object> parametres) {
        // Charge l'ensemble des itinéraires en mémoire
        ItineraireDAO itineraireDAO = (ItineraireDAO) contexte.getLocalisateurService().obtenirService(ItineraireDAO_SQL.class);

        // Crée les paramètres de la vue
        HashMap<String, Object> parametresVue = new HashMap<>();

        try {
            parametresVue.put("itineraires", itineraireDAO.chargerTout());
        } catch (Exception exception) {
            parametresVue.put("message", exception.getMessage());
        }

        // Retourne la vue d'affichage des détails
        return rendreVue("edu.cegepvicto.application.voyage.vues.DetailItineraire", parametresVue);
    }


}
