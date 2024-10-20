from typing import List
from pydantic import BaseModel
from .cotacao import Cotacao
from .carteira import Carteira

class Ativo(BaseModel):
    nome: str
    valor_investido: float
    numero_cotas: float
    preco_atual: float
    
    class Config:
        orm_mode = True

class AtivoDetalhes(Ativo):
    cotacoes: List[Cotacao] = []
    carteira: Carteira