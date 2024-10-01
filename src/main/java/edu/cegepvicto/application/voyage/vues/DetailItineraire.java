package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.InterruptionSaisieException;
import edu.cegepvicto.application.services.SaisieConsoleService;
import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailItineraire extends VueAbstraite {

    public DetailItineraire(Contexte contexte) {
        super(contexte);
    }

    @Override
    public void afficher(HashMap<String, Object> parametres) {
        String messageErreur = (String) parametres.get("message");
        if(messageErreur != null) {
            System.out.println(messageErreur);
            return;
        }

        SaisieConsoleService consoleService = (SaisieConsoleService) contexte.getLocalisateurService().obtenirService(SaisieConsoleService.class);

        try {
            Object choix = consoleService.choisirListe("Quel itineraire doit etre affiche ?", (ArrayList<Object>) parametres.get("itineraires"));
            Itineraire itineraire = (Itineraire) choix;

            System.out.println(itineraire);


        } catch (InterruptionSaisieException exception) {
            System.out.println(exception.getMessage());

        } finally {
            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "menuVoyage",
                    null);
        }
    }
}
