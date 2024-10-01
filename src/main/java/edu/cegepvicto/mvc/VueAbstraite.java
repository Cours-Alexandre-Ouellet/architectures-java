package edu.cegepvicto.mvc;

import java.util.HashMap;

/**
 * Classe de base pour la gestion des vues affichées en console.
 */
public abstract class VueAbstraite {

    /**
     * Le contexte d'exécution du système.
     */
    protected Contexte contexte;

    /**
     * Crée une nouvelle vue abstraite.
     *
     * @param contexte le contexte d'exécution du système.
     */
    public VueAbstraite(Contexte contexte) {
        this.contexte = contexte;
    }

    /**
     * Affiche la vue dans la console.
     *
     * @param parametres les paramètres de la vue.
     * @return l'appel système à faire pour passer vers la prochaine fonctionnalité.
     */
    public abstract AppelSysteme afficher(HashMap<String, Object> parametres);

    /**
     * Crée une redirection vers un autre contrôleur de l'application.
     *
     * @param nomControleur le nom du contrôleur vers lequel se rediriger.
     * @param nomMethode    le nom de la méthode à appeler.
     * @param parametres    l'ensemble des paramètres à passer.
     * @return un objet qui gère l'appel fait au système.
     */
    public AppelSysteme rediriger(String nomControleur, String nomMethode, HashMap<String, Object> parametres) {
        return new AppelSysteme(nomControleur, nomMethode, parametres);
    }

    /**
     * Affiche un message contenu dans un ensemble de parametres
     *
     * @param parametres les paramètres associés à la vue.
     */
    protected void afficherMessage(HashMap<String, Object> parametres) {
        String message = (String) parametres.get("message");
        if (message != null) {
            System.out.println(message);
        }
    }
}
