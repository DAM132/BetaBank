                                           -- BASE DE DATOS
create database banco;
use banco;

-- CLiente
create table cliente(
idCliente INT UNSIGNED NOT NULL auto_increment,
dni CHAR(9) NOT NULL,
nombre VARCHAR (45) NOT NULL,
apellidos VARCHAR (150) NOT NULL,
fnac DATE NOT NULL,
domicilio VARCHAR (150) NOT NULL,
localidad VARCHAR (55) NOT NULL,
sexo ENUM ('M','H') DEFAULT NULL,
activo BOOLEAN NOT NULL DEFAULT FALSE,
mediaIngreso DOUBLE NOT NULL,
casado BOOLEAN NOT NULL DEFAULT FALSE,
PRIMARY KEY (idCliente),
UNIQUE uk_dni_cliente(dni) 
);

-- Perfil

create table perfil (
idPerfil INT UNSIGNED NOT NULL auto_increment,
usuario VARCHAR(25) NOT NULL,
contraseña VARCHAR(50) NOT NULL,
estadoCivil ENUM ('CASADO','SOLTERO') DEFAULT 'SOLTERO',
estadoLaboral ENUM ('EMPLEADO','DESEMPLEADO','AUTONOMO','PENSIONISTA','RENTISTA','ESTUDIANTE','RESPONSABLE DEL HOGAR') DEFAULT 'DESEMPLEADO',
moroso BOOLEAN NOT NULL DEFAULT FALSE,
idPareja INT,
PRIMARY KEY (idPerfil),
CONSTRAINT fk_perfil_cliente FOREIGN KEY (idPerfil) REFERENCES cliente(idCliente)
);

-- Cuenta 

create table cuenta(
iban CHAR(24) NOT NULL,
tipoCuenta VARCHAR(45) NOT NULL,
saldo DOUBLE NOT NULL,
ingresos DOUBLE NOT NULL,
media DOUBLE NOT NULL,
idCliente INT UNSIGNED NOT NULL,
idMovimiento INT UNSIGNED NOT NULL,
PRIMARY KEY (iban),
CONSTRAINT fk_cuenta_cliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

-- Movimiento

create table movimiento(
idMovimientos INT UNSIGNED NOT NULL,
cantidad FLOAT NOT NULL,
concepto VARCHAR(200) NOT NULL,
emisor VARCHAR(50) NOT NULL,
destinatario INT UNSIGNED NOT NULL,
PRIMARY KEY (idMovimientos),
CONSTRAINT fk_movimiento_cliente FOREIGN KEY (destinatario) REFERENCES cliente(idCliente)
);

-- Prestamos 

create table prestamos(
idPrestamo INT UNSIGNED NOT NULL auto_increment,
idCliente INT UNSIGNED NOT NULL,
fechaFirma DATE NOT NULL,
periodoMes VARCHAR(30) NOT NULL,
cantidad DOUBLE NOT NULL,
tipoInteres DOUBLE NOT NULL,
plazoDias VARCHAR(30) NOT NULL,
estado ENUM ('ACTIVO','INACTIVO') ,
PRIMARY KEY (idPrestamo),
CONSTRAINT fk_prestamosPreconcedidos_cliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);
