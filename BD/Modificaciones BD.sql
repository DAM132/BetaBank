-- Alter table cliente ADD telefono CHAR(9) NOT NULL AFTER apellidos;
-- Alter table movimiento MODIFY cantidad DOUBLE NOT NULL;

 /* create table parejas (
idPareja INT UNSIGNED NOT NULL,
idCliente INT UNSIGNED NOT NULL, 
idCliente2 INT UNSIGNED NOT NULL,
PRIMARY KEY (idPareja)
); */ 

-- ALTER TABLE perfil ADD CONSTRAINT fk_parejas_clientes FOREIGN KEY (idPareja) REFERENCES parejas(idPareja);
-- ALTER TABLE movimiento ADD fecha DATE NOT NULL;

-- Error procedimiento 
-- ERROR 1327: Undeclared variable: casado
/* 
 Procedimiento : 
	CREATE DEFINER=`root`@`%` PROCEDURE `generarPareja`(in dniCliente char (9),in dniPareja char(9),OUT pareja INT)
BEGIN
declare numeroClientes INT;
declare n INT;
DECLARE pareja BOOLEAN;
SET n=0;
select count(idCliente) INTO numeroClientes from cliente;
while n<numeroClientes DO
select cliente.casado INTO pareja from cliente;
IF casado = TRUE THEN
INSERT INTO parejas VALUES (n,(select idCliente from cliente where cliente.dni=dniCliente),(select idCliente from cliente where cliente.dni=dniPareja));
END IF;
SET n=n+1;
END WHILE;
END
*/

ALTER TABLE prestamos MODIFY periodoMes SMALLINT NOT NULL;
ALTER TABLE prestamos MODIFY plazoDias SMALLINT NOT NULL;

