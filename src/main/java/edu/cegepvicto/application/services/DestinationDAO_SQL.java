package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Destination;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Spécialisation de la DAO pour gérer des destinations accessibles.
 */
public class DestinationDAO_SQL extends DestinationDAO implements IService {

    /**
     * Gère la connexion SQL
     */
    private ConnexionSQL gestionnaireConnexion;

    /**
     * @inheritDoc
     */
    @Override
    public void enregistrer(Destination objet) throws Exception {
        throw new UnsupportedOperationException("Operation d'enregistrement d'objet non supportee.");
    }

    /**
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Destination> chargerTout() throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, ville, region, pays FROM Destination");
            ResultSet resultats = requete.executeQuery();

            ArrayList<Destination> destinations = new ArrayList<>();

            // Accumulation des données
            while (resultats.next()) {
                destinations.add(new Destination(
                        resultats.getInt("id"),
                        resultats.getString("ville"),
                        resultats.getString("region"),
                        resultats.getString("pays")
                ));
            }

            gestionnaireConnexion.fermerConnexion();
            return destinations;

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger la collection de destinations.");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }
}
