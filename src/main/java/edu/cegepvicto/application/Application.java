package edu.cegepvicto.application;

import edu.cegepvicto.application.voyage.ControleurVoyage;
import edu.cegepvicto.mvc.AppelSysteme;
import edu.cegepvicto.mvc.Contexte;

/**
 * Permet la gestion de l'application des voyages.
 */
public class Application {

    /**
     * Gère le contexte d'exécution de l'application.
     */
    private Contexte contexte;

    /**
     * Point d'entrée du programme.
     * @param args les arguments du programme.
     */
    public static void main(String[] args) {
        Application application = new Application();
        application.executer();
    }

    /**
     * Crée une nouvelle instance de l'application.
     */
    public Application() {
        contexte  = new Contexte();
    }

    /**
     * Exécute l'application et gère les appels des contrôleurs.
     */
    public void executer() {
        ControleurVoyage controleurVoyage = new ControleurVoyage(contexte);
        AppelSysteme appel =  controleurVoyage.menuVoyage(null);

        while(appel != null) {
            appel = appel.executer(contexte);
        }
    }

}
