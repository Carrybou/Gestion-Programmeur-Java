package org.example.model;

import java.util.Date;

public class Programmeur {
    private final int id_programmeur;
    private String nom;
    private String prenom;
    private int anNaissance;
    private float salaire;
    private float prime;
    private String email;
    private Date date_embauche;
    private boolean actif;

    // Constructeur Naturel (info depuis la bdd)
    Programmeur(int id, String nom, String prenom, int anNaissance, float salaire, float prime, String email, Date date_embauche, boolean actif) {
        this.id_programmeur = id;
        this.prenom = prenom;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.email = email;
        this.date_embauche = date_embauche;
        this.actif = actif;
    }

    // Constructeur
}
