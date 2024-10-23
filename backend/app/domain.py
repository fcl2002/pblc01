from datetime import date
from sqlmodel import Field
from sqlmodel import Relationship
from sqlmodel import SQLModel
from sqlmodel import Session

from sqlmodel import create_engine

from uuid import UUID

class Usuario(SQLModel, table=True):
    email : str | None = Field(max_length=255, default=None, primary_key=True)
    nome : str | None = Field(max_length=255)
    senha : str | None = Field(max_length=255)
    logado : bool | None
    perfilRisco : str | None = Field(max_length=255)

    carteiras : list['Carteira'] = Relationship(back_populates="usuario_carteira")
    notificacoes : list['Notificacao'] = Relationship(back_populates="usuario_notificacao")


class Notificacao(SQLModel, table=True):
    uuid: UUID = Field(primary_key=True)
    mensagem : str | None = Field(max_length=255)
    tipo : str | None = Field(max_length=255)

    cotacao : 'Cotacao' = Relationship(back_populates="notificacoes_cotacao")
    cotacao_uuid : UUID = Field(foreign_key="cotacao.uuid")
    usuario_notificacao : 'Usuario' = Relationship(back_populates="notificacoes")
    usuario_email : str = Field(max_length=255, default=None, foreign_key="usuario.email")


class Cotacao(SQLModel, table=True):
    uuid: UUID = Field(primary_key=True)
    preco : float | None
    data : date | None

    # ativo : 'Ativo' = Relationship(back_populates="cotacoes")
    # nome_ativo : str = Field(max_length=255, default=None, foreign_key="ativo.nome")
    notificacoes_cotacao : list['Notificacao'] = Relationship(back_populates="cotacao")


class Carteira(SQLModel, table=True):
    id: UUID = Field(primary_key=True)
    risco : float | None

    # ativos : list['Ativo'] = Relationship(back_populates="carteira")
    usuario_carteira : 'Usuario' = Relationship(back_populates="carteiras")
    email_usuario : str = Field(max_length=255, default=None, foreign_key="usuario.email")


class Ativo(SQLModel):
    nome : str | None = Field(max_length=255)
    valorInvestido : float | None
    numeroCotas : float | None
    precoAtual : float | None

    # carteira : 'Carteira' = Relationship(back_populates="ativos")
    # id_carteira : UUID = Field(foreign_key="carteira.id")
    # cotacoes : list['Cotacao'] = Relationship(back_populates="ativo")


class Variavel(Ativo, table=True):
    ticker : str | None = Field(max_length=255, default=None, primary_key=True)
    etf : bool | None

class Fixo(Ativo, table=True):
    id : UUID = Field(primary_key=True, default=None,)
    rentabilidade : float | None
    periodo : int | None
    isencaoIR : bool | None
    valorFace : int | None



usuario_bd='wealth'
senha_bd='wealth'
host_bd='localhost'
banco_bd='wealth'
url_bd = f"mariadb+pymysql://{usuario_bd}:{senha_bd}@{host_bd}:3306/{banco_bd}?charset=utf8mb4"
engine = create_engine(url_bd, echo=True)  

if __name__=="__main__":    
    SQLModel.metadata.create_all(engine) 
    with Session(engine) as se:
        u = Usuario(email="teste@teste.com")
        c = Carteira(id=UUID("123e4567-e89b-12d3-a456-426614174000"), usuario_carteira=u)
        u.carteiras.append(c)
        se.add(u)
        se.add(c)
        se.commit()