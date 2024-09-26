package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Itineraire;

import java.util.ArrayList;

public interface IItineraireDAO extends IGenericDAO<Itineraire> {

    @Override
    public void enregistrer(Itineraire objet) throws Exception;

    @Override
    public Itineraire chargerParId(int id) ;

    @Override
    public ArrayList<Itineraire> chargerTout() ;
}
