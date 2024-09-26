package edu.cegepvicto.application.voyage.modeles;

import java.util.ArrayList;
import java.util.List;

public class Itineraire {

    private Destination depart;

    private List<Deplacement> deplacements;

    public Itineraire(Destination depart, Destination fin, MoyenTransport moyenTransport){
        this.depart =  depart;
        deplacements = new ArrayList<>();

        deplacements.add(new Deplacement(fin, moyenTransport));
    }

}
