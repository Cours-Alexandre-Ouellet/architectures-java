package edu.cegepvicto.application.voyage;

import edu.cegepvicto.application.services.DeplacementDAO_SQL;
import edu.cegepvicto.application.services.IDeplacementDAO;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.ControleurAbstrait;

import java.util.HashMap;

public class ControleurVoyage extends ControleurAbstrait {

    public ControleurVoyage(Contexte contexte) {
        super(contexte);
    }

    public void menuVoyage(HashMap<String, Object> parametres) {
        rendreVue("edu.cegepvicto.application.voyage.vues.VueMenuVoyage", null);
    }

    public void creeItineraire(HashMap<String, Object> parametres) {

        // Premier affichage
        if(parametres == null) {
            rendreVue("edu.cegepvicto.application.voyage.vues.FormulaireItineraire", null);
        }

        Destination depart = (Destination) parametres.get("Depart");
        Destination fin = (Destination) parametres.get("Fin");
        MoyenTransport moyenTransport = (MoyenTransport) parametres.get("MoyenTransport");

        if (depart == null || fin == null || moyenTransport == null) {
            // Erreur, paramètre null
        }

        Itineraire itineraire = new Itineraire(depart, fin, moyenTransport);
        IDeplacementDAO daoDeplacement = (IDeplacementDAO) contexte.getLocalisateurService().obtenirService(DeplacementDAO_SQL.class);

        // rendreVue ...
    }

    public void ajouterDeplacement(HashMap<String, Object> parametres) {

    }

}
