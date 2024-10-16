from pydantic import BaseModel
from typing import List
from app.schemas.ativo import Ativo

class FixoBase(AtivoBase):
    rentabilidade: float
    periodo: int
    isencao_IR: bool
    valor_face: int
    
class Fixo(FixoBase):
    class Config:
        orm_mode = True