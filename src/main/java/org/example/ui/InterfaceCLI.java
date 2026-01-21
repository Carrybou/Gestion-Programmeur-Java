package org.example.ui;

import org.example.dao.ActionsBDD;
import org.example.model.employe.Employe;
import org.example.model.employe.EmployeTag;
import org.example.model.projet.Projet;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InterfaceCLI {

    private final ActionsBDD actionsBDD = new ActionsBDD();

    private boolean displayAllEmploye() {
        try {
            List<Employe> employeList = actionsBDD.getAllEmployes();
            if (employeList == null || employeList.isEmpty()) {
                System.out.println("Aucun employé trouvé.");
                return true;
            }

            for (Employe employe : employeList) {
                System.out.println(employe);
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

            System.out.println(employe);
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
            Date date = null;
            if (dateEmbauche != null) {
                LocalDate localDate = LocalDate.parse(dateEmbauche, formatter);
                date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }

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

    private boolean displayAllProjets()
    {
        try {
            List<Projet> projetList = getAllProjets();

            if (projetList == null || projetList.isEmpty())
            {
                System.out.println("Aucun projet trouvé.");
                return true;
            }

            for (Projet projet : projetList)
            {
                System.out.println(projet.toString());
            }

            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des projets : " + e.getMessage());
            return false;
        }
    }

    private boolean displayUniqueProjet(int id)
    {
        try {
            Projet projet = actionsBDD.getProjetById(id);
            if (projet == null) {
                System.out.println("Aucun projet trouvé avec l'id : " + id);
                return false;
            }

            System.out.println(projet);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean displayListEmployeOfUniqueProjet(int id)
    {
        try {
            Projet projet = actionsBDD.getProjetById(id);
            if (projet == null) {
                System.out.println("Aucun projet trouvé avec l'id : " + id);
                return false;
            }

            for (Employe employe : projet.getProgrammeurs()) System.out.println(employe);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<Projet> getAllProjets()
    {
        try {
            return actionsBDD.getAllProjets();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws Exception {
        Scanner clavier = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println(
                    """
                            ********* MENU *********
                            1. Afficher tous les programmeurs
                            2. Afficher un programmeur
                            3. Supprimer un programmeur
                            4. Ajouter un programmeur
                            5. Modifier le salaire
                            6. Afficher la liste des projets
                            7. Obtenir la liste des programmeurs qui travaillent sur le même projet
                            8. Quitter le programme
                            
                            Quel est votre choix ?\s"""
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
                    clavier.nextLine();
                    if (!displayUniqueEmploye(id2)) System.out.println("Recherche KO !\n");;
                    break;

                case 3:
                    System.out.print("Saisissez l'id de l'employé à supprimer : ");
                    int id3 = clavier.nextInt();
                    clavier.nextLine();
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
                    int cnt = 4; // Limite d'essais
                    int id5 = -1;

                    while (cnt >= 0) {
                        int tentative = cnt + 1;
                        System.out.print("Saisissez l'id de l'employé (" + tentative + " tentative restante) : ");
                        id5 = clavier.nextInt();
                        clavier.nextLine();

                        if (displayUniqueEmploye(id5))
                        {
                            break;
                        };
                        cnt--;
                    }

                    if (cnt == -1)
                    {
                        System.out.println("Limite atteinte !");
                        break;
                    }

                    System.out.print("Saisissez le nouveau salaire de l'employé id " + id5 + " : ");
                    float salaireEmploye = clavier.nextFloat();
                    modifyEmployeSalary(id5, salaireEmploye);
                    clavier.nextLine();
                    break;

                case 6:
                    if (!displayAllProjets()) System.out.println("Recherche KO !\n");;
                    break;

                case 7:
                    int cnt2 = 4; // Limite de 5 essais
                    int id6 = -1;

                    while (cnt2 >= 0) {
                        int tentative = cnt2 + 1;
                        System.out.print("Saisissez l'id du projet (" + tentative + " tentative restante) : ");
                        id6 = clavier.nextInt();
                        clavier.nextLine();

                        if (displayListEmployeOfUniqueProjet(id6))
                        {
                            break;
                        };
                        cnt2--;
                    }

                    if (cnt2 == -1)
                    {
                        System.out.println("Limite atteinte !");
                        break;
                    }
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
