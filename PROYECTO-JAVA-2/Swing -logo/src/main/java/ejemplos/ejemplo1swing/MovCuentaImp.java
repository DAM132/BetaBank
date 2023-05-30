
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import static ejemplos.ejemplo1swing.MovimientoDAOImp.crearMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DAM122
 */
public class MovCuentaImp{

     private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    
    public List<MovCuenta> listar() {
        String sql = "SELECT * FROM movcuenta";
        List<MovCuenta> movimientosCuenta = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MovCuenta movimientoCuenta = crearMovCuenta(rs);
                if (!movimientosCuenta.add(movimientoCuenta)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientosCuenta;
    }
    
    
    public ArrayList<MovCuenta> listarPorCliente(String iban) {
        String sql = "SELECT * FROM movcuenta where numeroCuenta=?";
        ArrayList<MovCuenta> movimientosCuenta = new ArrayList<>();
         try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, iban);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MovCuenta movimientoCuenta = crearMovCuenta(rs);
                    movimientosCuenta.add(movimientoCuenta);
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientosCuenta;
    }
    
    
    
    
    public MovCuenta getMovCuentaDeUnCliente(String iban) {
        String sql = "SELECT * FROM movcuenta WHERE numeroCuenta=?";
        MovCuenta movimientoCuenta = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, iban);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movimientoCuenta = crearMovCuenta(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientoCuenta;
    }

    
    public void insertar(MovCuenta movimientoCuenta) {
        String sql = "INSERT INTO movcuenta(numeroCuenta,idMovimientos) VALUES(?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, movimientoCuenta.getCuenta().getIban());
            stmt.setInt(2, movimientoCuenta.getMovimiento().getIdMovimiento());
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

   
    public void modificar(MovCuenta movimientoCuenta) {
        String sql = "UPDATE movcuenta SET numeroCuenta=?,idMovimientos=? idMovCuenta=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, movimientoCuenta.getCuenta().getIban());
            stmt.setInt(2, movimientoCuenta.getMovimiento().getIdMovimiento());
            stmt.setInt(3, movimientoCuenta.getIdMovCuenta());
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

    
    public void eliminar(int id) {
        String sql = "DELETE FROM movcuenta WHERE idMovCuenta=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
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
    
    public static MovCuenta crearMovCuenta(ResultSet rs) throws SQLException {
        CuentaImp cuentaImp = new CuentaImp();
        Cuenta cuenta = cuentaImp.getDatosCuenta(rs.getString("numeroCuenta"));
        MovimientoDAOImp movimientoDAO = new MovimientoDAOImp();
        Movimiento movimiento = movimientoDAO.porId(rs.getInt("idMovimientos"));
        return new MovCuenta(rs.getInt("idMovimientos"), cuenta, movimiento);
    }
    
}
