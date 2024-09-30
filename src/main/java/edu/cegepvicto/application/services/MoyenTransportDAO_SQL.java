package edu.cegepvicto.application.services;

import edu.cegepvicto.application.dummy.ConnexionSQL;
import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoyenTransportDAO_SQL extends MoyenTransportDAO implements IService {

    ConnexionSQL gestionnaireConnexion;

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }

    @Override
    public void enregistrer(MoyenTransport moyenTransport) throws Exception {

    }

    @Override
    public MoyenTransport chargerParId(int id) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT * from MoyenTransport WHERE id = ?");
            requete.setInt(1, id);
            ResultSet resultat = requete.executeQuery();

            if(resultat.next()) {
                return new MoyenTransport(
                        id,
                        resultat.getString("nom"),
                        resultat.getFloat("vitesse")
                );
            }
            throw new Exception("Impossible de charger le moyen de transport avec l'id " + id + ".");

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger le moyen de transport avec l'id " + id + ".");
        }
    }

    @Override
    public ArrayList<MoyenTransport> chargerTout() throws Exception {
        return null;
    }
}
