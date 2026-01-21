package org.example.ui;

import org.example.dao.ActionsBDD;
import org.example.model.employe.Employe;
import org.example.model.employe.EmployeTag;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    private void deleteEmploye(int id) {
        try {
            boolean ok = actionsBDD.deleteEmployeById(id); // adapte si ta méthode a un autre nom
            if (ok) {
                System.out.println("Employé supprimé (id=" + id + ").");
            } else {
                System.out.println("Suppression impossible : aucun employé avec id=" + id);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addEmploye(String nom, String prenom, String adresse, int anNaissance, float salaire, float prime , String dateEmbauche, String email, int idResponsable)  {
        try {
            // int id conversion
            Employe responsable = actionsBDD.getEmployeById(idResponsable);

            // Date conversion
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateEmbauche, formatter);
            Date date = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );
            boolean ok = actionsBDD.addEmploye(
                    nom,
                    prenom,
                    anNaissance,
                    salaire, prime,
                    email,
                    date,
                    true,
                    EmployeTag.PROGRAMMEUR,
                    adresse,
                    responsable
            );
            if (ok) {
                System.out.println("AJOUT RÉUSSIE !");
            } else {
                System.out.println("ajout impossible");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void modifyEmployeSalary(int id, float newSalaire)
    {
        try {
            boolean ok  = actionsBDD.updateSalaireEmploye(id, newSalaire);

            if (ok) {
                System.out.println("MODIFICATION RÉUSSIE !");
            } else {
                System.out.println("ajout impossible");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws Exception {
        Scanner clavier = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println(
                    "********* MENU *********\n"
                            + "1. Afficher tous les programmeurs\n"
                            + "2. Afficher un programmeur\n"
                            + "3. Supprimer un programmeur\n"
                            + "4. Ajouter un programmeur\n"
                            + "5.  Modifier le salaire\n"
                            + "8. Quitter le programme\n\n"
                            + "Quel est votre choix ? "
            );

            int choix;
            try {
                choix = clavier.nextInt();
                clavier.nextLine();
            } catch (Exception e) {
                System.out.println("Veuillez saisir un nombre parmi les choix. \n");
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

                case 4:
                    System.out.println("Saisissez le prénom du programmeur : ");
                    String prenom = clavier.nextLine();

                    System.out.println("Saisissez le nom du programmeur : ");
                    String nom = clavier.nextLine();

                    System.out.println("Saisissez l'an de naissance du programmeur : ");
                    int anNaissance = Integer.parseInt(clavier.nextLine());

                    System.out.println("Saisissez le salaire du programmeur : ");
                    float salaire = clavier.nextFloat();
                    clavier.nextLine();

                    System.out.println("Saisissez la prime du programmeur : ");
                    float prime = clavier.nextFloat();
                    clavier.nextLine();

                    System.out.println("Saisissez le mail du programmeur : ");
                    String mail = clavier.nextLine();

                    System.out.println(
                            "Saisissez la date d'embauche du programmeur " +
                                    "(si null tapez \"null\", format jj/mm/aaaa)"
                    );
                    String dateEmbauche = clavier.nextLine();
                    if ("null".equalsIgnoreCase(dateEmbauche)) {
                        dateEmbauche = null;
                    }

                    System.out.println("Saisissez l'adresse du programmeur : ");
                    String adresse = clavier.nextLine();

                    System.out.println("Saisissez l'id du responsable du programmeur : ");
                    int idResponsable = Integer.parseInt(clavier.nextLine());

                    addEmploye(
                            nom,
                            prenom,
                            adresse,
                            anNaissance,
                            salaire,
                            prime,
                            dateEmbauche,
                            mail,
                            idResponsable
                    );
                    break;

                case 5:
                    int cnt = 5;
                    int id5 = -1;

                    while (cnt >= 0) {
                        System.out.print("Saisissez l'id de l'employé : ");
                        id5 = clavier.nextInt();
                        clavier.nextLine();
                        if (!displayUniqueEmploye(id5)) System.out.println("Recherche KO !\n");
                        else {
                            id5 = -1;
                            cnt = 0;
                        };
                        cnt--;
                    }


                    System.out.print("Saisissez le nouveau salaire de l'employé id : ");
                    float salaireEmploye = clavier.nextFloat();
                    modifyEmployeSalary(id5, salaireEmploye);
                    clavier.nextLine();
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
