package edu.cegepvicto.application.services;

import java.util.ArrayList;

import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.application.dummy.BDSQL;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;


/**
 * Permet la manipulation des données dans un format SQL.
 */
public class DeplacementDAO_SQL implements IDeplacementDAO, IService {

    BDSQL connexionBD;

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        connexionBD = (BDSQL) localisateurService.obtenirService(BDSQL.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void enregistrer(Deplacement deplacement) throws Exception {
        try {
            connexionBD.inserer(deplacement);
        } catch (Error erreur) {
            throw new Exception(erreur.getMessage());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Deplacement chargerParId(int id) {
        return (Deplacement) connexionBD.getElement(Deplacement.class, id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Deplacement> chargerTout() {
        return null;
    }


}
