-- CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- CREATE TABLE carteira (
--     id UUID DEFAULT gen_ramdon_uuid() PRIMARY KEY,
--     risk FLOAT(2,2) NOT NULL,

--     user_id UUID,
--     FOREIGN KEY (uder_id) REFERENCES usuario(id),
--     stock_id UUID,
--     FOREIGN KEY (stock_id) REFERENCES ativo(id)
-- );