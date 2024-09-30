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
        rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", null);
    }

    @SuppressWarnings("Appelée par réflexivité")
    public void creeItineraire(HashMap<String, Object> parametres) {

        // Premier affichage
        if (parametres == null) {
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

        // Sinon on enregistre le formulaire

        Destination depart = (Destination) parametres.get("Depart");
        Destination arrivee = (Destination) parametres.get("Arrivee");
        MoyenTransport moyenTransport = (MoyenTransport) parametres.get("MoyenTransport");

        if(depart == null || arrivee == null || moyenTransport == null) {
            // Erreur, paramètre null
        }

        try {
            Itineraire itineraire = new Itineraire(depart, arrivee, moyenTransport);
            ItineraireDAO daoItineraire = (ItineraireDAO) contexte.getLocalisateurService().obtenirService(ItineraireDAO_SQL.class);

            daoItineraire.enregistrer(itineraire);
            // rendreVue : succès
        } catch (Exception exception) {
            // rendreVue : échec
        }
    }

    public void ajouterDeplacement(HashMap<String, Object> parametres) {

    }

}
