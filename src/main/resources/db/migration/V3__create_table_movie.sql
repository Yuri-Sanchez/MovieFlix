CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    rating NUMERIC,
    created_at TIMESTAMP,
    update_at TIMESTAMP
);