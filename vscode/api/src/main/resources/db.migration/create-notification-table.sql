-- CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- CREATE TABLE notificacao (
--     id UUID DEFAULT gen_ramdon_uuid() PRIMARY KEY,
--     notification_message VARCHAR(255) NOT NULL,
--     message_type VARCHAR(50) NOT NULL,

--     user_id UUID,
--     FOREIGN KEY (uder_id) REFERENCES usuario(id), 
--     stock_price_id UUID,
--     FOREIGN KEY (stock_price_id) REFERENCES cotacao(id)
-- );