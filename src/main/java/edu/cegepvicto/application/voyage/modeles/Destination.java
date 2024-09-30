package edu.cegepvicto.application.voyage.modeles;

public class Destination {

    private int id;

    private String ville;

    private String region;

    private String pays;

    public Destination(int id, String ville, String region, String pays) {
        this.id = id;
        this.ville = ville;
        this.region = region;
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
