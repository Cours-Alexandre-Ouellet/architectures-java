package edu.cegepvicto.application.voyage;

import edu.cegepvicto.application.services.*;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.ControleurAbstrait;

import java.util.ArrayList;
import java.util.HashMap;

public class ControleurVoyage extends ControleurAbstrait {

    public ControleurVoyage(Contexte contexte) {
        super(contexte);
    }

    public void menuVoyage(HashMap<String, Object> parametres) {
        rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", new HashMap<>());
    }

    @SuppressWarnings("unused")
    public void creeItineraire(HashMap<String, Object> parametres) {

        // Premier affichage

        HashMap<String, Object> parametresVue =new HashMap<>();

        try {
            ArrayList<Destination> destinations = ((DestinationDAO) contexte.getLocalisateurService().obtenirService(DestinationDAO_SQL.class)).chargerTout();
            ArrayList<MoyenTransport> moyensTransport = ((MoyenTransportDAO) contexte.getLocalisateurService().obtenirService(MoyenTransportDAO_SQL.class)).chargerTout();

            parametresVue.put("destinations", destinations);
            parametresVue.put("moyensTransport", moyensTransport);
        } catch (Exception exception) {
            parametresVue.put("message", exception.getMessage());
        }

        rendreVue("edu.cegepvicto.application.voyage.vues.FormulaireItineraire", parametresVue);
    }

    public void enregistrerItineraire(HashMap<String, Object> parametres){

        Destination depart = (Destination) parametres.get("depart");
        Destination arrivee = (Destination) parametres.get("arrivee");
        MoyenTransport moyenTransport = (MoyenTransport) parametres.get("moyenTransport");

        if(depart == null || arrivee == null || moyenTransport == null) {
            // Erreur, paramètre null
        }

        HashMap<String, Object> parametresVue = new HashMap<>();

        try {
            Itineraire itineraire = new Itineraire(depart, arrivee, moyenTransport);
            ItineraireDAO daoItineraire = (ItineraireDAO) contexte.getLocalisateurService().obtenirService(ItineraireDAO_SQL.class);

            daoItineraire.enregistrer(itineraire);

            parametresVue.put("message", "Itineraire enregistre avec succes.");
            rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", parametresVue);
        } catch (Exception exception) {
            parametresVue.put("message", exception.getMessage());
            rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", parametresVue);
        }
    }

    public void ajouterDeplacement(HashMap<String, Object> parametres) {

    }

    /**
     * Permet de sélectionner un itineraire parmi la liste des itinéraires disponibles
     * @param parametres
     */
    @SuppressWarnings("unused")
    public void detailItineraire(HashMap<String, Object> parametres) {
        HashMap<String, Object> parametresVue = new HashMap<>();
        ItineraireDAO itineraireDAO = (ItineraireDAO) contexte.getLocalisateurService().obtenirService(ItineraireDAO_SQL.class);

        try {
            parametresVue.put("itineraires", itineraireDAO.chargerTout());
        } catch (Exception exception) {
            parametresVue.put("message", exception.getMessage());
        }

        rendreVue("edu.cegepvicto.application.voyage.vues.DetailItineraire", parametresVue);
    }





}
