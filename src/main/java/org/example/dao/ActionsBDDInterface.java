package org.example.dao;

import org.example.model.employe.Employe;
import org.example.model.employe.EmployeTag;
import org.example.model.projet.Projet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ActionsBDDInterface {

    /**
     * Récupérer tous les employés.
     */
    List<Employe> getAllEmployes() throws Exception;

    /**
     * Récupérer un employé par son id.
     */
    Employe getEmployeById(int idEmploye) throws Exception;

    /**
     * Supprimer un employé par son id.
     *
     * @return true si la suppression a réussi, false sinon (id non trouvé, par ex.).
     */
    boolean deleteEmployeById(int idEmploye) throws Exception;

    /**
     * Ajouter un nouvel employé.
     *
     * @return true si l'insertion a réussi.
     */
    boolean addEmploye(String nom, String prenom, int anNaissance, float salaire, float prime, String email, java.util.Date date_embauche, boolean actif, EmployeTag code_metier, String adresse, Employe responsable) throws Exception;

    /**
     * Modifier le salaire d’un employé.
     *
     * @return true si la mise à jour a réussi.
     */
    boolean updateSalaireEmploye(int idEmploye, float nouveauSalaire) throws Exception;

    /**
     * Récupérer la liste de tous les projets.
     */
    List<Projet> getAllProjets() throws Exception;

    /**
     * Récupérer un projet par son id.
     */
    Projet getProjetById(int idProjet) throws Exception;

    /**
     * Récupérer une liste d'employé par l'id d'un projet.
     */
    //List<Employe> getEmployesByProjetId(int idProjet) throws Exception;

    List<Projet> getProjetsByEmployeId(int idEmploye) throws SQLException;
}
