package edu.cegepvicto.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Définit un appel à un contrôleur du système.
 */
public class AppelSysteme {

    /**
     * Nom du contrôleur à appeler.
     */
    private String nomControleur;

    /**
     * Nom de la méthode à appeler.
     */
    private String nomMethode;

    /**
     * Paramètres à utiliser lors de l'appel du constructeur.
     */
    private HashMap<String, Object> parametresAppel;

    /**
     * Crée un nouvel appel à l'une des fonctionnalités du système.
     * @param nomControleur le nom du contrôleur qui est appelée.
     * @param nomMethode le nom de la méthode qui est appelée.
     * @param parametresAppel les paramètres à utiliser dans l'appel du contrôleur.
     */
    public AppelSysteme(String nomControleur, String nomMethode, HashMap<String, Object> parametresAppel) {
        this.nomControleur = nomControleur;
        this.nomMethode = nomMethode;
        this.parametresAppel = parametresAppel;
    }

    /**
     * Exécute l'appel du contrôleur.
     * @param contexte le contexte d'appel dans le système.
     * @return le prochain appel à faire dans le système ou null s'il faut arrêter le système.
     */
    public AppelSysteme executer(Contexte contexte) {
        try {
            // Crée une instance du contrôleur basé sur son nom.
            ControleurAbstrait controleur = (ControleurAbstrait) Class.forName(nomControleur).
                    getConstructor(Contexte.class).newInstance(contexte);

            // Trouve la méthode selon son nom.
            Method methode = controleur.getClass().getMethod(nomMethode, HashMap.class);

            // Exécute et retourne le prochain appel.
            return (AppelSysteme) methode.invoke(controleur, parametresAppel);
        } catch (ClassNotFoundException classeException) {
            System.out.println("FATAL : le controleur " + nomControleur + " n'est pas trouve.");
        } catch (NoSuchMethodException methodException) {
            System.out.println("FATAL : la methode " + nomMethode + " n'est pas trouvee.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {
            System.out.println("FATAL : erreur dans la definition de la methode appelee (" + nomControleur + "." + nomMethode + ").");
        }

        return null;
    }

}
