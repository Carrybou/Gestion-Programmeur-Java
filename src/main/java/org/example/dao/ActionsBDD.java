package org.example.dao;

import org.example.config.DatabaseManager;
import org.example.model.employe.Employe;
import org.example.model.employe.EmployeTag;
import org.example.model.projet.EtatProjet;
import org.example.model.projet.Projet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionsBDD implements ActionsBDDInterface {

    // ===============================
    // Constantes SQL
    // ===============================

    private static final String SELECT_ALL_EMPLOYES =
            "SELECT id_employe, nom, prenom, anNaissance, salaire, prime, email, date_embauche, actif, code_metier, adresse " +
                    "FROM employe ORDER BY id_employe";

    private static final String SELECT_EMPLOYE_BY_ID =
            "SELECT id_employe, nom, prenom, anNaissance, salaire, prime, email, date_embauche, actif, code_metier, adresse, responsable " +
                    "FROM employe WHERE id_employe = ?";

    private static final String DELETE_EMPLOYE_BY_ID =
            "DELETE FROM employe WHERE id_employe = ?";

    private static final String INSERT_EMPLOYE =
            "INSERT INTO employe (nom, prenom, anNaissance, salaire, prime, email, date_embauche, actif, code_metier, adresse, responsable) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SALAIRE_EMPLOYE =
            "UPDATE employe SET salaire = ? WHERE id_employe = ?";

    private static final String SELECT_ALL_PROJETS =
            "SELECT id_projet, intitule, date_debut, date_fin_prevue, etat " +
                    "FROM projet ORDER BY id_projet";

    private static final String SELECT_EMPLOYES_BY_PROJET =
            "SELECT e.id_employe, e.nom, e.prenom, e.anNaissance, e.salaire, e.prime, e.email, e.date_embauche, e.actif, e.code_metier " +
                    "FROM employe e " +
                    "JOIN employe_projet ep ON e.id_employe = ep.id_employe " +
                    "WHERE ep.id_projet = ? " +
                    "ORDER BY e.id_employe";

    private static final String SELECT_ALL_RESPONSABLE_EMPLOYE =
            "SELECT responsable, id_employe FROM employe ORDER BY id_employe";
    // ===============================
    // Implémentation des méthodes
    // ===============================

    @Override
    public List<Employe> getAllEmployes() throws Exception {
        List<Employe> employes = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_EMPLOYES);
             PreparedStatement psr = connection.prepareStatement(SELECT_ALL_RESPONSABLE_EMPLOYE);
             ResultSet rs = ps.executeQuery();
             ResultSet rsr = psr.executeQuery()) {

            while (rs.next()) {
                employes.add(mapEmploye(rs));
            }

            while (rsr.next()) {
                // check if >= 1 because getInt on null value is equal 0
                if (rsr.getInt("responsable") >= 1) employes.get(rsr.getInt("id_employe") - 1).setResponsable(Employe.findEmployeById(rsr.getInt("responsable"), employes));
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de tous les employés", e);
        }

        return employes;
    }

    @Override
    public Employe getEmployeById(int idEmploye) throws Exception {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYE_BY_ID)) {

            ps.setInt(1, idEmploye);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmploye(rs);
                } else {
                    return null; // à gérer dans le Menu : "id non trouvé"
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de l'employé avec l'id " + idEmploye, e);
        }
    }

    @Override
    public boolean deleteEmployeById(int idEmploye) throws Exception {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYE_BY_ID)) {

            ps.setInt(1, idEmploye);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la suppression de l'employé avec l'id " + idEmploye, e);
        }
    }

    @Override
    public boolean addEmploye(Employe employe) throws Exception {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYE)) {

            // À adapter aux noms de getters de ta classe Employe
            ps.setString(1, employe.getNom());
            ps.setString(2, employe.getPrenom());
            ps.setInt(3, employe.getAnNaissance());
            ps.setDouble(4, employe.getSalaire());
            ps.setDouble(5, employe.getPrime());
            ps.setString(6, employe.getEmail());

            if (employe.getDate_embauche() != null) {
                ps.setDate(7, new Date(employe.getDate_embauche().getTime()));
            } else {
                ps.setDate(7, null);
            }

            ps.setBoolean(8, employe.isActif());
            ps.setString(9, employe.getMetier().getCode());

            ps.setString(10, employe.getAdresse());

            ps.setInt(11, employe.getResponsable().getId_employe());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new Exception("Erreur lors de l'ajout d'un nouvel employé", e);
        }
    }

    @Override
    public boolean updateSalaireEmploye(int idEmploye, double nouveauSalaire) throws Exception {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SALAIRE_EMPLOYE)) {

            ps.setDouble(1, nouveauSalaire);
            ps.setInt(2, idEmploye);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la mise à jour du salaire de l'employé " + idEmploye, e);
        }
    }

    @Override
    public List<Projet> getAllProjets() throws Exception {
        List<Projet> projets = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PROJETS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                projets.add(mapProjet(rs));
            }

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de tous les projets", e);
        }

        return projets;
    }

    @Override
    public List<Employe> getEmployesByProjet(int idProjet) throws Exception {
        List<Employe> employes = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYES_BY_PROJET)) {

            ps.setInt(1, idProjet);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    employes.add(mapEmploye(rs));
                }
            }

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération des employés pour le projet " + idProjet, e);
        }

        return employes;
    }

    // ===============================
    // Méthodes de mapping
    // ===============================

    /**
     * Construit un objet Employe à partir d'un ResultSet.
     * À adapter en fonction des constructeurs / setters réels de ta classe Employe.
     */
    private Employe mapEmploye(ResultSet rs) throws SQLException {

        return new Employe(rs.getInt("id_employe"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("anNaissance"), rs.getFloat("salaire"), rs.getFloat("prime"), rs.getString("email"), rs.getDate("date_embauche"), rs.getBoolean("actif"), EmployeTag.fromCode(rs.getString("code_metier")), rs.getString("adresse"));
    }

    /**
     * Construit un objet Projet à partir d'un ResultSet.
     * À adapter en fonction des constructeurs / setters réels de ta classe Projet.
     */
    private Projet mapProjet(ResultSet rs) throws SQLException {
        Projet projet = new Projet(rs.getInt("id_projet"));

        // À adapter selon les noms réels de tes setters
        projet.setIntitule(rs.getString("intitule"));

        Date dateDebut = rs.getDate("date_debut");
        if (dateDebut != null) {
            projet.setDateDebut(dateDebut.toLocalDate());
        }

        Date dateFinPrevue = rs.getDate("date_fin_prevue");
        if (dateFinPrevue != null) {
            projet.setDateFinPrevue(dateFinPrevue.toLocalDate());
        }

        projet.setEtat(EtatProjet.valueOf(rs.getString("etat")));

        return projet;
    }
}
