-- Criação do banco de dados
create database if not exists wealth;

-- Seleção do banco de dados
use wealth;

-- Exclusão das tabelas existentes, se houver
drop table if exists ativo;
drop table if exists carteira;
drop table if exists cotacao;
drop table if exists fixo;
drop table if exists notificacao;
drop table if exists usuario;

create extension if not exists "pgcrypto";

-- Tabela de ativo
create table ativo (
    id UUID default gen_ramdon_uuid() primary key,
    asset_name varchar(100) not null,
    invested_value float(,2) not null,
    number_of_quotas integer not null,
    current_value float(,2) not null,

    wallet_id UUID,
    foreign key (wallet_id) references carteira(id),
    stock_price_id UUID,
    foreign key (stock_price_id) references cotacao(id)
);

-- Tabela de carteira
create table carteira (
    id UUID default gen_ramdon_uuid() primary key,
    risk float(2,2) not null,

    user_id UUID,
    foreign key (uder_id) references usuario(id),
    stock_id UUID,
    foreign key (stock_id) references ativo(id)
);

-- Tabela de cotação
create table cotacao (
    id UUID default gen_ramdon_uuid() primary key,
    price float(2,2) not null,
    date TIMESTAMP not null,

    stock_id UUID,
    foreign key (stock_id) references ativo(id)
);

-- Tabela de ativo fixo
create table fixo (
    id UUID default gen_ramdon_uuid() primary key,
    rentabilidade float (,2) not null,
    periodo integer not null,
    isencaoIR boolean not null,
    valorFace integer not null,
);

-- Tabela de ativo variável
create table variavel (
    id UUID default gen_ramdon_uuid() primary key,
    ticker varchar(10) not null,
    etf boolean not null,
);

-- Tabela de notificação
create table notificacao (
    id UUID default gen_ramdon_uuid() primary key,
    notification_message varchar(255) not null,
    message_type varchar(50) not null,

    user_id UUID,
    foreign key (uder_id) references usuario(id), 
    stock_price_id UUID,
    foreign key (stock_price_id) references cotacao(id)
);

-- Tabela de usuário
create table usuario (
    id UUID default gen_ramdon_uuid() primary key,
    email varchar(100) not null,
    username varchar(100) not null,
    userpass varchar(20) not null,
    is_active boolean not null default TRUE,
    is_super boolan not null default FALSE,
    risk_profile varchar(30) not null,

    notification_id UUID,
    foreign key (notification_id) references notificacao(id),
    wallet_id UUID,
    foreign key (wallet_id) references carteira(id)
);