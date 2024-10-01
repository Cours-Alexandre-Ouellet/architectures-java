package edu.cegepvicto.application.services;

import edu.cegepvicto.application.voyage.modeles.Itineraire;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.sql.*;
import java.util.ArrayList;

/**
 * Gestion des données relatives aux itinéraires
 */
public class ItineraireDAO_SQL extends ItineraireDAO implements IService {

    /**
     * Gestionnaire de la connexion SQL
     */
    ConnexionSQL gestionnaireConnexion;

    /**
     * Référence vers le localisateur de services.
     */
    private LocalisateurService localisateurService;

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {
        gestionnaireConnexion = (ConnexionSQL) localisateurService.obtenirService(ConnexionSQL.class);
        this.localisateurService = localisateurService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void enregistrer(Itineraire itineraire) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {

            PreparedStatement requete;
            requete = connexion.prepareStatement("INSERT INTO Itineraire (depart) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS);

            requete.setInt(1, getId(itineraire.getDepart()));
            requete.executeUpdate();

            ResultSet cleGenere = requete.getGeneratedKeys();

            if (cleGenere.next()) {
                setId(itineraire, cleGenere.getInt(1));
            }

            gestionnaireConnexion.fermerConnexion();
            DeplacementDAO deplacementDAO  = (DeplacementDAO) localisateurService.obtenirService(DeplacementDAO_SQL.class);

            for(int i = 0; i < itineraire.getDeplacements().size() ;i++){
                deplacementDAO.enregistrer(itineraire.getDeplacements().get(i), getId(itineraire), i);
            }

        } catch (SQLException exception) {
            throw new Exception("Impossible d'enregistrer l'itinéaire.");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Itineraire chargerParId(int id) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Itineraire> chargerTout() throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, depart FROM Itineraire");
            ResultSet resultats = requete.executeQuery();

            ArrayList<ItineraireBrut> donneesItineraires = new ArrayList<>();

            // Accumulation des données
            while (resultats.next()) {
                donneesItineraires.add(new ItineraireBrut(
                        resultats.getInt("id"),
                        resultats.getInt("depart")
                ));
            }

            ArrayList<Itineraire> itineraires = new ArrayList<>();

            DestinationDAO destinationDAO = (DestinationDAO) localisateurService.obtenirService(DestinationDAO_SQL.class);
            DeplacementDAO deplacementDAO = (DeplacementDAO) localisateurService.obtenirService(DeplacementDAO_SQL.class);

            for(ItineraireBrut donnees : donneesItineraires) {
                itineraires.add(new Itineraire(
                        donnees.id,
                        destinationDAO.chargerParId(donnees.depart),
                        deplacementDAO.chargerToutPourItineraire(donnees.id)
                ));
            }

            gestionnaireConnexion.fermerConnexion();
            return itineraires;

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new Exception("Impossible de charger la collection des itineraires.");
        }
    }

    private class ItineraireBrut {
        public int id;
        public int depart;

        public ItineraireBrut(int id, int depart) {
            this.id = id;
            this.depart = depart;
        }
    }
}
