class ChurnModel:
    """
    Modèle ML simulé pour prédire la probabilité qu'un utilisateur quitte (churn)
    """

    def predict(self, usage_count: int, last_used_days: int):
        """
        usage_count : nombre de services utilisés
        last_used_days : nombre de jours depuis la dernière utilisation

        Retourne une probabilité de churn entre 0 et 1
        """
        # Logique ultra simplifiée :
        # - Si utilisateur actif récemment => faible churn
        # - Si pas utilisé depuis longtemps => fort churn
        # - Moins d'usage => plus de risque
        base_risk = 0.2

        # Plus il a utilisé de services, moins il a de risque
        if usage_count < 3:
            base_risk += 0.3
        elif usage_count < 5:
            base_risk += 0.15

        # Si dernière utilisation date de plus de 30 jours, augmente le risque
        if last_used_days > 30:
            base_risk += 0.4
        elif last_used_days > 10:
            base_risk += 0.2

        # Clamp entre 0 et 1
        return min(1.0, max(0.0, base_risk))
