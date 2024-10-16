from pydantic import BaseModel
from abc import ABC, abstractmethod

class AtivoBase(BaseModel, ABC):
    nome: str
    valor_investido: float
    numero_cotas: float
    preco_atual: float

class Ativo(AtivoBase):
    class Config:
        orm_mode = True
