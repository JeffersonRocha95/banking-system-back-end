/*Creacion BD clientes*/
CREATE DATABASE IF NOT EXISTS pr_cliente;
USE pr_cliente;

/*creacion tabla cliente*/
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    cliente_id VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL
);

/*Creacion BD cuenta*/
CREATE DATABASE IF NOT EXISTS pr_cuenta;
USE pr_cuenta;

/*creacion tabla cuenta*/
CREATE TABLE IF NOT EXISTS cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(50) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT NOT NULL
);

/*creacion tabla movimiento*/
CREATE TABLE IF NOT EXISTS movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor DOUBLE NOT NULL,
    saldo DOUBLE NOT NULL,
    cuenta_id BIGINT NOT NULL
);
