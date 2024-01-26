-- Criação da tabela Client
CREATE TABLE IF NOT EXISTS client
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    login    VARCHAR(255)        NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL
);

-- Criação da tabela Hardware
CREATE TABLE IF NOT EXISTS hardware
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    model     VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    client_id BIGINT REFERENCES client (id)
);

-- Criação da tabela Event
CREATE TABLE IF NOT EXISTS event
(
    id          SERIAL PRIMARY KEY,
    hardware_id BIGINT REFERENCES hardware (id),
    message     VARCHAR(255) NOT NULL,
    date        DATE         NOT NULL,
    executed    BOOLEAN      NOT NULL,
    prefix      VARCHAR(255) NOT NULL,
    endpoint    VARCHAR(255) NOT NULL
);
