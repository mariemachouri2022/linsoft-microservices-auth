class RecommendationModel:
    """
    Modèle ML simulé pour recommander des services
    Basé sur une logique simple (content-based filtering mocké)
    """

    # Un petit catalogue de services fictifs
    SERVICE_CATALOG = {
        "Service A": ["Service B", "Service C"],
        "Service B": ["Service A", "Service D"],
        "Service C": ["Service A", "Service E"],
        "Service D": ["Service B", "Service F"],
        "Service E": ["Service C", "Service G"]
    }

    def recommend(self, history):
        """
        history : liste des services déjà utilisés par l'utilisateur
        Retourne une liste de recommandations
        """
        recommendations = set()

        for used_service in history:
            if used_service in self.SERVICE_CATALOG:
                # ajoute les services similaires
                recommendations.update(self.SERVICE_CATALOG[used_service])

        # Retirer les services déjà utilisés
        recommendations -= set(history)

        # Limiter à 3 recommandations
        return list(recommendations)[:3]
