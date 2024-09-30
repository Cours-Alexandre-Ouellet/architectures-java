package edu.cegepvicto.application.services;

import java.util.ArrayList;

public abstract class DAOGenerique<T> {

    /**
     * Enregistre un objet dans la base de données
     *
     * @param objet l'objet à enregistrer
     * @throws Exception si l'enregistrement est impossible
     */
    public abstract void enregistrer(T objet) throws Exception;

    /**
     * Charge en mémoire un objet en particulier par son id. Requiert que l'objet définisse une méthode getId
     *
     * @param id l'identifiant de l'objet à charger
     * @return l'objet qui a été chargé
     * @throws java.util.NoSuchElementException si aucun objet ne porte l'id recherché
     */
    public abstract T chargerParId(int id) throws Exception;

    /**
     * Charge tous les objets de ce type de la base de données
     *
     * @return la liste de tous les objets
     */
    public abstract ArrayList<T> chargerTout() throws Exception;

    /**
     * Retourne l'identifiant d'un objet avec la méthode getId. L'objet doit posséder une méthode
     * public int getId()
     * @param objet l'objet pour lequel récupérer l'id
     * @return la valeur de l'identifiant unique
     * @throws NoSuchMethodException si la méthode getId ne peut être appelée sur l'objet.
     */
    protected int getId(Object objet) throws NoSuchMethodException {
        try {
            return (int) objet.getClass().getMethod("getId").invoke(objet);
        } catch (Exception exception) {
            throw new NoSuchMethodException("Impossible d'accéder à l'id de l'objet (" + objet.getClass() + ")");
        }
    }

    /**
     * Modifier l'identifiant d'un objet avec la méthode setId. L'objet doit posséder une méthode
     * public void setId(int)
     * @param objet l'objet pour lequel changer l'id
     * @param id la nouvelle valeur de l'id
     * @throws NoSuchMethodException si la méthode setId ne peut être appelée sur l'objet.
     */
    protected void setId(Object objet, int id) throws NoSuchMethodException{
        try {
            objet.getClass().getMethod("setId", int.class).invoke(objet, id);
        } catch (Exception exception) {
            throw new NoSuchMethodException("Impossible de modifier l'id de l'objet (" + objet.getClass() + ")");
        }
    }
}
