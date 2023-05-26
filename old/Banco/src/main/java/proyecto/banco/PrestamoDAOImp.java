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
 * @author DAM118
 */
public class PrestamoDAOImp implements Repositorio<Prestamo> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Prestamo> listar() {
        String sql = "SELECT * FROM prestamos";
        List<Prestamo> prestamos = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Prestamo prestamo = crearPrestamo(rs);
                if (!prestamos.add(prestamo)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return prestamos;
    }

    @Override
    public Prestamo porId(int id) {
        String sql = "SELECT * FROM prestamo WHERE idPrestamo=?";
        Prestamo prestamo = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    prestamo = crearPrestamo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return prestamo;
    }

    @Override
    public void insertar(Prestamo prestamo) {
        String sql = "INSERT INTO prestamo(idCliente,fechaFirma,periodoMes,cantidad,tipoInteres,plazoDias,estado,idMovimiento) VALUES(?,?,?,?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fechafirma = java.sql.Date.valueOf(prestamo.getFechaFirma());
            stmt.setInt(1, prestamo.getIdCliente());
            stmt.setDate(2, fechafirma);
            stmt.setInt(3, prestamo.getPeriodoMes());
            stmt.setDouble(4, prestamo.getCantidad());
            stmt.setDouble(5, prestamo.getTipoInteres());
            stmt.setInt(6, prestamo.getPlazoDias());
            stmt.setString(7, prestamo.getEstadoPrest().name());
            stmt.setInt(8, prestamo.getIdMovimiento());
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
    public void modificar(Prestamo prestamo) {
        String sql = "UPDATE prestamo SET idCliente=?,fechaFirma=?,periodoMes=?,cantidad=?,tipoInteres=?,plazpDias=?,estado=?,idMovimiento=?  WHERE idPrestamo=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fechafirma = java.sql.Date.valueOf(prestamo.getFechaFirma());
            stmt.setInt(1, prestamo.getIdCliente());
            stmt.setDate(2, fechafirma);
            stmt.setInt(3, prestamo.getPeriodoMes());
            stmt.setDouble(4, prestamo.getCantidad());
            stmt.setDouble(5, prestamo.getTipoInteres());
            stmt.setInt(6, prestamo.getPlazoDias());
            stmt.setString(7, prestamo.getEstadoPrest().name());
            stmt.setInt(8, prestamo.getIdMovimiento());
            stmt.setInt(9, prestamo.getIdPrestamo());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el prestamos");
            } else {
                System.out.println("Se ha modificado el prestamo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
       String sql = "DELETE FROM prestamo WHERE idPrestamo=?";
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

    public static Prestamo crearPrestamo(ResultSet rs) throws SQLException {
        LocalDate fechafirma = rs.getDate("fechaFirma").toLocalDate();
        return new Prestamo(rs.getInt("idPrestamo"), rs.getInt("idCliente"), fechafirma, rs.getDouble("cantidad"), rs.getInt("periodoMes"), rs.getDouble("tipoInteres"), rs.getInt("plazoDias"), EstadoPrestamo.valueOf(rs.getString("EstadoPrestamo")),rs.getInt("idMovimiento"));
    }

}
