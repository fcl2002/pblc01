-- Criação do banco de dados
create database if not exists wealthwise;

-- Seleção do banco de dados
use wealthwise;

set foreign_key_checks = 0;

-- Exclusão das tabelas existentes, se houver
drop table if exists ativo_cotacoes;
drop table if exists carteira_ativos;
drop table if exists usuario_carteiras;
drop table if exists usuario_notificacoes;

drop table if exists ativo;
drop table if exists carteira;
drop table if exists cotacao;
drop table if exists usuario;
drop table if exists notificacao;

set foreign_key_checks = 1;

-- -- Tabela de ativo
-- create table ativo (
--     id integer default auto_increment primary key,
--     name varchar(100) not null,
--     invested_value float(,2) not null,
--     number_of_quotas integer not null,
--     current_value float(,2) not null

    -- wallet_id UUID,
    -- foreign key (wallet_id) references carteira(id),
    -- stock_price_id UUID,
    -- foreign key (stock_price_id) references cotacao(id)
-- );

-- -- Tabela de carteira
-- create table carteira (
--     id UUID default gen_ramdon_uuid() primary key,
--     risk float(2,2) not null,

--     user_id UUID,
--     foreign key (uder_id) references usuario(id),
--     stock_id UUID,
--     foreign key (stock_id) references ativo(id)
-- );

-- -- Tabela de cotação
-- create table cotacao (
--     id UUID default gen_ramdon_uuid() primary key,
--     price float(2,2) not null,
--     date TIMESTAMP not null,

--     stock_id UUID,
--     foreign key (stock_id) references ativo(id)
-- );

-- -- Tabela de ativo fixo
-- create table fixo (
--     profitability float (,2) not null,
--     period integer not null,
--     is_taxable boolean not null,
--     face_value integer not null
-- );

-- -- Tabela de ativo variável
-- create table variavel (
--     ticker varchar(10) not null,
--     etf boolean not null
-- );

-- -- Tabela de notificação
-- create table notificacao (
--     id UUID default gen_ramdon_uuid() primary key,
--     notification_message varchar(255) not null,
--     message_type varchar(50) not null,

--     user_id UUID,
--     foreign key (uder_id) references usuario(id), 
--     stock_price_id UUID,
--     foreign key (stock_price_id) references cotacao(id)
-- );

-- -- Tabela de usuário
-- create table usuario (
--     username varchar(50) primary key,
--     email varchar(100) not null,
--     userpass varchar(20) not null,
--     is_active boolean not null default true,
--     is_super boolean not null default false,
--     risk_profile varchar(30) not null,

--     notification_id UUID,
--     foreign key (notification_id) references notificacao(id),
--     wallet_id UUID,
--     foreign key (wallet_id) references carteira(id)
-- );