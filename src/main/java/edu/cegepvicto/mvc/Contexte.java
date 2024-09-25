package edu.cegepvicto.mvc;

import edu.cegepvicto.localisateurservice.LocalisateurService;

/**
 * Données pour le fonctionnement de l'application. Ce sont les seules données persistantes entre les contrôleurs.
 */
public class Contexte {

    private final LocalisateurService localisateurService;

    /**
     * Crée un nouveau contexte
     */
    public Contexte() {
        localisateurService = new LocalisateurService();
    }

    public LocalisateurService getLocalisateurService() {
        return localisateurService;
    }
}
