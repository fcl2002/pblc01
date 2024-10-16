import sentry_sdk
from fastapi import FastAPI
from fastapi.routing import APIRoute # personalizar geraçõa de IDs únicos para asrotas da API
from starlette.middleware.cors import CORSMiddleware

from app.api.main import api_router
from app.core.config import settings

app = FastAPI()

@app.get("/")
def home():
    return "Backend do Sistema"

