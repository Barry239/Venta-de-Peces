-- Crear base de datos
CREATE DATABASE IF NOT EXISTS pezkbonis;

-- Seleccionar base de datos
USE pezkbonis;

-- Crear tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios(
	id_usuario INT(11) NOT NULL AUTO_INCREMENT,
	nombre_usuario VARCHAR(255) NOT NULL,
	email_usuario VARCHAR(255) UNIQUE NOT NULL,
	contrasena_usuario VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_usuario)
);

-- Crear tabla productos
CREATE TABLE IF NOT EXISTS productos(
	id_producto INT(11) NOT NULL AUTO_INCREMENT,
	nombre_producto VARCHAR(255) UNIQUE NOT NULL,
	precio_producto FLOAT NOT NULL,
	stock_producto INT(11) NOT NULL DEFAULT 0,
	PRIMARY KEY (id_producto)
);

-- Crear tabla carrito
CREATE TABLE IF NOT EXISTS carrito(
	id_carrito INT(11) NOT NULL AUTO_INCREMENT,
	id_usuario INT(11) NOT NULL,
	id_producto INT(11) NOT NULL,
	cantidad_carrito INT(11) NOT NULL,
	PRIMARY KEY (id_carrito),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla compras
CREATE TABLE IF NOT EXISTS compras(
	id_compra INT(11) NOT NULL AUTO_INCREMENT,
	creada_compra TIMESTAMP NOT NULL DEFAULT now(),
	terminada_compra BOOLEAN DEFAULT FALSE,
	domicilio_compra VARCHAR(255) NOT NULL,
	cantidad_compra INT(11) NOT NULL,
	id_u INT(11) NOT NULL,
	id_producto INT(11) NOT NULL,
	PRIMARY KEY (id_compra),
	FOREIGN KEY (id_u) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Crear funciones
DELIMITER //


DELIMITER ;


-- Insertar productos
INSERT INTO productos(nombre_producto, precio_producto) VALUES 
("ANGEL SURTIDO CH", 25),
("ANGEL SURTIDO GDE", 134),
("ANGEL AZUL GDE", 150),
("BETTA MACHO", 75),
("BETTA HEMBRA", 45),
("CEBRA TRANSGENICA", 10),
("COMETAS", 102),
("OSCAR ALBINO GDE", 170),
("GUPPY HEMBRA", 10),
("GUPPY MACHO", 40),
("MOLLY BALON", 20),
("MOLLY SURTIDA", 10),
("MONJA COMUN GDE", 13),
("MONJA TRANSGENICA SURTIDA", 40),
("PECES TELESCOPICOS", 127),
("GOLDFISH ORANDA", 980),
("GATO COLA ROJA", 800);
