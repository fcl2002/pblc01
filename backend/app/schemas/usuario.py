from pydantic import BaseModel
from typing import List
from app.schemas.enum_risco import PerfilRiscoEnum
from enumRisco import enumRisco

class UsuarioBase(BaseModel):
    email: str
    nome: str
    perfil_risco: enumRisco
    senha: str

class Usuario(UsuarioBase):
    logado: bool
    class Config:
        orm_mode = True
