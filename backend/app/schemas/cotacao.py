import ativo as Ativo
from pydantic import BaseModel
from datetime import datetime
from typing import Optional

class Cotacao(BaseModel):
    preco: Optional[float] = None
    date: Optional[datetime] = None
    ativo: Optional[Ativo] = None