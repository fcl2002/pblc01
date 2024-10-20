import uuid

from typing import List
from pydantic import EmailStr
from sqlmodel import Field, Relationship, SQLModel

# shared user properties
class UserBase(SQLModel):
    email: EmailStr = Field(unique=True, index=True, max_length=255)       # email é único
    is_active: bool = True                                                 # logado
    is_super: bool = False                                                 # usuário super
    full_name: str | None = Field(default=None, max_length=255)            # nome completo
    risk_profile: EnumRisco | None = Field(default=None, max_length=255)  # perfil de risco
    
# properties to receive via API on creation
class UserCreate(UserBase):
    password: str = Field(min_length=8, max_length=20)
    
class UserRegister(UserBase):
    email: EmailStr = Field(max_length=255)
    password: str = Field(min_length=8, max_length=20)
    full_name: str = Field(max_length=255)
    
# properties to receive via API on update, all are optional
class UserUpdate(UserBase):
    email: EmailStr | None = Field(default=None, max_length=255)
    password: str = Field(min_length=8, max_length=20)
    risk_profile: EnumRisco | None = Field(default=None)
    
class UserUpdateMe(SQLModel):
    full_name: str | None = Field(default=None, max_length=255)
    risk_profile: EnumRisco | None = Field(default=None)
    
class UpdatePassword(SQLModel):
    current_password: str = Field(min_length=8, max_length=20)
    new_password: str = Field(min_length=8, max_length=20)
 
    
# shared wallet properties    
class WalletBase(SQLModel):
    risco: float = Field(default=0.0)
    
class WalletCreate(WalletBase):
    name: str | None = Field(default="My Wallet", max_length=40)
    
class WalletRegister(WalletBase):
    name: str = Field(max_length=40)
    
# properties to receive via API, all are optional
class WalletUpdate(WalletBase):
    name: str | None = Field(default="My Wallet", max_length=40)
    risco: float | None = Field(default=0.0)

class WalletUpdateMe(SQLModel):
    name: str | None = Field(default="My Wallet", max_length=40)


# Shared Assests properties
class AssetBase(SQLModel):
    name: str = Field(unique=True, max_length=255)
    ammount_invested: float | None = Field(default=0.0)
    number_of_quotas: float | None = Field(default=0.0)
    current_value: float | None = Field(default=0.0)
    
class AssetCreate(AssetBase):
    name: str = Field(max_length=255)
    ammount_invested: float = Field(default=0.0)
    number_of_quotas: float = Field(default=0.0)
    current_value: float = Field(default=0.0)
    
class AssetRegister(AssetBase):
    name: str = Field(max_length=255)
    ammount_invested: float = Field(default=0.0)
    number_of_quotas: float = Field(default=0.0)
    current_value: float = Field(default=0.0)
    
class AssetUpdate(AssetBase):
    name: str | None = Field(default=None, max_length=255)
    ammount_invested: float | None = Field(default=None)
    number_of_quotas: float | None = Field(default=None)
    current_value: float | None = Field(default=None)
    
class AssetUpdateMe(AssetBase):
    ammount_invested: float | None = Field(default=None)
    number_of_quotas: float | None = Field(default=None)
    current_value: float | None = Field(default=None)