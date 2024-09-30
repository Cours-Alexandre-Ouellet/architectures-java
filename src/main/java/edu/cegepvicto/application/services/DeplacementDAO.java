package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Deplacement;

import java.util.ArrayList;

/**
 * Interface de gestion de base de la DAO pour les déplacements
 */
public abstract class DeplacementDAO extends DAOGenerique<Deplacement> {

    /**
     * Enregistre un deplacement dans la base de données
     * @param deplacement le deplacement à enregistrer
     * @return true si le deplacement a pu être enregistré, false sinon
     */
    public abstract void enregistrer(Deplacement deplacement) throws Exception;

    /**
     * Charge en mémoire un déplacement en particulier
     * @param id l'identifiant du déplacement à charger
     * @return le déplacement qui a été chargé
     * @throws java.util.NoSuchElementException si aucun deplacement ne porte l'id recherché
     */
    public abstract Deplacement chargerParId(int id) throws Exception;

    /**
     * Charge tous les déplacements de la base de données
     * @return la liste de tous les déplacements
     */
    public abstract ArrayList<Deplacement> chargerTout() throws Exception;

}
