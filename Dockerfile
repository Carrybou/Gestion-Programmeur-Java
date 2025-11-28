# Utilise l'image officielle PostgreSQL
FROM postgres:17-alpine

# Variables d'environnement de base
ENV POSTGRES_USER=app_user
ENV POSTGRES_PASSWORD=app_password
ENV POSTGRES_DB=app_db

# (Optionnel) Script d'initialisation exécuté au premier démarrage
# Si tu as un fichier init.sql, mets-le dans le même dossier que le Dockerfile
COPY init.sql /docker-entrypoint-initdb.d/

# Port exposé par PostgreSQL
EXPOSE 5432
