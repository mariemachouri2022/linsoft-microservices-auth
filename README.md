# LinSoft - Microservices Authentication & Authorization

Ce projet implémente une architecture **microservices sécurisée avec Keycloak et LDAP**, orchestrée via **Docker Compose**.  
Il comprend plusieurs services :

✅ **Eureka Server** – Service Discovery  
✅ **API Gateway (Spring Cloud Gateway)** – Reverse proxy & sécurisation des routes  
✅ **User-Service** – Service de gestion des utilisateurs (Spring Boot + MongoDB)  
✅ **Keycloak** – Identity Provider (OpenID Connect, OAuth2, LDAP)  
✅ **OpenLDAP** + **phpLDAPadmin** – Gestion d’annuaire LDAP  
✅ **MySQL** – Base de données relationnelle pour Keycloak et User-Service

## ⚙️ Technologies

- **Spring Boot 3 / WebFlux**
- **Spring Cloud Gateway**
- **Spring Security (Resource Server)**
- **Docker & Docker Compose**
- **Keycloak 23**
- **LDAP (OpenLDAP + phpLDAPadmin)**

## 🚀 Lancement rapide

1. Cloner le projet :
   ```bash
   git clone https://github.com/mariemachouri2022/linsoft-microservices-auth.git
Lancer les conteneurs :

```bash
  docker compose up -d --build
  
Accéder aux services :

Eureka → http://localhost:8761

API Gateway → http://localhost:8058

Keycloak → http://localhost:9098

phpLDAPadmin → http://localhost:8090

🛡️ Sécurisation
Keycloak gère l’authentification des utilisateurs.

Les API derrière Gateway nécessitent un JWT valide.

Possibilité de synchroniser les utilisateurs via LDAP.

📄 Auteur
Maryem Achouri – Stage chez LinSoft

