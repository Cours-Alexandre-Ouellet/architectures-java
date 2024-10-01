package edu.cegepvicto.mvc;

import edu.cegepvicto.localisateurservice.LocalisateurService;

/**
 * Données pour le fonctionnement de l'application. Ce sont les seules données persistantes entre les contrôleurs.
 */
public class Contexte {

    /**
     * Référence vers le localisateur de services.
     */
    private final LocalisateurService localisateurService;

    /**
     * Crée un nouveau contexte
     */
    public Contexte() {
        localisateurService = new LocalisateurService();
    }

    /**
     * Accesseur du localisateur de services.
     * @return le localisateur de service de l'application.
     */
    public LocalisateurService getLocalisateurService() {
        return localisateurService;
    }
}
