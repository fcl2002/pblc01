from typing import List, Optional
from pydantic import BaseModel
import cotacao as Cotacao
import carteira as Carteira

class Ativo(BaseModel):
    nome: Optional[str] = None
    valor_investido: float = None
    numero_cotas: float = None
    preco_atual: float = None
    cotacoes: List[Cotacao] = []
    carteira: Carteira = None