CREATE TABLE programmeur (
                             id_programmeur SERIAL PRIMARY KEY,
                             nom           VARCHAR(100) NOT NULL,
                             prenom        VARCHAR(100) NOT NULL,
                             anNaissance   INT NOT NULL,
                             salaire       NUMERIC(10,2) NOT NULL,
                             prime         NUMERIC(10,2) DEFAULT 0,
                             email         VARCHAR(255),
                             date_embauche DATE,
                             actif         BOOLEAN DEFAULT TRUE
);

CREATE TABLE projet (
                        id_projet        SERIAL PRIMARY KEY,
                        intitule         VARCHAR(200) NOT NULL,
                        date_debut       DATE NOT NULL,
                        date_fin_prevue  DATE,
                        etat             VARCHAR(50) NOT NULL
);

CREATE TABLE programmeur_projet (
                                    id_programmeur INT NOT NULL,
                                    id_projet      INT NOT NULL,
                                    PRIMARY KEY (id_programmeur, id_projet),
                                    CONSTRAINT fk_prog FOREIGN KEY (id_programmeur)
                                        REFERENCES programmeur(id_programmeur)
                                        ON DELETE CASCADE,
                                    CONSTRAINT fk_proj FOREIGN KEY (id_projet)
                                        REFERENCES projet(id_projet)
                                        ON DELETE CASCADE
);

INSERT INTO programmeur (nom, prenom, anNaissance, salaire, prime, email, date_embauche, actif) VALUES
                                                                                                    ('Dupont',  'Jean',   1995, 32000.00, 1500.00, 'jean.dupont@example.com',  '2022-01-10', TRUE),
                                                                                                    ('Martin',  'Claire', 1990, 38000.00, 2000.00, 'claire.martin@example.com','2021-03-01', TRUE),
                                                                                                    ('Durand',  'Paul',   1988, 29000.00,  800.00, 'paul.durand@example.com',  '2023-09-15', TRUE);

INSERT INTO projet (intitule, date_debut, date_fin_prevue, etat) VALUES
                                                                     ('Application interne RH',          '2024-01-15', '2024-06-30', 'EN_COURS'),
                                                                     ('Refonte site e-commerce',        '2023-09-01', '2024-03-31', 'EN_RETARD'),
                                                                     ('Outil reporting BI',             '2023-02-01', '2023-12-31', 'TERMINE');

INSERT INTO programmeur_projet (id_programmeur, id_projet) VALUES
                                                               (1, 1),
                                                               (1, 2),
                                                               (2, 2),
                                                               (2, 3),
                                                               (3, 1);
