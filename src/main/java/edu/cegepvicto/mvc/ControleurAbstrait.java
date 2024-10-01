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

    /**
     * Crée un nouveau contrôleur abstrait pour un contexte d'application
     * @param contexte
     */
    public ControleurAbstrait(Contexte contexte) {
        this.contexte = contexte;
    }

    /**
     * Permet au contrôleur de générer une vue.
     * @param nomVue le nom de la vue à générer.
     * @param parametres les paramètres à envoyer vers la vue.
     * @return l'appel système généré après l'exécution de la vue.
     */
    protected AppelSysteme rendreVue(String nomVue, HashMap<String, Object> parametres) {
        try {
            VueAbstraite vue = (VueAbstraite) Class.forName(nomVue).getConstructor(Contexte.class).newInstance(contexte);
            return vue.afficher(parametres);

        } catch (ClassNotFoundException classeException) {
            System.out.println("FATAL : la vue " + nomVue + " n'est pas trouvee.");
        } catch (NoSuchMethodException constructeurException) {
            System.out.println("FATAL : la vue " + nomVue + " ne définit pas de constructeur qui accepte un Contexte.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {
            System.out.println("FATAL : impossible de créer " + nomVue + ", sa configuration est invalide.");
        }

        return null;
    }

}
