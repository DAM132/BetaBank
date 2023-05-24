/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

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
public class CuentaImp {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public static List<Cuenta> getDatosCuentas() {
        String sql = "SELECT * FROM cuenta";
        List<Cuenta> cuentas = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  
              ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cuenta cuenta = crearCuenta(rs);
                if (!cuentas.add(cuenta)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cuentas;
    }

    public static List<Cuenta> getDatosDeUnTipoCuenta(String tipoCuenta) {
        String sql = "SELECT * FROM cuenta WHERE tipoCuenta=?";
        List<Cuenta> cuentas = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            try ( ResultSet rs = stmt.executeQuery()) {
                stmt.setString(1, tipoCuenta);
                while (rs.next()) {
                    Cuenta cuenta = crearCuenta(rs);
                    if (!cuentas.add(cuenta)) {
                        throw new Exception("error no se ha insertado el objeto");
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cuentas;
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

    public void modificarCuenta(Cuenta cuenta) {
         String sql = "UPDATE cuenta SET iban=?,tipoCuenta=?,saldo=?,ingresos=?,fnac=?,media=? WHERE idCliente=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, cuenta.getIban());
            stmt.setString(2, cuenta.getTipoCuenta());
            stmt.setDouble(3, cuenta.getSaldo());
            stmt.setDouble(4, cuenta.getIngresos());
            stmt.setDouble(5, cuenta.getMedia());
            stmt.setInt(6, cuenta.getCliente().getIdCliente());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el cliente");
            }else{
                System.out.println("Se ha modificado el cliente");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public static Cuenta crearCuenta(ResultSet rs) throws SQLException {
        ClienteDAOImp clienteAux = new ClienteDAOImp();
        Cliente cliente = clienteAux.porId(rs.getInt("idCliente"));
        Cuenta cuenta = new Cuenta(rs.getString("iban"), rs.getString("tipoCuenta"), rs.getDouble("saldo"), rs.getDouble("ingresos"), rs.getDouble("media"), cliente);
        return cuenta;
    }
}
