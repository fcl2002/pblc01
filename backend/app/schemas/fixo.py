from typing import List
from ativo import Ativo
from pydantic import BaseModel

class Fixo(Ativo):
    rentabilidade: float
    periodo: int
    isencao_IR: bool
    valor_face: int

    class Config:
        orm_mode = True