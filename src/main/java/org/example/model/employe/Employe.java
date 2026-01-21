package org.example.model.employe;

import org.example.model.projet.Projet;

import java.time.LocalDate;
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
    private LocalDate date_embauche;
    private boolean actif;
    private EmployeTag metier;
    private List<Projet> projetList;


    // Constructeur Naturel
    public Employe(int id, String nom, String prenom, int anNaissance, float salaire, float prime, String email, LocalDate date_embauche, boolean actif, EmployeTag code_metier, String adresse, List<Projet> projetList) {
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
        this.projetList = projetList;
    }

    public Employe(int id_employe)
    {
        this.id_employe = id_employe;
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

    public LocalDate getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(LocalDate date_embauche)
    {
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    @Override
    public String toString() {
        return "Id : " + id_employe + " \n"
         + "Nom : " + nom + " \n"
         + "Prenom : " + prenom + " \n"
         + "Adresse : " + adresse + " \n"
         + "Naissance : " + anNaissance + " \n"
         + "Salaire : " + salaire + "€ \n"
         + "Prime : " + prime + "€ \n"
         + "Responsable : " + (responsable != null ? responsable.getPrenom() + " " + responsable.getNom() : "aucun") + " \n"
         + "Metier : " + metier + " \n"
                + "Date d'embauche : " + (date_embauche != null ? date_embauche: "null") + " \n"
                + "Email : " + email + " \n"
                + "Actif : " + actif + " \n"
                + "Liste des projets : " +
                (projetList == null || projetList.isEmpty()
                        ? "aucun"
                        : projetList.stream()
                        .map(Projet::getIntitule)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("aucun")
                ) + " \n"
         + "--------------------";
    }

    public String toStringSmall() {
        return nom + " " +  prenom;
    }

    // Helpers

    public static Employe findEmployeById(int id, List<Employe> employes) {
        for(Employe employe: employes) {
            if(employe.id_employe == id) return employe;
        }

        return null;
    }
}
