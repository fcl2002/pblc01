-- CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- CREATE TABLE cotacao (
--     id UUID DEFAULT gen_ramdon_uuid() PRIMARY KEY,
--     price FLOAT(2,2) NOT NULL,
--     date TIMESTAMP NOT NULL,

--     stock_id UUID,
--     FOREIGN KEY (stock_id) REFERENCES ativo(id)
-- );