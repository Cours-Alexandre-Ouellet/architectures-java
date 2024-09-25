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

    protected VueAbstraite RendreVue(String nomVue, HashMap<String, Object> parametres) {
        try {
            return (VueAbstraite) Class.forName(nomVue).getConstructor().newInstance();

        } catch (ClassNotFoundException classeException) {

        } catch (NoSuchMethodException constructeurException) {

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {

        }

        return null;
    }

}
