-- Migration: Add listas table and lista_id FK to todos
use todogrupo2;

CREATE TABLE IF NOT EXISTS listas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    color VARCHAR(20) NOT NULL DEFAULT '#6C63FF',
    user_id BINARY(16) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_listas_user_id (user_id)
);

ALTER TABLE todos
    ADD COLUMN lista_id BINARY(16) NULL,
    ADD INDEX idx_todos_lista_id (lista_id);
