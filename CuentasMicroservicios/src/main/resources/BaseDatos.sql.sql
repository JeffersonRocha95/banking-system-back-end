CREATE TABLE IF NOT EXISTS cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(50) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT NOT NULL
    );

CREATE TABLE IF NOT EXISTS movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor DOUBLE NOT NULL,
    saldo DOUBLE NOT NULL,
    cuenta_id BIGINT NOT NULL
    );