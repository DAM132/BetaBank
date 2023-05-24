/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DAM122
 */
public class CuentaImp {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public static Cuenta getDatosCuenta(String iban) {
        String sql = "SELECT * FROM cuenta WHERE iban=?";
        Cuenta cuenta = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, iban);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuenta = crearCuenta(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cuenta;
    }

    public static void abrirCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta(iban,tipoCuenta,saldo,ingresos,media,idCliente) VALUES(?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, cuenta.getIban());
            stmt.setString(2, cuenta.getTipoCuenta());
            stmt.setDouble(3, cuenta.getSaldo());
            stmt.setDouble(4, cuenta.getIngresos());
            stmt.setDouble(5, cuenta.getMedia());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha conseguido abrir la cuenta");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void borrarCuenta(String iban, int idCliente) {
        String sql = "DELETE FROM cuenta WHERE iban=? and idCliente=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, iban);
            stmt.setInt(2, idCliente);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado un solo participante");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Cliente getDatosCliente(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        Cliente cliente = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = crearCliente(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

    public static Cliente crearCliente(ResultSet rs) throws SQLException {
        return new Cliente(rs.getInt("idCliente"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getDate("fnac").toLocalDate(), rs.getString("domicilio"), rs.getString("localidad"), Sexo.valueOf(rs.getString("sexo")), rs.getBoolean("casado"), rs.getDouble("mediaIngreso"), rs.getBoolean("activo"));
    }

    public static Cuenta crearCuenta(ResultSet rs) throws SQLException {
        Cliente cliente = (Cliente) getDatosCliente(rs.getInt("idCliente"));
        Cuenta cuenta = new Cuenta(rs.getString("iban"), rs.getString("tipoCuenta"), rs.getDouble("saldo"), rs.getDouble("ingresos"), rs.getDouble("media"), cliente);
        return cuenta;
    }
}
