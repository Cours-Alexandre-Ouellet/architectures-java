package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.application.voyage.modeles.Itineraire;

import java.util.ArrayList;

/**
 * Méthodes d'accès et de chargement des itinéaires.
 */
public abstract class ItineraireDAO extends DAOGenerique<Itineraire> {

    /**
     * Enregistre un itinéraire dans la base de données.
     * @param itineraire l'itinéraire à enregistrer.
     * @return true si l'itinéraire a pu être enregistré, false sinon.
     */
    public abstract void enregistrer(Itineraire itineraire) throws Exception;

    /**
     * Charge en mémoire un itinéraire en particulier.
     * @param id l'identifiant de l'itinéraire à charger.
     * @return l'itinéraire qui a été chargé.
     * @throws java.util.NoSuchElementException si aucun itinéraire ne porte l'id recherché.
     */
    public abstract Itineraire chargerParId(int id) throws Exception;

    /**
     * Charge tous les itinéraires de la base de données.
     * @return la liste de tous les itinéraire.
     */
    public abstract ArrayList<Itineraire> chargerTout() throws Exception;

}
