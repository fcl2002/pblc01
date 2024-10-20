from .ativo import Ativo
from typing import List

class Variavel(Ativo):
    ticker: str
    etf: bool

    class Config:
        orm_mode = True