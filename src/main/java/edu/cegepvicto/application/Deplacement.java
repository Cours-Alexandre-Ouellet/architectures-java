package edu.cegepvicto.application;

public class Deplacement {

    private Destination destination;

    private MoyenTransport moyenTransport;

    public Deplacement(Destination destination, MoyenTransport moyenTransport)
    {
        this.destination = destination;
        this.moyenTransport = moyenTransport;
    }

}
