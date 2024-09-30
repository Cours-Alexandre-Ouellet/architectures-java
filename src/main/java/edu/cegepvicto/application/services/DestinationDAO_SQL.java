package edu.cegepvicto.application.services;

import edu.cegepvicto.application.dummy.ConnexionSQL;
import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinationDAO_SQL extends DestinationDAO implements IService {

    private ConnexionSQL gestionnaireConnexion;

    @Override
    public void enregistrer(Destination objet) throws Exception {
    }


    @Override
    public Destination chargerParId(int id) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT * from Destination WHERE id = ?");
            requete.setInt(1, id);
            ResultSet resultat = requete.executeQuery();

            if(resultat.next()) {
                return new Destination(
                        id,
                        resultat.getString("ville"),
                        resultat.getString("region"),
                        resultat.getString("pays")
                );
            }
            throw new Exception("Impossible de charger le deplacement avec l'id " + id + ".");

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger le deplacement avec l'id " + id + ".");
        }
    }

    @Override
    public ArrayList<Destination> chargerTout() {
        return null;
    }

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }
}
