# LinSoft - Microservices Authentication, Authorization & ML Consulting Platform

Ce projet implémente une **architecture microservices sécurisée** avec **Keycloak** et **LDAP**, orchestrée via **Docker Compose**. Il intègre également un microservice **Consulting** dédié aux traitements **Machine Learning**, ainsi qu’une **interface Frontend** de gestion des utilisateurs.

---

## 📦 Microservices inclus

- ✅ **Eureka Server** – Service Discovery
- ✅ **API Gateway (Spring Cloud Gateway)** – Reverse Proxy & Sécurisation des routes
- ✅ **User-Service** – Gestion des utilisateurs (Spring Boot + MongoDB)
- ✅ **Consulting Service** – Backend Spring Boot exposant des API ML
- ✅ **ML Microservices Flask** :
   - Analyse de sentiment (`/sentiment`)
   - Prédiction du churn (`/churn`)
   - Recommandation de services (`/recommendations`)
- ✅ **Keycloak** – Identity Provider (OpenID Connect, OAuth2, LDAP)
- ✅ **OpenLDAP + phpLDAPadmin** – Gestion d’annuaire LDAP
- ✅ **MySQL** – Base de données pour Keycloak et User-Service
- ✅ **Frontend - User Management** – Interface Angular pour la gestion des utilisateurs

---

## ⚙️ Technologies utilisées

- **Spring Boot 3** / **WebFlux**
- **Spring Cloud Gateway**
- **Spring Security** (JWT, OAuth2 Resource Server)
- **Docker** & **Docker Compose**
- **Keycloak 23**
- **LDAP** (OpenLDAP + phpLDAPadmin)
- **Python Flask** (microservices ML)
- **MongoDB** (User-Service)
- **MySQL** (Keycloak DB)
- **Angular** (Interface frontend)

---

## 🚀 Lancement rapide

### 1. Cloner le projet

```bash
git clone https://github.com/mariemachouri2022/linsoft-microservices-auth.git
cd linsoft-microservices-auth
```
2. Lancer les conteneurs principaux
docker compose up -d --build
3. Lancer les microservices Flask localement
À exécuter manuellement (via PyCharm ou terminal) :
python3 sentiment_api.py       # Port 5000
python3 churn_api.py           # Port 5001
python3 recommendation_api.py  # Port 5002

4. Lancer le frontend Angular:
cd Front
npm install
ng serve --port 4200

 Points d’accès:
| Service            | URL                                                                          |
| ------------------ | ---------------------------------------------------------------------------- |
| Eureka Server      | [http://localhost:8761](http://localhost:8761)                               |
| API Gateway        | [http://localhost:8058](http://localhost:8058)                               |
| Keycloak           | [http://localhost:9098](http://localhost:9098)                               |
| phpLDAPadmin       | [http://localhost:8090](http://localhost:8090)                               |
| User-Service API   | [http://localhost:8023/users](http://localhost:8023/users)                   |
| Consulting API     | [http://localhost:8082/api/consulting](http://localhost:8082/api/consulting) |
| Frontend (Angular) | [http://localhost:4200](http://localhost:4200)                               |

BESOINS MÉTIERS - Microservice Consulting:
🔹 Analyse de sentiment
POST /api/consulting/sentiment

Envoi d’un texte → Retour du sentiment (POSITIVE, NEGATIVE, NEUTRAL) + score

Délégation à sentiment_api.py

🔹 Prédiction du churn
POST /api/consulting/churn

Envoi : usage_count, last_used_days → Retour : probabilité de churn

Délégation à churn_api.py

🔹 Recommandation de services
POST /api/consulting/recommendations

Envoi : historique des services → Retour : recommandations

Délégation à recommendation_api.py

🧪 Tests avec Postman
Tester les microservices ML directement via leurs ports (5000, 5001, 5002)

Tester le backend consulting via :

l’API Gateway (8058)

ou directement sur le port 8082

🛡️ Sécurisation
Authentification centralisée via Keycloak

Accès aux API protégé par JWT

Synchronisation des utilisateurs via LDAP

Frontend connecté à Keycloak via OAuth2/OIDC

👩‍💻 Auteur
Maryem Achouri
Stage chez LinSoft
GitHub : @mariemachouri2022