/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import static ejemplos.ejemplo1swing.MovCuentaImp.crearMovCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DAM122
 */
public class MovPrestamoImp{
    
    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    
    public List<MovPrestamo> listar() {
        String sql = "SELECT * FROM movprestamo";
        List<MovPrestamo> movimientosPrestamos = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MovPrestamo movimientoPrestamo = crearMovPrestamo(rs);
                if (!movimientosPrestamos.add(movimientoPrestamo)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientosPrestamos;
    }

    
    public MovPrestamo porId(int idPrestamo) {
         String sql = "SELECT * FROM movprestamo WHERE idPrestamo=?";
        MovPrestamo movimientoCuenta = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, idPrestamo);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movimientoCuenta = crearMovPrestamo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientoCuenta;
    }

    
    public void insertar(MovPrestamo movimientoPrestamo) {
        String sql = "INSERT INTO movprestamo(idPrestamo,idMovimientos) VALUES(?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, movimientoPrestamo.getPrestamo().getIdPrestamo());
            stmt.setInt(2, movimientoPrestamo.getMovimiento().getIdMovimiento());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha conseguido registrar el movimiento");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modificar(MovPrestamo movimientoPrestamo) {
        String sql = "UPDATE movprestamo SET idPrestamo=?,idMovimientos=? idMovPrestamo=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, movimientoPrestamo.getPrestamo().getIdPrestamo());
            stmt.setInt(2, movimientoPrestamo.getMovimiento().getIdMovimiento());
            stmt.setInt(3, movimientoPrestamo.getIdMovPrestamo());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el movimiento");
            } else {
                System.out.println("Se ha modificado el movimiento");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void eliminar(int idMovPrestamo) {
        String sql = "DELETE FROM movprestamo WHERE idMovPrestamo=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, idMovPrestamo);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado el movimiento");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MovPrestamo crearMovPrestamo(ResultSet rs) throws SQLException {
        PrestamoDAOImp prestamoDAOImp = new PrestamoDAOImp();
        Prestamo prestamo = prestamoDAOImp.porId(rs.getInt("idPrestamo"));
        MovimientoDAOImp movimientoDAO = new MovimientoDAOImp();
        Movimiento movimiento = movimientoDAO.porId(rs.getInt("idMovimientos"));
        return new MovPrestamo(rs.getInt("idMovimientos"), prestamo, movimiento);
    }
}
