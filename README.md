LinSoft - Microservices Authentication & Authorization & Consulting ML
Ce projet implémente une architecture microservices sécurisée avec Keycloak et LDAP, orchestrée via Docker Compose.
Il intègre également un microservice Consulting dédié aux traitements Machine Learning via des microservices Flask dédiés.

📦 Microservices inclus
✅ Eureka Server – Service Discovery
✅ API Gateway (Spring Cloud Gateway) – Reverse proxy & sécurisation des routes
✅ User-Service – Gestion des utilisateurs (Spring Boot + MongoDB)
✅ Consulting Service – Backend Spring Boot exposant des API ML
✅ ML Microservices Flask – Trois services ML déployés en Flask :

Analyse de sentiment (/sentiment)

Prédiction du churn (/churn)

Recommandation de services (/recommendations)
✅ Keycloak – Identity Provider (OpenID Connect, OAuth2, LDAP)
✅ OpenLDAP + phpLDAPadmin – Gestion d’annuaire LDAP
✅ MySQL – Base relationnelle pour Keycloak et User-Service

⚙️ Technologies
Spring Boot 3 / WebFlux

Spring Cloud Gateway

Spring Security (Resource Server)

Docker & Docker Compose

Keycloak 23

LDAP (OpenLDAP + phpLDAPadmin)

Python Flask pour microservices ML

MongoDB pour User-Service

🚀 Lancement rapide
Cloner le projet :

bash
Copier
Modifier
git clone https://github.com/mariemachouri2022/linsoft-microservices-auth.git
Lancer les conteneurs backend et services principaux :

bash
Copier
Modifier
docker compose up -d --build
Lancer localement les microservices Flask (dans PyCharm ou via terminal) :

sentiment_api.py (port 5000)

churn_api.py (port 5001)

recommendation_api.py (port 5002)

🔍 Points d’accès
Service	URL
Eureka Server	http://localhost:8761
API Gateway	http://localhost:8058
Keycloak	http://localhost:9098
phpLDAPadmin	http://localhost:8090
User-Service API	http://localhost:8023/users
Consulting API	http://localhost:8082/api/consulting

🤖 BESOINS MÉTIERS du microservice Consulting
1. Analyse de sentiment
   POST /api/consulting/sentiment

Envoi d’un texte, retour du sentiment (POSITIVE, NEGATIVE, NEUTRAL) et score.

Délégation à sentiment_api.py Flask.

2. Prédiction du churn
   POST /api/consulting/churn

Envoi de statistiques d’usage (usage_count, last_used_days), retour probabilité de churn.

Délégation à churn_api.py Flask.

3. Recommandation de services
   POST /api/consulting/recommendations

Envoi de l’historique des services utilisés, retour d’une liste de recommandations.

Délégation à recommendation_api.py Flask.

🧪 Tests avec Postman
Tester les endpoints Flask directement sur leurs ports (5000, 5001, 5002)

Tester les endpoints Consulting via API Gateway ou direct sur 8082

🛡️ Sécurisation
Authentification gérée par Keycloak

Les API nécessitent un token JWT valide pour accès

Synchronisation des utilisateurs via LDAP

📄 Auteur
Maryem Achouri – Stage chez LinSoft