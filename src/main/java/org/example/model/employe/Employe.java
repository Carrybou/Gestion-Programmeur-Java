package org.example.model.employe;

import java.util.Date;
import java.util.List;

public class Employe {
    private final int id_employe;
    private String nom;
    private String prenom;
    private int anNaissance;
    private String adresse;
    private float salaire;
    private float prime;
    private String email;
    private Employe responsable;
    private Date date_embauche;
    private boolean actif;
    private EmployeTag metier;


    // Constructeur Naturel (info depuis la bdd)
    public Employe(int id, String nom, String prenom, int anNaissance, float salaire, float prime, String email, Date date_embauche, boolean actif, EmployeTag code_metier, String adresse) {
        this.id_employe = id;
        this.nom = nom;
        this.prenom = prenom;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.email = email;
        this.date_embauche = date_embauche;
        this.actif = actif;
        this.metier = code_metier;
        this.adresse = adresse;
    }

    public EmployeTag getMetier() {
        return metier;
    }

    public void setMetier(EmployeTag metier) {
        this.metier = metier;
    }

    public int getId_employe() {
        return id_employe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAnNaissance() {
        return anNaissance;
    }

    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setResponsable(Employe employe) {
        this.responsable = employe;
    }

    public String getAdresse() { return adresse; }

    public Employe getResponsable() { return responsable; }

    public void afficher() {
        System.out.println("Id : " + id_employe + "\n");
        System.out.println("Nom : " + nom + "\n");
        System.out.println("Prenom : " + prenom + "\n");
        System.out.println("Adresse : " + adresse + "\n");
        System.out.println("Naissance : " + anNaissance + "\n");
        System.out.println("Salaire : " + salaire + "\n");
        System.out.println("Prime : " + prime + "\n");
        if(responsable != null) System.out.println("Responsable : " + responsable.getPrenom() + " " + responsable.getNom() + "\n");
        System.out.println("Metier : " + metier + "\n");
        System.out.println("--------------------");

    }

    // Helpers
    public static Employe findLastHired(Employe[] employes) {
        Employe latestHired = employes[0];
        for(Employe employe : employes) {
            latestHired = latestHired.date_embauche.after(employe.date_embauche) ? latestHired : employe;
        }

        return latestHired;
    }

    public static Employe findEmployeById(int id, List<Employe> employes) {
        for(Employe employe: employes) {
            if(employe.id_employe == id) return employe;
        }

        return null;
    }
}
