from typing import List
from ativo import Ativo
from pydantic import BaseModel

class Fixo(Ativo):
    super().__init__()
    rentabilidade: float = None
    periodo: int = None
    isencao_IR: bool = None
    valor_face: int = None