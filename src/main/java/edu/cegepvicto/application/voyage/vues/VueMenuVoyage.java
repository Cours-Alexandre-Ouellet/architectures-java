package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.StringUtilService;
import edu.cegepvicto.mvc.AppelSysteme;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Affiche le menu pour gérer les différentes options de voyage.
 */
@SuppressWarnings("unused")
public class VueMenuVoyage extends VueAbstraite {

    /**
     * Crée une nouvelle vue pour les options de voyage.
     * @param contexte le contexte associé au menu du voyage.
     */
    @SuppressWarnings("unused")
    public VueMenuVoyage(Contexte contexte) {
        super(contexte);
    }

    /**
     * Affiche le menu du voyage et gère la saisie au clavier.
     * @param parametres les paramètres de la vue.
     * @return
     */
    @Override
    public AppelSysteme afficher(HashMap<String, Object> parametres) {

        boolean saisieValide;
        Scanner lecteur = new Scanner(System.in);
        StringUtilService stringService =
                (StringUtilService) contexte.getLocalisateurService().obtenirService(StringUtilService.class);

        afficherMessage(parametres);

        do {
            try {
                saisieValide = true;

                System.out.println("Menu de voyage");
                System.out.println("1) Ajouter un itinéraire");
                System.out.println("2) Ajouter un déplacement dans un itinéaire");
                System.out.println("3) Afficher un itinéaire");
                System.out.println("Q) Quitter");

                String saisie = lecteur.nextLine();

                if (stringService.estEntier(saisie)) {
                    int valeur = Integer.parseInt(saisie);

                    switch (valeur) {
                        case 1:
                            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "creeItineraire", null);
                        case 2:
                            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "ajouterDeplacement", null);
                        case 3:
                            return rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "detailItineraire", null);
                        default:
                            throw new Exception("La valeur saisie ne correspond pas à une option");
                    }
                } else if (!stringService.verifierCaractere(saisie, 'Q', true)) {
                    throw new Exception("La valeur saisie ne correspond pas à une option");
                }

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                saisieValide = false;
            }
        } while (!saisieValide);

        return null;
    }



}