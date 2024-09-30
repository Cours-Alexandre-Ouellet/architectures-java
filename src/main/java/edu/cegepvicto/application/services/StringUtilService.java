package edu.cegepvicto.application.services;

import edu.cegepvicto.localisateurservice.IService;
import edu.cegepvicto.localisateurservice.LocalisateurService;

public class StringUtilService implements IService {


    @Override
    public void initialiser(LocalisateurService localisateurService) {

    }

    public boolean estEntier(String saisie) {
        try {
            Integer.parseInt(saisie);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean verifierCaractere(String chaine, char caractere) {
        return verifierCaractere(chaine, caractere, true);
    }

    public boolean verifierCaractere(String chaine, char caractere, boolean convertirMajuscule){
        if(convertirMajuscule) {
            chaine = chaine.toUpperCase();
        }

        return (chaine.length() == 1 && chaine.charAt(0) == caractere);
    }
}
