package org.example.model.projet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un projet avec ses caractéristiques principales.
 * Un projet possède un intitulé, des dates de début et de fin prévue,
 * un état, un prix, un chef de projet et une équipe de programmeurs.
 *

 */
public class Projet {
    /**
     * L'identifiant unique du projet (généré automatiquement par la base de données)
     */
    private Integer id;

    /**
     * L'intitulé du projet
     */
    private String intitule;

    /**
     * La date de début du projet
     */
    private LocalDate dateDebut;

    /**
     * La date de fin prévue du projet
     */
    private LocalDate dateFinPrevue;

    /**
     * L'état actuel du projet
     */
    private EtatProjet etat;

    /**
     * Le prix du projet
     */
    private double prix;

    /**
     * Le chef de projet responsable (doit avoir la fonction CHEF_DE_PROJET)
     */
    private Employe chefDeProjet;

    /**
     * La liste des programmeurs assignés au projet (doivent avoir la fonction PROGRAMMEUR)
     */
    private List<Employe> programmeurs;

    /**
     * Constructeur par défaut.
     * Initialise la liste des programmeurs.
     */
    public Projet() {
        this.programmeurs = new ArrayList<>();
        this.etat = EtatProjet.EN_ATTENTE;
    }

    /**
     * Constructeur avec paramètres principaux.
     *
     * @param intitule le titre du projet
     * @param dateDebut la date de début du projet
     * @param dateFinPrevue la date de fin prévue du projet
     * @param prix le prix du projet
     * @param chefDeProjet le chef de projet responsable
     * @throws IllegalArgumentException si le chef de projet n'a pas la fonction CHEF_DE_PROJET
     */
    public Projet(String intitule, LocalDate dateDebut, LocalDate dateFinPrevue,
                  double prix, Employe chefDeProjet) {
        this();
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.prix = prix;
        setChefDeProjet(chefDeProjet);
    }

    /**
     * Retourne l'identifiant unique du projet.
     *
     * @return l'ID du projet (peut être null si le projet n'a pas encore été persisté)
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit l'identifiant du projet.
     *
     * @param id le nouvel ID du projet
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * Retourne l'intitulé du projet.
     *
     * @return l'intitulé du projet
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Définit l'intitulé du projet.
     *
     * @param intitule le nouvel intitulé du projet
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Retourne la date de début du projet.
     *
     * @return la date de début
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début du projet.
     *
     * @param dateDebut la nouvelle date de début
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Retourne la date de fin prévue du projet.
     *
     * @return la date de fin prévue
     */
    public LocalDate getDateFinPrevue() {
        return dateFinPrevue;
    }

    /**
     * Définit la date de fin prévue du projet.
     *
     * @param dateFinPrevue la nouvelle date de fin prévue
     */
    public void setDateFinPrevue(LocalDate dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    /**
     * Retourne l'état actuel du projet.
     *
     * @return l'état du projet
     */
    public EtatProjet getEtat() {
        return etat;
    }

    /**
     * Définit l'état du projet.
     *
     * @param etat le nouvel état du projet
     */
    public void setEtat(EtatProjet etat) {
        this.etat = etat;
    }

    /**
     * Retourne le prix du projet.
     *
     * @return le prix du projet
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Définit le prix du projet.
     *
     * @param prix le nouveau prix du projet
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Retourne le chef de projet responsable.
     *
     * @return le chef de projet
     */
    public Employe getChefDeProjet() {
        return chefDeProjet;
    }

    /**
     * Définit le chef de projet responsable.
     * Vérifie que l'employé a bien la fonction CHEF_DE_PROJET.
     *
     * @param chefDeProjet le nouveau chef de projet
     * @throws IllegalArgumentException si l'employé n'a pas la fonction CHEF_DE_PROJET
     */
    public void setChefDeProjet(Employe chefDeProjet) {
        if (chefDeProjet != null && !chefDeProjet.estChefDeProjet()) {
            throw new IllegalArgumentException("L'employé doit avoir la fonction CHEF_DE_PROJET");
        }
        this.chefDeProjet = chefDeProjet;
    }

    /**
     * Retourne la liste des programmeurs assignés au projet.
     *
     * @return la liste des programmeurs
     */
    public List<Employe> getProgrammeurs() {
        return programmeurs;
    }

    /**
     * Définit la liste des programmeurs assignés au projet.
     *
     * @param programmeurs la nouvelle liste des programmeurs
     */
    public void setProgrammeurs(List<Employe> programmeurs) {
        this.programmeurs = programmeurs;
    }

    /**
     * Ajoute un programmeur à l'équipe du projet.
     * Vérifie que l'employé a bien la fonction PROGRAMMEUR.
     *
     * @param programmeur le programmeur à ajouter
     * @return true si l'ajout a réussi, false sinon
     * @throws IllegalArgumentException si l'employé n'a pas la fonction PROGRAMMEUR
     */
    public boolean ajouterProgrammeur(Employe programmeur) {
        if (programmeur == null) {
            return false;
        }
        if (!programmeur.estProgrammeur()) {
            throw new IllegalArgumentException("L'employé doit avoir la fonction PROGRAMMEUR");
        }
        if (!programmeurs.contains(programmeur)) {
            return programmeurs.add(programmeur);
        }
        return false;
    }

    /**
     * Retire un programmeur de l'équipe du projet.
     *
     * @param programmeur le programmeur à retirer
     * @return true si le retrait a réussi, false sinon
     */
    public boolean retirerProgrammeur(Employe programmeur) {
        return programmeurs.remove(programmeur);
    }

    /**
     * Retourne le nombre de programmeurs assignés au projet.
     *
     * @return le nombre de programmeurs
     */
    public int getNombreProgrammeurs() {
        return programmeurs.size();
    }

    /**
     * Retourne une représentation textuelle du projet.
     *
     * @return une chaîne de caractères représentant le projet
     */
    @Override
    public String toString() {
        return "Projet{" +
                "intitule='" + intitule + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFinPrevue=" + dateFinPrevue +
                ", etat=" + etat +
                ", prix=" + prix +
                ", chefDeProjet=" + (chefDeProjet != null ? chefDeProjet.getNomComplet() : "non assigné") +
                ", nombreProgrammeurs=" + programmeurs.size() +
                '}';
    }
}