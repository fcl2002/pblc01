import usuario as Usuario
from typing import List, Optional
from cotacao import Cotacao
from enum_risco import EnumRisco
from pydantic import BaseModel

class Notificacao(BaseModel):
    mensagem: Optional[str] = None
    tipo: Optional[str] = None
    cotacao: Optional[Cotacao] = None
    usuario_id: Optional[Usuario] = None