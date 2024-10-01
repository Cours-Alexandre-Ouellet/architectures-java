package edu.cegepvicto.application.voyage.modeles;

public class Deplacement {

    private int id;

    private Destination destination;


    private MoyenTransport moyenTransport;

    public Deplacement(Destination destination, MoyenTransport moyenTransport) {
        // Appel à une autre définition du constructeur.
        this(-1, destination, moyenTransport);
    }

    public Deplacement(int id, Destination destination, MoyenTransport moyenTransport) {
        this.id = id;
        this.destination = destination;
        this.moyenTransport = moyenTransport;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public MoyenTransport getMoyenTransport() {
        return moyenTransport;
    }

    @Override
    public String toString() {
        return "Deplacement{" +
                "destination=" + destination +
                ", moyenTransport=" + moyenTransport +
                '}';
    }
}
