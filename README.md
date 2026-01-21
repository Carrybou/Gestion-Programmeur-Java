# Projet de gestion des employés et des projets

## a. Instructions pour lancer l’application

⚠️ Aucune configuration complexe n’est nécessaire pour lancer le projet.

### Prérequis
- **Java JDK 21**
- **Maven**
- **PostgreSQL**
- Un IDE Java (IntelliJ IDEA recommandé)

### Étapes de lancement
1. Créer une base de données PostgreSQL
2. Exécuter le script SQL fourni (`schema.sql`) afin de créer les tables et insérer les données de test
3. Vérifier les paramètres de connexion dans le fichier :
```
src/main/java/org/example/config/DatabaseManager.java
````
(URL, utilisateur, mot de passe)
4. Compiler et lancer le projet :
```bash
mvn clean install
mvn exec:java
````

ou lancer directement la classe principale depuis l’IDE

👉 L’application démarre en **ligne de commande (CLI)** avec un menu interactif.

---

## b. Choix du SGBD

Le **SGBD utilisé est PostgreSQL**.

### Justification

* SGBD relationnel robuste et largement utilisé
* Excellente gestion des clés étrangères et des contraintes d’intégrité
* Support complet des types nécessaires (`DATE`, `NUMERIC`, `BOOLEAN`)
* Modélisation relationnelle claire et adaptée au projet

---

## c. Fonctionnalités non implémentées

### Fonctionnalités non réalisées

* Authentification des utilisateurs
* Gestion avancée des droits et rôles
* Interface graphique (GUI)
* Historique des modifications (audit)

### Raisons

* Contraintes de temps
* Priorité donnée à la logique métier et au modèle de données
* Choix assumé d’une application **CLI** simple, fonctionnelle et robuste

---

## d. Fonctionnalités supplémentaires ajoutées

En complément du cahier des charges initial, les fonctionnalités suivantes ont été mises en place :

* Relation **many-to-many** entre employés et projets via la table `employe_projet`
* Récupération :

    * des projets associés à un employé
    * des employés affectés à un projet
* Gestion hiérarchique des employés (responsable)
* Différenciation des métiers (`PROGRAMMEUR`, `CHEF_DE_PROJET`)
* Prévention des problèmes de récursivité grâce à des mappings *light* et *complets*
* Gestion des valeurs `null` (dates, responsables, chefs de projet)
* Menu CLI avec contrôles d’erreurs utilisateur

---

## Remarque finale

Ce projet a été conçu avec une attention particulière portée à :

* la cohérence du modèle relationnel
* la lisibilité et la maintenabilité du code
* la séparation claire des responsabilités (DAO, modèles, interface CLI)

```