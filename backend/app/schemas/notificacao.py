from pydantic import BaseModel
from typing import List
from app.schemas.enum_risco import PerfilRiscoEnum

class NotificacaoBase(BaseModel):
    mensagem: str
    tipo: str

class NotificacaoCreate(NotificacaoBase):
    usuario_id: int
    cotacao: Cotacao

class Notificacao(NotificacaoBase):
    logado: bool
    class Config:
        orm_mode = True
