from pydantic import BaseModel
from typing import List
from app.schemas.ativo import Ativo

class VariavelBase(AtivoBase):
    ticker: str
    etf: bool

class Variavel(VariavelBase):
    class Config:
        orm_mode = True