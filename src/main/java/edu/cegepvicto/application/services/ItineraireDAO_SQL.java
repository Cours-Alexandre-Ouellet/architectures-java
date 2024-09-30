package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.application.dummy.ConnexionSQL;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.util.ArrayList;

public class ItineraireDAO_SQL extends ItineraireDAO implements IService {

    ConnexionSQL connexionBD;

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        connexionBD = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }

    @Override
    public void enregistrer(Itineraire objet) throws Exception {

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
