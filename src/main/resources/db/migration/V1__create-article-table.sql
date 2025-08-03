CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE article(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    tags VARCHAR(240),
    published_at TIMESTAMP
);