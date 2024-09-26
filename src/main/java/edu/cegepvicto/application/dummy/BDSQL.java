package edu.cegepvicto.application.dummy;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Émule le fonctionnement d'une base de données SQL
 */
public class BDSQL implements IService {

    private HashMap<Class<?>, ArrayList<Object>> donnees;

    private HashMap<Class<?>, Integer> clesPrimaires;

    public BDSQL() {
        donnees = new HashMap<>();
        clesPrimaires = new HashMap<>();
    }

    @Override
    public void initialiser(LocalisateurService localisateurService){

    }

    public int inserer(Object objet) {
        try {
            Class<?> type = objet.getClass();

            ArrayList<Object> table = donnees.getOrDefault(type, new ArrayList<>());
            int clePrimaire = clesPrimaires.getOrDefault(type, 1);

            affecterId(objet, clePrimaire);
            table.add(objet);

            // La table existe déjà => Mise à jour des références
            if (donnees.containsKey(type)) {
                clesPrimaires.replace(type, clePrimaire + 1);
            } else {               // La table n'existe pas => Ajout des références
                donnees.put(type, table);
                clesPrimaires.put(type, clePrimaire + 1);
            }

            return clePrimaire;
        } catch (Exception exception) {
            throw new Error("Impossible d'insérer | " + objet);
        }

    }

    public Object getElement(Class<?> type, int id) {
        try {

            if (donnees.containsKey(type)) {
                Method getId = type.getMethod("getId");

                for (Object element : donnees.get(type)) {
                    int idElement = (int) getId.invoke(element);
                    if (idElement == id) {
                        return element;
                    }
                }
            }

            return null;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
                 InvocationTargetException exception) {
            throw new Error("Impossible de récupéré l'objet | " + type + " | avec id = " + id);
        }
    }

    public ArrayList<Object> chargerTout(Class<?> type) {
        if(donnees.containsKey(type)) {
            return donnees.get(type);
        }

        return null;
    }

    private void affecterId(Object objet, int id) throws Exception {
        try {
            objet.getClass().getMethod("setId", Integer.class).invoke(objet, id);
            clesPrimaires.replace(objet.getClass(), id);

        } catch (NoSuchMethodException | SecurityException exception) {
            throw new Exception(exception);
        } catch (IllegalAccessException | InvocationTargetException appelException) {
            throw new Exception(appelException);
        }
    }

}
