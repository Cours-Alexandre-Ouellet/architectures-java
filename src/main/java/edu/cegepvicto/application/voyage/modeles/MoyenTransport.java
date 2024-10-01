package edu.cegepvicto.application.voyage.modeles;

public class MoyenTransport {

    private int id;

    private String nom;

    private int vitesseMoyenne;

    public MoyenTransport(int id, String nom, int vitesseMoyenne) {
        this.id = id;
        this.nom = nom;
        this.vitesseMoyenne = vitesseMoyenne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MoyenTransport{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", vitesseMoyenne=" + vitesseMoyenne +
                '}';
    }
}
