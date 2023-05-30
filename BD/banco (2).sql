 create database banco;
 use banco ;
 show create table cuenta;
CREATE TABLE `perfil` (
  `idPerfil` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario` varchar(25) NOT NULL,
  `contraseña` varchar(50) NOT NULL,
  `estadoCivil` enum('CASADO','SOLTERO') DEFAULT 'SOLTERO',
  `estadoLaboral` enum('EMPLEADO','DESEMPLEADO','AUTONOMO','PENSIONISTA','RENTISTA','ESTUDIANTE','RESPONSABLE_DEL_HOGAR') DEFAULT 'DESEMPLEADO',
  `moroso` tinyint(1) NOT NULL DEFAULT 0,
  `tipoPerfil` enum('CLIENTE','BANQUERO') NOT NULL DEFAULT 'CLIENTE',
  PRIMARY KEY (`idPerfil`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `usuario` (
  `idUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPerfil` int(10) unsigned NOT NULL,
  `dni` char(9) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `telefono` char(9) NOT NULL,
  `fnac` date NOT NULL,
  `domicilio` varchar(150) NOT NULL,
  `localidad` varchar(55) NOT NULL,
  `sexo` enum('M','H') DEFAULT NULL,
  `casado` boolean NOT NULL DEFAULT FALSE,
  `mediaIngreso` double NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 0,
  `idPareja` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `uk_dni_cliente` (`dni`),
  KEY `fk_perfil_usuario_idx` (`idPerfil`),
  CONSTRAINT `fk_perfil_usuario` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `cuenta` (
  `iban` char(24) NOT NULL,
  `tipoCuenta` varchar(45) NOT NULL,
  `saldo` double NOT NULL,
  `ingresos` double NOT NULL,
  `media` double NOT NULL,
  `idUsuario` int(10) unsigned NOT NULL,
  PRIMARY KEY (`iban`),
  KEY `fk_cuenta_cliente` (`idUsuario`),
  CONSTRAINT `fk_cuenta_cliente` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `prestamos` (
  `idPrestamo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idUsuario` int(10) unsigned NOT NULL,
  `fechaFirma` date NOT NULL,
  `periodoMes` smallint(6) NOT NULL,
  `cantidadPendiente` double NOT NULL,
  `tipoInteres` double NOT NULL,
  `plazoDias` smallint(6) NOT NULL,
  `estado` enum('DENEGADO','PENDIENTE','ACTIVO','LIQUIDADO') DEFAULT 'ACTIVO',
  PRIMARY KEY (`idPrestamo`),
  KEY `fk_prestamosPreconcedidos_cliente` (`idUsuario`),
  CONSTRAINT `fk_prestamosPreconcedidos_cliente` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci; 

CREATE TABLE `movimiento` (
  `idMovimientos` int(10) unsigned NOT NULL,
  `cantidad` float NOT NULL,
  `fechaMov` date NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `emisor` varchar(50) NOT NULL,
  `destinatario` varchar(50) NOT NULL,
  PRIMARY KEY (`idMovimientos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `movcuenta` (
  `idMovCuenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numeroCuenta` char(24) NOT NULL,
  `idMovimientos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idMovCuenta`),
  KEY `fk_movimiento_cuenta_idx` (`numeroCuenta`),
  KEY `fk_cuenta_movimiento_idx` (`idMovimientos`),
  CONSTRAINT `fk_cuenta_movimiento` FOREIGN KEY (`idMovimientos`) REFERENCES `movimiento` (`idMovimientos`),
  CONSTRAINT `fk_movimiento_cuenta` FOREIGN KEY (`numeroCuenta`) REFERENCES `cuenta` (`iban`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `movprestamo` (
  `idMovPrestamo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPrestamo` int(10) unsigned NOT NULL,
  `idMovimientos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idMovPrestamo`),
  KEY `fk_movimiento_prestamo_idx` (`idMovimientos`),
  KEY `fk_prestamo_movimiento_idx` (`idPrestamo`),
  CONSTRAINT `fk_movimieento_prestamo` FOREIGN KEY (`idMovimientos`) REFERENCES `movimiento` (`idMovimientos`),
  CONSTRAINT `fk_prestamo_movimiento` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;