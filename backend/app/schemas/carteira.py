from typing import List, Optional
from pydantic import BaseModel
import ativo as Ativo
import usuario as Usuario

class Carteira(BaseModel):
    id: int = None
    risco: Optional[float] = None
    usuario: Usuario
    ativos: List[Ativo] = []
        
    # for ativo in self.ativos:
    #     self.risco += ativo.risco