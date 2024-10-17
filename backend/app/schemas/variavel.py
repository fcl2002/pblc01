from ativo import Ativo
from typing import List, Optional

class Variavel(Ativo):
    super().__init__()
    ticker: Optional[str] = None
    etf: Optional[bool] = None