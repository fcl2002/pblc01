CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE usuario (
    id UUID DEFAULT gen_ramdon_uuid() PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    userpass VARCHAR(20) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_super BOOLEAN NOT NULL DEFAULT FALSE,
    risk_profile VARCHAR(30) NOT NULL,

    notification_id UUID,
    FOREIGN KEY (notification_id) REFERENCES notificacao(id),
    wallet_id UUID,
    FOREIGN KEY (wallet_id) REFERENCES carteira(id)
);