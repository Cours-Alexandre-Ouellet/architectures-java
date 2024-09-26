package edu.cegepvicto.localisateurservice;

/**
 * Représente une classe de service pour le système. Les classes de service devraient être codées sans état.
 */
public interface IService {

    /**
     * Initialise le service et ses dépendances
     * @param localisateurService référence vers le localisateur pour l'initialisation des services dépendants
     */
    public void initialiser(LocalisateurService localisateurService);

}
