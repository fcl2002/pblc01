from datetime import datetime
from pydantic import BaseModel
from typing import List
from app.schemas.enum_risco import PerfilRiscoEnum

class CotacaoBase(BaseModel):
    preco: float
    date: datetime
    
class CotacaoCreate(CotacaoBase):
    ativo: Ativo
    
class Cotacao(CotacaoBase):
    class Config:
        orm_mode = True