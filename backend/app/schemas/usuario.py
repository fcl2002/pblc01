import carteira as Carteira
import notificacao as Notificacao
from enum_risco import EnumRisco
from pydantic import BaseModel

class Usuario(BaseModel):
    email: Optional[str] = None
    nome: Optional[str] = None
    senha: Optional[str] = None
    logado: Optional[bool] = None
    perfil_risco: Optional[EnumRisco] = None
    carteiras: List[Carteira] = []
    notificacoes: List[Notificacao] = []
