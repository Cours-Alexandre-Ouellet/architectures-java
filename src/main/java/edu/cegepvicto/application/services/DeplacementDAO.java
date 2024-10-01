package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.application.voyage.modeles.Destination;

import java.util.ArrayList;

/**
 * Interface de gestion de base de la DAO pour les déplacements
 */
public abstract class DeplacementDAO extends DAOGenerique<Deplacement> {

    /**
     * Enregistre un deplacement dans la base de données
     * @param deplacement le deplacement à enregistrer
     * @return true si le deplacement a pu être enregistré, false sinon
     * @throws Exception si une erreur SQL survient
     */
    public abstract void enregistrer(Deplacement deplacement) throws Exception;

    /**
     * Enregistre un deplacement dans la base de données
     * @param deplacement le deplacement à enregistrer
     * @param itineraire l'identifiant de l'itinéaire à enregistrer
     * @param ordre dans quel ordre est-ce que le déplacement apparaît
     * @return true si la destination a pu être enregistrée, false sinon
     * @throws Exception si une erreur SQL survient
     */
    public abstract void enregistrer(Deplacement deplacement,int itineraire, int ordre) throws Exception;


    /**
     * Charge en mémoire un déplacement en particulier
     * @param id l'identifiant du déplacement à charger
     * @return le déplacement qui a été chargé
     * @throws Exception si aucun deplacement ne porte l'id recherché ou qu'une erreur SQL survient
     */
    public abstract Deplacement chargerParId(int id) throws Exception;

    /**
     * Charge tous les déplacements de la base de données
     * @return la liste de tous les déplacements
     * @throws Exception si une erreur SQL survient
     */
    public abstract ArrayList<Deplacement> chargerTout() throws Exception;

    /**
     * Charge tous les déplacements pour un itinéraire
     * @param idItineraire l'identifiant de l'itinéraire à charger
     * @return la liste de tous les déplacements pour l'itinéraire
     * @throws Exception si aucun itineraire ne porte l'id recherché ou qu'un erreur SQL survient
     */
    public abstract ArrayList<Deplacement> chargerToutPourItineraire(int idItineraire) throws Exception;


}
