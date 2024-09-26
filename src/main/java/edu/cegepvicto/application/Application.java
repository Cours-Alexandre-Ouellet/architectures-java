package edu.cegepvicto.application;

import edu.cegepvicto.application.voyage.ControleurVoyage;
import edu.cegepvicto.application.voyage.vues.VueMenuVoyage;
import edu.cegepvicto.mvc.Contexte;

public class Application {

    public static void main(String[] args) {
        ControleurVoyage controleurVoyage = new ControleurVoyage(new Contexte());
        controleurVoyage.menuVoyage(null);
    }

}
