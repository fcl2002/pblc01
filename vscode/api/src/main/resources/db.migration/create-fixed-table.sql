CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE ativo (
    id UUID DEFAULT gen_ramdon_uuid() PRIMARY KEY,
    asset_name VARCHAR(100) NOT NULL,
    invested_value FLOAT(,2) NOT NULL,
    number_of_quotas INTEGER NOT NULL,
    current_value FLOAT(,2) NOT NULL,


    wallet_id UUID,
    FOREIGN KEY (wallet_id) REFERENCES carteira(id),
    stock_price_id UUID,
    FOREIGN KEY (stock_price_id) REFERENCES cotacao(id)
);