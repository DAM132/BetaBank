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
public class PerfilDAOImp implements Repositorio<Perfil> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Perfil> listar() {
        String sql = "SELECT idPerfil,usuario,md5(contraseña),estadoCivil,estadoLaboral,moroso,idPareja FROM perfil";
        List<Perfil> perfiles = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perfil perfil = crearPerfil(rs);
                if (!perfiles.add(perfil)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return perfiles;
    }

    @Override
    public Perfil porId(int id) {
        String sql = "SELECT * FROM cliente WHERE idPerfil=?";
        Perfil perfil = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    perfil = crearPerfil(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return perfil;
    }

    @Override
    public void insertar(Perfil perfil) {
        String sql = "INSERT INTO perfil(usuario,contraseña,estadoCivil,estadoLaboral,moroso,idPareja) VALUES(?,?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, perfil.getUsuario());
            stmt.setString(2, perfil.getContrasena());
            stmt.setString(3, String.valueOf(perfil.getEstadoCiv()));
            stmt.setString(4, String.valueOf(perfil.getEstadolab()));
            stmt.setBoolean(5, perfil.isMoroso());
            stmt.setInt(6, perfil.getIdPareja());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha conseguido registrar el perfil del cliente");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modificar(Perfil perfil) {
        String sql = "UPDATE perfil SET usuario=?,contraseña=?,estadoCivil=?,estadoLaboral=?,moroso=?,idPareja=? WHERE idPerfil=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, perfil.getUsuario());
            stmt.setString(2, perfil.getContrasena());
            stmt.setString(3, String.valueOf(perfil.getEstadoCiv()));
            stmt.setString(4, String.valueOf(perfil.getEstadolab()));
            stmt.setBoolean(5, perfil.isMoroso());
            stmt.setInt(6, perfil.getIdPareja());
            stmt.setInt(7, perfil.getIdPerfil());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el perfil");
            } else {
                System.out.println("Se ha modificado el perfil");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
         String sql = "DELETE FROM perfil WHERE idPerfil=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado el perfil");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Perfil crearPerfil(ResultSet rs) throws SQLException {
        Perfil perfil = new Perfil(rs.getInt("idPerfil"), rs.getString("usuario"), rs.getString("contraseña"), EstadoCivil.valueOf(rs.getString("estadoCivil")), EstadoLaboral.valueOf(rs.getString("estadoCivil")), rs.getBoolean("moroso"), rs.getInt("idPareja"));
        return perfil;
    }

}
