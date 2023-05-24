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
 * @author Pablo
 */
public class ClienteDAOImp implements Repositorio<Cliente> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cuenta";
        List<Cliente> clientes = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  
              ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = crearCliente(rs);
                if (!clientes.add(cliente)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente porId(int id) {
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

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente(dni,nombre,apellidos,telefono,fnac,domicilio,localidad,sexo,casado,mediaIngreso) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fnac=java.sql.Date.valueOf(cliente.getFnac());
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellidos());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDate(5, fnac);
            stmt.setString(6, cliente.getDomicilio());
            stmt.setString(7, cliente.getLocalidad());
            stmt.setString(8, String.valueOf(cliente.getSexo()));
            stmt.setBoolean(9, cliente.isCasado());
            stmt.setDouble(10, cliente.getMediaIngreso());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha conseguido registrar al nuevo cliente");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modificar(Cliente cliente) {
         String sql = "UPDATE cliente SET dni=?,nombre=?,apellidos=?,telefono=?,fnac=?,domicilio=?,localidad=?,sexo=?,casado=?,mediaIngreso=?  WHERE idCliente=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fnac=java.sql.Date.valueOf(cliente.getFnac());
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellidos());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDate(5, fnac);
            stmt.setString(6, cliente.getDomicilio());
            stmt.setString(7, cliente.getLocalidad());
            stmt.setString(8, String.valueOf(cliente.getSexo()));
            stmt.setBoolean(9, cliente.isCasado());
            stmt.setDouble(10, cliente.getMediaIngreso());
            stmt.setInt(11, cliente.getIdCliente());
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

    @Override
    public void eliminar(int id) {
         String sql = "DELETE FROM cliente WHERE idCliente=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado el cliente");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Cliente crearCliente(ResultSet rs) throws SQLException {
        return new Cliente(rs.getInt("idCliente"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getDate("fnac").toLocalDate(), rs.getString("domicilio"), rs.getString("localidad"), Sexo.valueOf(rs.getString("sexo")), rs.getBoolean("casado"), rs.getDouble("mediaIngreso"), rs.getBoolean("activo"));
    }

}
