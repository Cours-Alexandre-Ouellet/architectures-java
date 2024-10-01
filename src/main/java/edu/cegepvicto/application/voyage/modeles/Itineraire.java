package edu.cegepvicto.application.voyage.modeles;

import java.util.ArrayList;
import java.util.List;

public class Itineraire {

    private int id;

    private Destination depart;

    private List<Deplacement> deplacements;

    public Itineraire(Destination depart, Destination fin, MoyenTransport moyenTransport){
        id = 0;
        this.depart =  depart;
        deplacements = new ArrayList<>();

        deplacements.add(new Deplacement(fin, moyenTransport));
    }

    public Itineraire(int id, Destination depart, List<Deplacement> deplacements){
        this.id = id;
        this.depart = depart;
        this.deplacements = deplacements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Destination getDepart() {
        return depart;
    }

    public List<Deplacement> getDeplacements() {
        return deplacements;
    }

    @Override
    public String toString() {
        String affichage = "Itineraire{" +
                "depart=" + depart +
                ", deplacements={";

        for(Deplacement deplacement : deplacements) {
            affichage += deplacement.toString() + " | ";
        }
        affichage = affichage.substring(0, affichage.length() - 2);
        affichage += "}";

        return affichage;
    }
}
