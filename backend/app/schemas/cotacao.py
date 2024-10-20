from uuid import UUID
from typing import List
from datetime import datetime
from pydantic import BaseModel
from .ativo import Ativo


class Cotacao(BaseModel):
    price: float
    date: datetime
    
    class Config:
        orm_mode = True
    
class CotacaoDetalhes(Cotacao):
    ativo: Ativo