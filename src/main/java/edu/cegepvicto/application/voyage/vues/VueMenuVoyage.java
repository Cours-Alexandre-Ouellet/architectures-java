package edu.cegepvicto.application.voyage.vues;

import edu.cegepvicto.application.services.StringUtilService;
import edu.cegepvicto.mvc.Contexte;
import edu.cegepvicto.mvc.VueAbstraite;

import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("unused")
public class VueMenuVoyage extends VueAbstraite {

    @SuppressWarnings("unused")
    public VueMenuVoyage(Contexte contexte) {
        super(contexte);
    }

    @Override
    public void afficher(HashMap<String, Object> parametres) {

        boolean saisieValide;
        Scanner lecteur = new Scanner(System.in);
        StringUtilService stringService =
                (StringUtilService) contexte.getLocalisateurService().obtenirService(StringUtilService.class);

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
                            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "creeItineraire", null);
                        case 2:
                            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "ajouterDeplacement", null);
                        case 3:
                            rediriger("edu.cegepvicto.application.voyage.ControleurVoyage",
                                    "afficherItineraire", null);
                        default:
                            throw new Exception("La valeur saisie ne correspond pas à une option");
                    }
                } else if (!stringService.verifierCaractere(saisie, 'Q')) {
                    throw new Exception("La valeur saisie ne correspond pas à une option");
                }

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                saisieValide = false;
            }
        } while (!saisieValide);
    }



}