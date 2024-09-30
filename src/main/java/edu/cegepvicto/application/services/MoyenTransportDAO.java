package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;

import java.util.ArrayList;

public abstract class MoyenTransportDAO extends DAOGenerique<MoyenTransport> {

    /**
     * Enregistre un moyen de transport dans la base de données.
     * @param moyenTransport le moyen de transport à enregistrer.
     * @return true si le moyen de transport a pu être enregistré, false sinon.
     */
    @Override
    public abstract void enregistrer(MoyenTransport moyenTransport) throws Exception;

    /**
     * Charge en mémoire un moyen de transport en particulier.
     * @param id l'identifiant du moyen de transport à charger.
     * @return le moyen de transport qui a été chargé.
     * @throws java.util.NoSuchElementException si aucun moyen de transport ne porte l'id recherché.
     */
    @Override
    public abstract MoyenTransport chargerParId(int id) throws Exception;

    /**
     * Charge tous les moyens de transport de la base de données.
     * @return la liste de tous les moyens de transport.
     */
    @Override
    public abstract ArrayList<MoyenTransport> chargerTout() throws Exception;
}
