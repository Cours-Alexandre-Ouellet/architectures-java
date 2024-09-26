package edu.cegepvicto.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Classe de base pour la gestion des contrôleurs pour l'application
 */
public abstract class ControleurAbstrait {

    /**
     * Le contexte de l'application
     */
    protected Contexte contexte;

    public ControleurAbstrait(Contexte contexte) {
        this.contexte = contexte;
    }

    protected void rendreVue(String nomVue, HashMap<String, Object> parametres) {
        try {
            VueAbstraite vue = (VueAbstraite) Class.forName(nomVue).getConstructor(Contexte.class).newInstance(contexte);
            vue.afficher(parametres);

        } catch (ClassNotFoundException classeException) {
            classeException.printStackTrace();
        } catch (NoSuchMethodException constructeurException) {
            constructeurException.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {
            initialisationException.printStackTrace();
        }
    }

}
