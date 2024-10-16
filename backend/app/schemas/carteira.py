from pydantic import BaseModel
from typing import List
from app.schemas.ativo import Ativo

class CarteiraBase(BaseModel):
    id: str
    risco: float
    
class CarteiraoCreate(CarteiraBase):
    usuario: Usuario

class Carteira(CarteiraBase):
    ativos: List[Ativo] = []
    class Config:
        orm_mode = True
