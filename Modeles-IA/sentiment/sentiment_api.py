from flask import Flask, request, jsonify
from ml_model import SentimentModel  # on importe le modèle

# Initialiser Flask et le modèle ML
app = Flask(__name__)
model = SentimentModel()

@app.route('/sentiment', methods=['POST'])
def sentiment():
    """
    Endpoint qui reçoit un JSON : {"text": "..." }
    et retourne : {"sentiment": "...", "score": 0.xx }
    """
    data = request.get_json()
    text = data.get("text", "")

    sentiment, score = model.predict(text)

    return jsonify({
        "sentiment": sentiment,
        "score": score
    })

if __name__ == '__main__':
    # Lancer le serveur Flask sur le port 5000
    app.run(host="0.0.0.0", port=5000)
