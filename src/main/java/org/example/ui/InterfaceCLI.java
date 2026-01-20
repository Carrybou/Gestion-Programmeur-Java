package org.example.ui;

import org.example.dao.ActionsBDD;
import org.example.model.employe.Employe;

import java.util.List;
import java.util.Scanner;

public class InterfaceCLI {

    public final ActionsBDD actionsBDD = new ActionsBDD();

    private boolean displayAllEmploye() {
        try {
            List<Employe> employeList = actionsBDD.getAllEmployes();
            if (employeList == null || employeList.isEmpty()) {
                System.out.println("Aucun employé trouvé.");
                return true;
            }

            for (Employe employe : employeList) {
                employe.afficher();
            }
            return true;

        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des employés : " + e.getMessage());
            return false;
        }
    }

    private boolean displayUniqueEmploye(int id) {
        try {
            Employe employe = actionsBDD.getEmployeById(id);
            if (employe == null) {
                System.out.println("Aucun employé trouvé avec l'id : " + id);
                return false;
            }

            employe.afficher();
            return true;

        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'employé : " + e.getMessage());
            return false;
        }
    }

    private boolean deleteEmploye(int id) {
        try {
            boolean ok = actionsBDD.deleteEmployeById(id); // adapte si ta méthode a un autre nom
            if (ok) {
                System.out.println("Employé supprimé (id=" + id + ").");
            } else {
                System.out.println("Suppression impossible : aucun employé avec id=" + id);
            }
            return ok;

        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return false;
        }
    }

    public void run() {
        Scanner clavier = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println(
                    "********* MENU *********\n"
                            + "1. Afficher tous les programmeurs\n"
                            + "2. Afficher un programmeur\n"
                            + "3. Supprimer un programmeur\n"
                            + "8. Quitter le programme\n\n"
                            + "Quel est votre choix ? "
            );

            int choix;
            try {
                choix = clavier.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez saisir un nombre.");
                clavier.nextLine(); // vider l'entrée invalide
                continue;
            }

            switch (choix) {
                case 1:
                    if (!displayAllEmploye()) System.out.println("Recherche KO !\n");;
                    break;

                case 2:
                    System.out.print("Saisissez l'id de l'employé : ");
                    int id2 = clavier.nextInt();
                    if (!displayUniqueEmploye(id2)) System.out.println("Recherche KO !\n");;
                    break;

                case 3:
                    System.out.print("Saisissez l'id de l'employé à supprimer : ");
                    int id3 = clavier.nextInt();
                    deleteEmploye(id3);
                    break;

                case 8:
                    on = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Cette option n'est pas disponible.");
                    break;
            }

            System.out.println();
        }

        clavier.close();
    }
}
