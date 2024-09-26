package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.application.dummy.BDSQL;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.util.ArrayList;

public class IItineraireDAO_SQL implements IItineraireDAO, IService {

    BDSQL connexionBD;

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        connexionBD = (BDSQL) localisateurService.obtenirService(BDSQL.class);
    }

    @Override
    public void enregistrer(Itineraire objet) throws Exception {
        try {
            connexionBD.inserer(objet);
        } catch (Error error) {
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Itineraire chargerParId(int id) {
        return null;
    }

    @Override
    public ArrayList<Itineraire> chargerTout() {
        return null;
    }
}
