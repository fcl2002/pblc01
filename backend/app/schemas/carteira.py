from uuid import UUID
from typing import List
from pydantic import BaseModel
from .ativo import Ativo
from .usuario import Usuario

class Carteira(BaseModel):
    uuid: UUID
    risk: float
    
class CarteiraDetalhes(Carteira):
    usuario: Usuario
    ativos: List[Ativo] = []