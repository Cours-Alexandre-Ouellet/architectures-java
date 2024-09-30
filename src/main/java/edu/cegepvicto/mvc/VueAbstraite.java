package edu.cegepvicto.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class VueAbstraite {

    protected Contexte contexte;

    public VueAbstraite(Contexte contexte) {
        this.contexte = contexte;
    }

    public abstract void afficher(HashMap<String, Object> parametres);

    public void rediriger(String nomControleur, String nomMethode, HashMap<String, Object> parametres) {
        try {
            ControleurAbstrait controleur = (ControleurAbstrait) Class.forName(nomControleur).
            getConstructor(Contexte.class).newInstance(contexte);

            Method methode = controleur.getClass().getMethod(nomMethode, HashMap.class);
            methode.invoke(controleur, parametres);

        } catch (ClassNotFoundException classeException) {

        } catch (NoSuchMethodException methodException) {

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {

        }
    }
}
