from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def home():
    return "Backend do Sistema"

