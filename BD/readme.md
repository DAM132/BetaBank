# Trigers

Para la bbdd y poder actualizar los distintos tipos de datos hemos tenído que crear distintos Triggers. Aquí dejamos algunos ejemplos:

## Procedimiento prestamo

<code>CREATE DEFINER=`root`@`%` TRIGGER procedimentoPrestmo AFTER INSERT ON `prestamos` FOR EACH ROW
BEGIN
Declare de INT;
SELECT idUsuario INTO de FROM prestamos WHERE idUsuario=new.IdUsuario;
UPDATE cuenta SET saldo=saldo+new.cantidadPendiente where idUsuario=de;
insert into movimientos(cantidad, fechaMov, concepto, emisor, destinatario) VALUES (new.cantidadPendiente,curdate(),'prestamo','Banco H',(SubSTRING((SELECT concat(nombre,' ',apellidos) FROM usuario WHERE IdUsuario = new.IdUsuario),1,50)));
insert into movprestamo(idPrestamo, idMovimientos) VALUES (new.IdPrestamo,(SELECT idMovimientos FROM banco.movimiento ORDER BY idMovimientos DESC));
END</code>

## Amortizar Prestamo

<code>CREATE DEFINER=`root`@`%` TRIGGER amortizarPrestamo AFTER UPDATE ON `prestamos` FOR EACH ROW
BEGIN
if (new.cantidadPendiente<old.cantidadPendiente) THEN
INSERT INTO movimientos(cantidad, fechaMov, concepto, emisor, destinatario) VALUES (old.cantidadPendiente-new.cantidadPendiente,curdate(),'amortizacion',(SubSTRING((SELECT concat(nombre,' ',apellidos) FROM usuario WHERE IdUsuario = new.IdUsuario),1,50)),'BANCO H');
insert into movprestamo(idPrestamo, idMovimientos) VALUES (old.IdPrestamo,(SELECT idMovimientos FROM banco.movimiento ORDER BY idMovimientos DESC));
END IF;
END</code>

  ##  Aumentar Prestamo

  <code>CREATE DEFINER=`root`@`%` TRIGGER `banco`.`aumentarPrestamo` AFTER UPDATE ON `prestamos` FOR EACH ROW
if (new.cantidadPendiente>old.cantidadPendiente) THEN
UPDATE cuenta SET saldo=saldo+new.cantidadPendiente-old.cantidadPendiente where idUsuario=de;
INSERT INTO movimientos(cantidad, fechaMov, concepto, emisor, destinatario) VALUES (old.cantidadPendiente-new.cantidadPendiente,curdate(),'aumento',(SubSTRING((SELECT concat(nombre,' ',apellidos) FROM usuario WHERE IdUsuario = new.IdUsuario),1,50)),'BANCO H');
insert into movprestamo(idPrestamo, idMovimientos) VALUES (old.IdPrestamo,(SELECT idMovimientos FROM banco.movimiento ORDER BY idMovimientos DESC));
END IF</code>
  
  ## Procedimiento MediaIngresos 
  <code>CREATE DEFINER=`root`@`%` PROCEDURE `mediaIngresos`()
BEGIN
declare contador int;
declare numero double;
declare trabajo varchar(28);
set contador=1;
while contador<51 do
set numero=FLOOR(RAND() * (4000 - 1 )+ 1);
set trabajo=(select estadoLaboral from perfil where idPerfil=contador);
if trabajo!='DESEMPLEADO' then
update usuario set mediaIngreso =numero where idUsuario=contador;
end if;
set contador=contador+1;
end while;

                  END</code>
    
      ## Procedimiento PrestamosMayo
    
    <code>CREATE DEFINER=`root`@`%` PROCEDURE `prestamoMayo`()
BEGIN
Select * FROM prestamos WHERE fechaFirma LIKE '2023-05-%%';
END</code>

    
  
