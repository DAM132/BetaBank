/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import com.mysql.cj.util.StringUtils;
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
public class UsuarioDAOImp implements Repositorio<Usuario> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    
    public List<Usuario> listarPorFiltro(FiltroUsuarios filtro) {
        String sql = "SELECT * FROM usuario where 1=1";
        
        if(!StringUtils.isEmptyOrWhitespaceOnly(filtro.getDni())){
            sql += " and DNI='" + filtro.getDni() + "'";
        }
        if(!StringUtils.isEmptyOrWhitespaceOnly(filtro.getLocalidad())){
            sql += " and localidad='" + filtro.getLocalidad() + "'";
        }
        if(!StringUtils.isEmptyOrWhitespaceOnly(filtro.getNombre())){
            sql += " and nombre='" + filtro.getNombre()+ "'";
        }
        
        if(filtro.getActivo() != null){
            if(filtro.getActivo()){
                sql += " and activo=1";
            }else{
                sql += " and activo=0";
            }
        }
        
        if(filtro.getCasado() != null){
            if(filtro.getCasado()){
                sql += " and casado=1";
            }else{
                sql += " and casado=0";
            }
        }
        
        if(filtro.getCasado() != null){
            if(filtro.getCasado()){
                sql += " and casado=1";
            }else{
                sql += " and casado=0";
            }
        }
        
        if(filtro.getSexo() != null){
            sql += " and sexo='" + filtro.getSexo() + "'";
        }
 
        List<Usuario> clientes = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  
              ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario cliente = crearCliente(rs);
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
    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> clientes = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  
              ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario cliente = crearCliente(rs);
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
    public Usuario porId(int id) {
        String sql = "SELECT * FROM usuario WHERE idUsuario=?";
        Usuario usuario = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = crearCliente(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return usuario;
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario(dni,idPerfil,nombre,apellidos,telefono,fnac,domicilio,localidad,sexo,casado,mediaIngreso,activo,idPareja) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fnac=java.sql.Date.valueOf(usuario.getFnac());
            stmt.setString(1, usuario.getDni());
            stmt.setInt(2, usuario.getPerfil().getIdPerfil());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getApellidos());
            stmt.setString(5, usuario.getTelefono());
            stmt.setDate(6, fnac);
            stmt.setString(7, usuario.getDomicilio());
            stmt.setString(8, usuario.getLocalidad());
            stmt.setString(9, String.valueOf(usuario.getSexo()));
            stmt.setBoolean(10, usuario.isCasado());
            stmt.setDouble(11, usuario.getMediaIngreso());
            stmt.setBoolean(12, usuario.isActivo());
            stmt.setDouble(13, usuario.getIdPareja());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha conseguido registrar al nuevo usuario");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modificar(Usuario cliente) {
         String sql = "UPDATE usuario SET dni=?,nombre=?,apellidos=?,telefono=?,fnac=?,domicilio=?,localidad=?,sexo=?,casado=?,mediaIngreso=?  WHERE idUsuario=?";
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
            stmt.setInt(11, cliente.getIdUsuario());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el usuario");
            }else{
                System.out.println("Se ha modificado el usuario");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
         String sql = "DELETE FROM usuario WHERE idUsuario=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado el usuario");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public Usuario porDNI(String dni){
        String sql = "SELECT * FROM usuario WHERE dni=?";
        Usuario cliente = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, dni);
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

    public static Usuario crearCliente(ResultSet rs) throws SQLException {
        PerfilDAOImp perfilDAOImp = new PerfilDAOImp();
        Perfil perfil = perfilDAOImp.porId(rs.getInt("idPerfil"));
        return new Usuario(rs.getInt("idUsuario"),perfil ,rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("telefono"), rs.getDate("fnac").toLocalDate(), rs.getString("domicilio"), rs.getString("localidad"), Sexo.valueOf(rs.getString("sexo")), rs.getBoolean("casado"), rs.getDouble("mediaIngreso"), rs.getBoolean("activo"),rs.getInt("idPareja"));
    }
    
     

}
