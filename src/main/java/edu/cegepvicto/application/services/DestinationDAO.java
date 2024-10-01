package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Destination;

import java.util.ArrayList;

/**
 * DAO de base pour la gestion des destinations disponibles dans le système.
 */
public abstract class DestinationDAO extends DAOGenerique<Destination> {

    /**
     * Enregistre une destination dans la base de données
     * @param destination la destination à enregistrer
     * @return true si la destination a pu être enregistrée, false sinon
     */
    public abstract void enregistrer(Destination destination) throws Exception;

    /**
     * Charge en mémoire une destination en particulier
     * @param id l'identifiant de la destination à charger
     * @return la destination qui a été chargé
     * @throws java.util.NoSuchElementException si aucune destination ne porte l'id recherché
     */
    public abstract Destination chargerParId(int id) throws Exception;

    /**
     * Charge toutes les destinations de la base de données
     * @return la liste de tous les destinations
     */
    public abstract ArrayList<Destination> chargerTout() throws Exception;

}
