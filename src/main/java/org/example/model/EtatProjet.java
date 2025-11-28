package org.example.model;

/**
 * Énumération représentant les différents états possibles d'un projet.
 *
 * @author Votre Nom
 * @version 1.0
 */
public enum EtatProjet {
    /**
     * Le projet est en attente de démarrage
     */
    EN_ATTENTE,

    /**
     * Le projet est en cours de réalisation
     */
    EN_COURS,

    /**
     * Le projet est temporairement suspendu
     */
    SUSPENDU,

    /**
     * Le projet est terminé avec succès
     */
    TERMINE,

    /**
     * Le projet a depassé la deadline
     */
    EN_RETARD,

    /**
     * Le projet a été annulé
     */
    ANNULE
}