from pydantic import BaseModel

class EnumRisco(BaseModel):
    conservador: bool = False
    moderado: bool = False
    agressivo: bool = False