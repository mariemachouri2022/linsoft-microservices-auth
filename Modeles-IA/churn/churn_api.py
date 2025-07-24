from flask import Flask, request, jsonify
from churn_model import ChurnModel

app = Flask(__name__)
model = ChurnModel()

@app.route('/churn', methods=['POST'])
def predict_churn():
    """
    Endpoint reçoit un JSON :
    {
        "usage_count": 4,
        "last_used_days": 20
    }
    Retourne :
    {
        "churn_probability": 0.35
    }
    """
    data = request.get_json()
    usage_count = data.get("usage_count", 0)
    last_used_days = data.get("last_used_days", 0)

    prob = model.predict(usage_count, last_used_days)

    return jsonify({
        "churn_probability": round(prob, 2)
    })

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5001)  # ⚠️ port 5001 pour ne pas écraser sentiment_api
