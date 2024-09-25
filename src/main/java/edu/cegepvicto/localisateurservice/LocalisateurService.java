package edu.cegepvicto.localisateurservice;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Permet l'accès et la gestion des instances des différents
 * services
 */
public class LocalisateurService {

    /**
     * Liste des services instanciés dans le localisateur.
     */
    private final ArrayList<IService> services;

    /**
     * Crée le localisateur de service
     */
    public LocalisateurService() {
        services = new ArrayList<>();
    }

    /**
     * Permet d'obtenir une instance d'un service. Si le service n'a jamais été utilisé, alors il est instancié à ce
     * moment.
     * @param serviceAObtenir la classe du service à créer ou obtenir.
     * @return l'instance du service.
     */
    public IService obtenirService(Class<IService> serviceAObtenir) {

        // Recherche le service s'il a déjà été initialisé
        for(IService service : services) {
            if(service.getClass().equals(serviceAObtenir)) {
                return service;
            }
        }

        // Initialise le service
        try {
            IService service = serviceAObtenir.getConstructor().newInstance();
            service.initialiser();

            services.add(service);

            return service;
        } catch (NoSuchMethodException constructeurException) {

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException initialisationException) {

        }

        return null;
    }
}
