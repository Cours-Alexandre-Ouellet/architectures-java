package edu.cegepvicto.application;

import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.ControleurAbstrait;

import javax.sound.sampled.Control;
import java.util.HashMap;

public class ControleurVoyage extends ControleurAbstrait {

    public ControleurVoyage(Contexte contexte) {
        super(contexte);
    }

    public void creeItineraire(HashMap<String, Object> parametres) {
        Destination depart = (Destination) parametres.get("Depart");
        Destination fin = (Destination) parametres.get("Fin");
        MoyenTransport moyenTransport = (MoyenTransport) parametres.get("MoyenTransport");

        if (depart == null || fin == null || moyenTransport == null) {
            // Erreur, paramètre null
        }

        Itineraire itineraire = new Itineraire(depart, fin, moyenTransport);
        contexte.get
    }

    public void ajouterDeplacement(HashMap<String, Object> parametres) {

    }

}
