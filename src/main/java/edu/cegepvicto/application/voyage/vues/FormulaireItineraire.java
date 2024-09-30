package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.InterruptionSaisieException;
import edu.cegepvicto.application.services.SaisieConsoleService;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

@SuppressWarnings("unused")
public class FormulaireItineraire extends VueAbstraite {

    @SuppressWarnings("unused")
    public FormulaireItineraire(Contexte contexte) {
        super(contexte);
    }

    @Override
    public void afficher(HashMap<String, Object> parametres) {
        SaisieConsoleService consoleService = (SaisieConsoleService) contexte.getLocalisateurService().
                obtenirService(SaisieConsoleService.class);

        System.out.println("Saisie d'un nouvel itinéaire\n=======================");

        try {
            // Affichage du formulaire
            Destination depart = (Destination) consoleService.choisirListe("Choisissez la destination de depart.",
                    (ArrayList<Object>) parametres.get("Destinations"));

            Destination arrivee = (Destination) consoleService.choisirListe("Choisissez la destination d'arrivée.",
                    (ArrayList<Object>) parametres.get("Destinations"));

            MoyenTransport moyenTransport  = (MoyenTransport) consoleService.choisirListe("Choisissez le moyen de transport " +
                            "que vous voulez employer.",
                    (ArrayList<Object>) parametres.get("Moyens de transport"));

            // Création des paramètres de retour
            HashMap<String, Object> parametresControleur = new HashMap<>();

            parametresControleur.put("Depart", depart);
            parametresControleur.put("Arrivee", arrivee);
            parametresControleur.put("MoyenTransport", moyenTransport);

            // Appel du contrôleur
            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "creeItineraire",
                    parametresControleur);

        } catch (InterruptionSaisieException interruptionException) {
            System.out.println(interruptionException.getMessage());
            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage", "menuVoyage",
                    null);
        }
    }
}
