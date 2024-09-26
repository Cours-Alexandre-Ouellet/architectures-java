package edu.cegepvicto.application.services;

import java.util.ArrayList;

public interface IGenericDAO<T> {

    /**
     * Enregistre un objet dans la base de données
     * @param objet l'objet à enregistrer
     * @throws Exception si l'enregistrement est impossible
     */
    void enregistrer(T objet) throws Exception ;

    /**
     * Charge en mémoire un objet en particulier par son id. Requiert que l'objet définisse une méthode getId
     * @param id l'identifiant de l'objet à charger
     * @return l'objet qui a été chargé
     * @throws java.util.NoSuchElementException si aucun objet ne porte l'id recherché
     */
    T chargerParId(int id);

    /**
     * Charge tous les objets de ce type de la base de données
     * @return la liste de tous les objets
     */
    ArrayList<T> chargerTout();

}
