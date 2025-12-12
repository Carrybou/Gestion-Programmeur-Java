package org.example.dao;

import org.example.model.employe.Employe;
import org.example.model.projet.Projet;

import java.util.List;

public interface ActionsBDDInterface {

    /**
     * Afficher tous les employés.
     */
    List<Employe> getAllEmployes() throws Exception;

    /**
     * Afficher un employé par son id.
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
    boolean addEmploye(Employe employe) throws Exception;

    /**
     * Modifier le salaire d’un employé.
     *
     * @return true si la mise à jour a réussi.
     */
    boolean updateSalaireEmploye(int idEmploye, double nouveauSalaire) throws Exception;

    /**
     * Afficher la liste de tous les projets.
     */
    List<Projet> getAllProjets() throws Exception;

    /**
     * Obtenir la liste des employés qui travaillent sur un même projet.
     */
    List<Employe> getEmployesByProjet(int idProjet) throws Exception;
}
