from flask import Flask, request, jsonify
from recommendation_model import RecommendationModel

app = Flask(__name__)
model = RecommendationModel()

@app.route('/recommendations', methods=['POST'])
def recommend_services():
    """
    Endpoint reçoit un JSON :
    {
        "history": ["Service A", "Service B"]
    }
    Retourne :
    {
        "recommendations": ["Service C", "Service D"]
    }
    """
    data = request.get_json()
    history = data.get("history", [])

    recos = model.recommend(history)

    return jsonify({
        "recommendations": recos
    })

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5002)  # port 5002 pour ce service
