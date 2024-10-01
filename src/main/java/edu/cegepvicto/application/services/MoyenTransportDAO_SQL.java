package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.MoyenTransport;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Gère la monipulation des données des moyens de transport avec une base de données MySQL.
 */
public class MoyenTransportDAO_SQL extends MoyenTransportDAO implements IService {

    /**
     * Gère la connexion à la base de données MySQL
     */
    ConnexionSQL gestionnaireConnexion;

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void enregistrer(MoyenTransport moyenTransport) throws Exception {

    }

    /**
     * @inheritDoc
     */
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
                        resultat.getInt("vitesse")
                );
            }
            throw new Exception("Impossible de charger le moyen de transport avec l'id " + id + ".");

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger le moyen de transport avec l'id " + id + ".");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<MoyenTransport> chargerTout() throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, nom, vitesse FROM MoyenTransport");
            ResultSet resultats = requete.executeQuery();

            ArrayList<MoyenTransport> moyenTransports = new ArrayList<>();

            // Accumulation des données
            while (resultats.next()) {
                moyenTransports.add(new MoyenTransport(
                        resultats.getInt("id"),
                        resultats.getString("nom"),
                        resultats.getInt("vitesse")
                ));
            }

            gestionnaireConnexion.fermerConnexion();
            return moyenTransports;

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger la collection de moyens de transport.");
        }
    }
}
