DROP TABLE IF EXISTS employe_projet CASCADE;
DROP TABLE IF EXISTS projet CASCADE;
DROP TABLE IF EXISTS employe CASCADE;

-- Table EMPLOYE
-- id_employe auto-incrémenté
-- code_metier : 'P' = Programmeur, 'CP' = Chef de projet
CREATE TABLE employe (
                         id_employe     INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nom            VARCHAR(100) NOT NULL,
                         prenom         VARCHAR(100) NOT NULL,
                         anNaissance    INT NOT NULL,
                         salaire        NUMERIC(10,2) NOT NULL,
                         prime          NUMERIC(10,2) DEFAULT 0,
                         email          VARCHAR(255),
                         date_embauche  DATE,
                         adresse        VARCHAR(150),
                         actif          BOOLEAN DEFAULT TRUE,
                         code_metier    VARCHAR(5) NOT NULL,
                         responsable    INT NULL,
                         CONSTRAINT chk_code_metier CHECK (code_metier IN ('P', 'CP')),
                         CONSTRAINT fk_responsable FOREIGN KEY (responsable)
                             REFERENCES employe(id_employe)
                             ON DELETE SET NULL
);

-- Table PROJET
-- id_projet auto-incrémenté
CREATE TABLE projet (
                        id_projet        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        intitule         VARCHAR(200) NOT NULL,
                        date_debut       DATE NOT NULL,
                        date_fin_prevue  DATE,
                        etat             VARCHAR(50) NOT NULL
);

-- Table de liaison EMPLOYE_PROJET
CREATE TABLE employe_projet (
                                id_employe INT NOT NULL,
                                id_projet  INT NOT NULL,
                                PRIMARY KEY (id_employe, id_projet),
                                CONSTRAINT fk_employe FOREIGN KEY (id_employe)
                                    REFERENCES employe(id_employe)
                                    ON DELETE CASCADE,
                                CONSTRAINT fk_projet FOREIGN KEY (id_projet)
                                    REFERENCES projet(id_projet)
                                    ON DELETE CASCADE
);

-- Données de test pour EMPLOYE
INSERT INTO employe (nom, prenom, anNaissance, salaire, prime, email, date_embauche, actif, code_metier, adresse, responsable) VALUES
                                                                                                             ('Dupont',  'Jean',   1995, 32000.00, 1500.00, 'jean.dupont@example.com',   '2022-01-10', TRUE, 'P', '5 rue des Perrins', null),
                                                                                                             ('Martin',  'Claire', 1990, 38000.00, 2000.00, 'claire.martin@example.com', '2021-03-01', TRUE, 'CP', 'test rue', 1),
                                                                                                             ('Durand',  'Paul',   1988, 29000.00,  800.00, 'paul.durand@example.com',   '2023-09-15', TRUE, 'P', '45 rue de Saintonge', 1);

-- Données de test pour PROJET
INSERT INTO projet (intitule, date_debut, date_fin_prevue, etat) VALUES
                                                                     ('Application interne RH',          '2024-01-15', '2024-06-30', 'EN_COURS'),
                                                                     ('Refonte site e-commerce',        '2023-09-01', '2024-03-31', 'EN_RETARD'),
                                                                     ('Outil reporting BI',             '2023-02-01', '2023-12-31', 'TERMINE');

-- Liaison EMPLOYE_PROJET
INSERT INTO employe_projet (id_employe, id_projet) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 2),
                                                       (2, 3),
                                                       (3, 1);
