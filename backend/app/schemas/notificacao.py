from uuid import UUID
from typing import List
from pydantic import BaseModel
from .usuario import Usuario
from .cotacao import Cotacao

class Notificacao(BaseModel):
    uuid: UUID
    message: str
    type: str
    
    class Config:
        orm_mode = True
    
class NotificacaoDetalhes(Notificacao):
    cotacao: Cotacao
    usuario_id: Usuario