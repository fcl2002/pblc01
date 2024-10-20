from uuid import UUID
from typing import List, Optional
from pydantic import BaseModel
from .carteira import Carteira
from .notificacao import Notificacao

class Usuario(BaseModel):
    uuid: UUID
    email: str
    username: str
    password: str
    is_active = True
    is_super = False
    risk_profile: Optional[str] | None = 'Conservador'
    
    class Config:
        orm_mode = True
    
class UsuarioDetalhes(Usuario):        
    carteiras: List[Carteira] = []
    notificacoes: List[Notificacao] = []
