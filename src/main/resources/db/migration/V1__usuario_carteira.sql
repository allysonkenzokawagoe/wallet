CREATE SEQUENCE IF NOT EXISTS seq_usuario START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_carteira START WITH 1 INCREMENT BY 1;

CREATE TABLE usuario
(
    id            INTEGER      NOT NULL,
    nome          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    senha         VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE carteira
(
    id            INTEGER NOT NULL,
    saldo         DECIMAL,
    fk_usuario    INTEGER NOT NULL,
    data_cadastro TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT pk_carteira PRIMARY KEY (id)
);

ALTER TABLE carteira
    ADD CONSTRAINT uc_carteira_fk_usuario UNIQUE (fk_usuario);

ALTER TABLE carteira
    ADD CONSTRAINT FK_USUARIO FOREIGN KEY (fk_usuario) REFERENCES usuario (id);