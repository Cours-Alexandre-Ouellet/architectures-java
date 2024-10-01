package edu.cegepvicto.application.services;

/**
 * Gère les exceptions à cause d'une demande d'interruption dans la console.
 */
public class InterruptionSaisieException extends Exception {

    /**
     * Crée une nouvelle interruption avec un message d'erreur.
     * @param message le message derrière l'interruption.
     */
    public InterruptionSaisieException(String message) {
        super(message);
    }

}
