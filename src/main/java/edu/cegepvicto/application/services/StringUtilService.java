package edu.cegepvicto.application.services;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

/**
 * Fournit des méthodes de validation des chaînes de caractère
 */
public class StringUtilService implements IService {

    /**
     * @inheritDoc
     */
    @Override
    public void initialiser(LocalisateurService localisateurService) {

    }

    /**
     * Vérifie si la chaîne est un entier.
     * @param chaine la chaîne de caractère à valider.
     * @return true si c'est un entier, false sinon.
     */
    public boolean estEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Vérifie si un caractère sous forme de String concorde avec un char.
     * @param chaine la chaîne à valider.
     * @param caractere le caractère recherché.
     * @return true si le caractère correspond, false sinon.
     */
    public boolean verifierCaractere(String chaine, char caractere) {
        return verifierCaractere(chaine, caractere, false);
    }

    /**
     * Vérifie si un caractère sous forme de String concorde avec un char.
     * @param chaine la chaîne à valider.
     * @param caractere le caractère recherché.
     * @param insensibleCasse indique si la recherche est insensible à la casse.
     * @return true si le caractère correspond, false sinon.
     */
    public boolean verifierCaractere(String chaine, char caractere, boolean insensibleCasse){
        if(insensibleCasse) {
            chaine = chaine.toUpperCase();
        }

        return (chaine.length() == 1 && chaine.charAt(0) == caractere);
    }
}
