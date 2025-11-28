package org.example.model.projet;

/**
 * Énumération représentant les différents états possibles d'un projet.
 *

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