package edu.cegepvicto.application.services;

import java.sql.*;
import java.util.ArrayList;

import edu.cegepvicto.application.voyage.modeles.Deplacement;
import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

/**
 * Permet la manipulation des données dans un format SQL.
 */
public class DeplacementDAO_SQL extends DeplacementDAO implements IService {

    /**
     * Gère la connexion SQL
     */
    private ConnexionSQL gestionnaireConnexion;

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
    public void enregistrer(Deplacement deplacement) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {

            PreparedStatement requete;

            requete = connexion.prepareStatement(
                    "INSERT INTO Deplacement (destination, moyenTransport) VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS);


            requete.setInt(1, getId(deplacement.getDestination()));
            requete.setInt(2, getId(deplacement.getMoyenTransport()));

            requete.executeUpdate();

            ResultSet cleGenere = requete.getGeneratedKeys();

            if (cleGenere.next()) {
                setId(deplacement, cleGenere.getInt(1));
            }

            gestionnaireConnexion.fermerConnexion();

        } catch (SQLException exception) {
            throw new Exception("Impossible d'enregistrer l'objet " + deplacement.toString());
        }
    }

    /**
     * @inheritDoc
     */
    public void enregistrer(Deplacement deplacement, int idItineraire, int ordre) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {

            PreparedStatement requete = connexion.prepareStatement(
                    "INSERT INTO Deplacement (destination, moyenTransport, ordre, itineraire) VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            requete.setInt(1, getId(deplacement.getDestination()));
            requete.setInt(2, getId(deplacement.getMoyenTransport()));
            requete.setInt(3, ordre);
            requete.setInt(4, idItineraire);


            requete.executeUpdate();


            ResultSet cleGenere = requete.getGeneratedKeys();

            if (cleGenere.next()) {
                setId(deplacement, cleGenere.getInt(1));
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
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, destination, moyenTransport FROM Deplacement WHERE id = ?");
            requete.setInt(1, id);
            ResultSet resultats = requete.executeQuery();

            DeplacementBrut donneesDeplacement = null;

            // Accumulation des données
            if (resultats.next()) {
                donneesDeplacement = new DeplacementBrut(
                        resultats.getInt("id"),
                        resultats.getInt("destination"),
                        resultats.getInt("moyenTransport")
                );
            }

            if (donneesDeplacement == null) {
                throw new SQLException();
            }

            DestinationDAO destinationDAO = new DestinationDAO_SQL();
            MoyenTransportDAO moyenTransportDAO = new MoyenTransportDAO_SQL();

            Deplacement deplacement = new Deplacement(
                    donneesDeplacement.id,
                    destinationDAO.chargerParId(donneesDeplacement.idDestination),
                    moyenTransportDAO.chargerParId(donneesDeplacement.idMoyenTransport)
            );


            gestionnaireConnexion.fermerConnexion();
            return deplacement;

        } catch (SQLException exception) {
            throw new Exception("Impossible de charger la collection de deplacement.");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Deplacement> chargerTout() throws Exception {

        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, destination, moyenTransport, ordre FROM Deplacement");
            ResultSet resultats = requete.executeQuery();

            ArrayList<DeplacementBrut> donneesDeplacements = new ArrayList<>();

            // Accumulation des données
            while (resultats.next()) {
                DeplacementBrut donneesDeplacement = new DeplacementBrut(
                        resultats.getInt("id"),
                        resultats.getInt("destination"),
                        resultats.getInt("moyenTransport"),
                        resultats.getInt("ordre")
                );
                donneesDeplacements.add(donneesDeplacement);
            }

            // Résolution des liens vers les autres objets
            ArrayList<Deplacement> deplacements = new ArrayList<>();
            DestinationDAO destinationDAO = new DestinationDAO_SQL();
            MoyenTransportDAO moyenTransportDAO = new MoyenTransportDAO_SQL();

            for (DeplacementBrut donneesDeplacement : donneesDeplacements) {
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

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<Deplacement> chargerToutPourItineraire(int idItineraire) throws Exception {
        try (Connection connexion = gestionnaireConnexion.ouvrirConnexion()) {
            PreparedStatement requete = connexion.prepareStatement("SELECT id, destination, moyenTransport, ordre FROM Deplacement WHERE itineraire = ?");
            requete.setInt(1, idItineraire);
            ResultSet resultats = requete.executeQuery();

            ArrayList<DeplacementBrut> donneesDeplacements = new ArrayList<>();

            // Accumulation des données
            while (resultats.next()) {
                DeplacementBrut donneesDeplacement = new DeplacementBrut(
                        resultats.getInt("id"),
                        resultats.getInt("destination"),
                        resultats.getInt("moyenTransport"),
                        resultats.getInt("ordre")
                );
                donneesDeplacements.add(donneesDeplacement);
            }

            // Résolution des liens vers les autres objets
            ArrayList<Deplacement> deplacements = new ArrayList<>();
            DestinationDAO destinationDAO = (DestinationDAO) localisateurService.obtenirService(DestinationDAO_SQL.class);
            MoyenTransportDAO moyenTransportDAO = (MoyenTransportDAO) localisateurService.obtenirService(MoyenTransportDAO_SQL.class);

            for (DeplacementBrut donneesDeplacement : donneesDeplacements) {
                deplacements.add(new Deplacement(
                        donneesDeplacement.id,
                        destinationDAO.chargerParId(donneesDeplacement.idDestination),
                        moyenTransportDAO.chargerParId(donneesDeplacement.idMoyenTransport)
                ));
            }
            triSelonOrdre(deplacements, donneesDeplacements);


            gestionnaireConnexion.fermerConnexion();
            return deplacements;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new Exception("Impossible de charger la collection de deplacement pour l'itineraire avec id = " + idItineraire + ".");
        }
    }

    /**
     * Tri le tableau des déplacements selon l'ordre des clés passés en parametres. Suppose que les cles et les
     * deplacement sont dans le même ordre (autrement dit que les id concordent)
     *
     * @param deplacements le tableau à trier
     * @param cles         les objets qui contiennent les clés (ordre)
     */
    private void triSelonOrdre(ArrayList<Deplacement> deplacements, ArrayList<DeplacementBrut> cles) {
        for (int i = 0; i < deplacements.size(); i++) {
            for (int j = i; j < deplacements.size(); j++) {
                if (cles.get(i).ordre > cles.get(j).ordre) {
                    // Permuter les objets pour les trier
                    Object temp = deplacements.get(i);
                    deplacements.set(i, deplacements.get(j));
                    deplacements.set(j, (Deplacement) temp);

                    // Permuter les clés
                    temp = cles.get(i);
                    cles.set(i, cles.get(j));
                    cles.set(j, (DeplacementBrut) temp);
                }
            }
        }
    }

    /**
     * Stocke les données tel quelles sont chargées de la table SQL dans la base de données.
     */
    private class DeplacementBrut {
        public int id;

        public int idDestination;

        public int idMoyenTransport;

        public int ordre;

        public DeplacementBrut(int id, int idDestination, int idMoyenTransport) {
            this.id = id;
            this.idDestination = idDestination;
            this.idMoyenTransport = idMoyenTransport;
        }

        public DeplacementBrut(int id, int idDestination, int idMoyenTransport, int ordre) {
            this.id = id;
            this.idDestination = idDestination;
            this.idMoyenTransport = idMoyenTransport;
            this.ordre = ordre;
        }
    }
}
