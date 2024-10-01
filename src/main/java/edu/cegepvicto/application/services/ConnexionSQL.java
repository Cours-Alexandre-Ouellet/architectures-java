package edu.cegepvicto.application.services;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gère la connexion à une base de données SQL
 */
public class ConnexionSQL implements IService {

    /**
     * Contient la connexion à la base de données
     */
    private Connection connexion;

    /**
     * Hôte de la BD
     */
    private final String HOTE = "jdbc:mysql://127.0.0.1:3306/voyage";

    /**
     * Nom de l'utilisateur
     */
    private final String UTILISATEUR = "root";

    /**
     * Mot de passe
     */
    private final String MOT_DE_PASSE = "mysql";

    /**
     * Ouvre une connexion avec le SGBD. Si une connexion est déjà ouverte au moment de l'appel, la connexion
     * précédente est fermée, puis réouverte.
     * @return la connexion active
     * @throws SQLException si jdbc retourne une exception.
     */
    public Connection ouvrirConnexion() throws SQLException {
        if (connexion != null && !connexion.isClosed()) {
            fermerConnexion();
        }

        connexion = DriverManager.getConnection(
                HOTE, UTILISATEUR, MOT_DE_PASSE
        );

        return connexion;
    }

    /**
     * Ferme la connexion si elle n'est pas déjà fermée.
     * @throws SQLException si jdbc retourne une exception.
     */
    public void fermerConnexion() throws SQLException {
        if (connexion != null && !connexion.isClosed()) {
            connexion.close();
        }

        connexion = null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {
        // N'a pas d'effet.
    }
}

