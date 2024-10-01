package edu.cegepvicto.application.voyage.modeles;

/**
 * Représente un moyen de transport entre deux destinations
 */
public class MoyenTransport {

    /**
     * Identifiant unique du moyen de transport.
     */
    private int id;

    /**
     * Le nom du moyen de transport.
     */
    private String nom;

    /**
     * La vitesse moyenne du moyen de transport.
     */
    private int vitesseMoyenne;

    /**
     * Crée un nouveau moyen de transport à partir de données existantes dans la BD.
     * @param id l'identifiant unique du moyen de transport.
     * @param nom le nom du moyen de transport.
     * @param vitesseMoyenne la vitesse moyenne du moyen de transport.
     */
    public MoyenTransport(int id, String nom, int vitesseMoyenne) {
        this.id = id;
        this.nom = nom;
        this.vitesseMoyenne = vitesseMoyenne;
    }

    /**
     * Accesseur de l'identifiant unique.
     * @return la valeur de l'identifiant unique.
     */
    public int getId() {
        return id;
    }

    /**
     * Mutateur de l'identifiant unique.
     * @param id la nouvelle valeur de l'identifiant unique.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Prépare une chaîne qui affiche le detail du moyen de transport.
     * @return
     */
    @Override
    public String toString() {
        return nom + " (" + vitesseMoyenne + " km/h)";
    }
}
