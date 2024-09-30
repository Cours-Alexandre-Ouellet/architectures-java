package edu.cegepvicto.application.services;

import java.sql.*;
import java.util.ArrayList;

import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.application.dummy.ConnexionSQL;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

/**
 * Permet la manipulation des données dans un format SQL.
 */
public class DeplacementDAO_SQL extends DeplacementDAO implements IService {

    ConnexionSQL gestionnaireConnexion;

    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void enregistrer(Deplacement deplacement) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {

            int id = getId(deplacement);
            boolean estInsertion = id > 0;

            PreparedStatement requete;
            if (estInsertion) {
                requete = connexion.prepareStatement(
                        "INSERT INTO Deplacement (destination, moyenTransport) VALUES (?, ?);",
                        Statement.RETURN_GENERATED_KEYS);
            } else {
                requete = connexion.prepareStatement("UPDATE projet SET destination = ?, moyenTransport = ? WHERE id = ?");
            }

            requete.setInt(1, getId(deplacement.getDestination()));
            requete.setInt(2, getId(deplacement.getMoyenTransport()));

            if (!estInsertion) {
                requete.setInt(3, id);
            }

            requete.executeUpdate();

            if (estInsertion) {
                ResultSet cleGenere = requete.getGeneratedKeys();

                if (cleGenere.next()) {
                    setId(deplacement, cleGenere.getInt(1));
                }
            }

            gestionnaireConnexion.fermerConnexion();

        } catch (SQLException exception) {
            throw new Exception("Impossible d'enregistrer l'objet " + deplacement.toString());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Deplacement chargerParId(int id) throws Exception {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Deplacement> chargerTout() throws Exception {

        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT * from Deplacement");
            ResultSet resultats = requete.executeQuery();


            ArrayList<DeplacementBrut> donneesDeplacements = new ArrayList<>();


            // Accumulation des données
            while (resultats.next()) {
                DeplacementBrut donneesDeplacement = new DeplacementBrut(
                        resultats.getInt("id"),
                        resultats.getInt("destination"),
                        resultats.getInt("moyenTransport")
                );
                donneesDeplacements.add(donneesDeplacement);
            }

            // Résolution des liens vers les autres objets
            ArrayList<Deplacement> deplacements = new ArrayList<>();
            DestinationDAO destinationDAO = new DestinationDAO_SQL();
            MoyenTransportDAO moyenTransportDAO = new MoyenTransportDAO_SQL();

            for(DeplacementBrut donneesDeplacement : donneesDeplacements) {
                deplacements.add(new Deplacement(
                        donneesDeplacement.id,
                        destinationDAO.chargerParId(donneesDeplacement.idDestination),
                        moyenTransportDAO.chargerParId(donneesDeplacement.idMoyenTransport)
                ));
            }

            gestionnaireConnexion.fermerConnexion();
            return deplacements;
        } catch (SQLException exception) {
            throw new Exception("Impossible de charger la collection de deplacement.");
        }

    }

    private class DeplacementBrut {
        public int id;

        public int idDestination;

        public int idMoyenTransport;

        public DeplacementBrut(int id, int idDestination, int idMoyenTransport) {
            this.id = id;
            this.idDestination = idDestination;
            this.idMoyenTransport = idMoyenTransport;
        }
    }
}
