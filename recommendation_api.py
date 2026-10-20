from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import numpy as np
import tensorflow as tf

# Define the expected incoming JSON format
class MoodInput(BaseModel):
    valence: float
    arousal: float

app = FastAPI(title="Music ML Inference Service")

# Global variable for our model
model = None

@app.on_event("startup")
def load_ml_model():
    global model
    try:
        # Load the Keras model saved from Google Colab
        model = tf.keras.models.load_model('complete_music_model.keras')
        print("Model loaded successfully!")
    except Exception as e:
        print(f"Error loading model: {e}")

@app.post("/predict")
def predict_preference(request: MoodInput):
    if model is None:
        raise HTTPException(status_code=503, detail="Model is not loaded.")

    # 1. Convert the incoming JSON into a 2D NumPy array the model expects
    input_features = np.array([[request.valence, request.arousal]])
    
    # 2. Run the math (Inference)
    prediction = model.predict(input_features)
    
    # 3. Extract the probability score (a float between 0.0 and 1.0)
    like_probability = float(prediction[0][0])
    
    # 4. Return a clean JSON response
    return {
        "valence_input": request.valence,
        "arousal_input": request.arousal,
        "probability_score": round(like_probability, 4),
        "will_like": like_probability > 0.5
    }