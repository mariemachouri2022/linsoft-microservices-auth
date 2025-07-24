class SentimentModel:
    """
    Modèle ML simulé pour analyse de sentiment
    (ici simple logique de mots-clés, mais on pourra remplacer par un vrai modèle NLP plus tard)
    """

    def predict(self, text: str):
        text_lower = text.lower()

        # Logique ultra simple pour démo
        if "ador" in text_lower or "incroyable" in text_lower or "super" in text_lower:
            return "POSITIVE", 0.9
        elif "mauvais" in text_lower or "nul" in text_lower or "horrible" in text_lower:
            return "NEGATIVE", 0.1
        else:
            return "NEUTRAL", 0.5
