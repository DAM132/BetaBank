/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import static ejemplos.ejemplo1swing.CuentaImp.crearCuenta;
import static ejemplos.ejemplo1swing.PrestamoDAOImp.crearPrestamo;
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
public class NominaDAOImp implements Repositorio<Nomina> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Nomina> listar() {
        String sql = "SELECT * FROM nominas";
        List<Nomina> nominas = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Nomina nomina = crearNomina(rs);
                if (!nominas.add(nomina)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nominas;
    }

    public List<Nomina> listarNominasUsuario(int idUsuario) {
        String sql = "SELECT * FROM nominas WHERE idUsuario=?";
        List<Nomina> nominas = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, idUsuario);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Nomina nomina = crearNomina(rs);
                    if (!nominas.add(nomina)) {
                        throw new Exception("error no se ha insertado el objeto");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nominas;
    }

    @Override
    public Nomina porId(int id) {
        String sql = "SELECT * FROM nominas WHERE idNomina=?";
        Nomina nomina = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomina = crearNomina(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nomina;
    }

    @Override
    public void insertar(Nomina nomina) {
        String sql = "INSERT INTO nominas(fecha,cantidad,idUsuario) VALUES(?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fecha = java.sql.Date.valueOf(nomina.getFechaNomina());
            stmt.setDate(1, fecha);
            stmt.setDouble(2, nomina.getCantidad());
            stmt.setInt(3, nomina.getUsuario().getIdUsuario());
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

    @Override
    public void modificar(Nomina nomina) {
        String sql = "UPDATE nominas SET fecha=?,cantidad=?,idUsuario=? WHERE idNomina=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fecha = java.sql.Date.valueOf(nomina.getFechaNomina());
            stmt.setDate(1, fecha);
            stmt.setDouble(2, nomina.getCantidad());
            stmt.setInt(3, nomina.getUsuario().getIdUsuario());
            stmt.setInt(4, nomina.getIdNomina());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado la nomina");
            } else {
                System.out.println("Se ha modificado la nomina");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM nominas WHERE idNomina=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            int borrado = stmt.executeUpdate();
            if (borrado != 1) {
                throw new Exception("No se ha borrado la nomina");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Nomina crearNomina(ResultSet rs) throws SQLException {
        UsuarioDAOImp clienteAux = new UsuarioDAOImp();
        Usuario usuario = clienteAux.porId(rs.getInt("idUsuario"));
        Nomina nomina = new Nomina(rs.getInt("idNomina"), rs.getDate("fecha").toLocalDate(), rs.getDouble("cantidad"), usuario);
        return nomina;
    }

}
