from uuid import UUID
from typing import List
from datetime import datetime
from pydantic import BaseModel
from .ativo import Ativo


class Cotacao(BaseModel):
    price: float
    date: datetime
    
class CotacaoDetalhes(Cotacao):
    ativo: Ativo