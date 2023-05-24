/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DAM118
 */
public class MovimientoDAOImp implements Repositorio<Movimiento> {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Movimiento> listar() {
        String sql = "SELECT * FROM movimientos";
        List<Movimiento> movimientos = new LinkedList<>();
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movimiento movimiento = crearMovimiento(rs);
                if (!movimientos.add(movimiento)) {
                    throw new Exception("error no se ha insertado el objeto");
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimientos;
    }

    @Override
    public Movimiento porId(int id) {
        String sql = "SELECT * FROM movimiento WHERE idMovimientos=?";
        Movimiento movimiento = null;
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movimiento = crearMovimiento(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLexception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movimiento;
    }

    @Override
    public void insertar(Movimiento movimiento) {
        String sql = "INSERT INTO movimiento(cantidad,concepto,emisor,destinatario,numeroCuenta,fecha) VALUES(?,?,?,?,?,?)";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fnac = java.sql.Date.valueOf(movimiento.getFechaMovimiento());
            stmt.setDouble(1, movimiento.getCantidad());
            stmt.setString(2, movimiento.getConcepto());
            stmt.setString(3, movimiento.getEmisor());
            stmt.setInt(4, movimiento.getDestinatario().getIdCliente());
            stmt.setString(5, movimiento.getNumeroCuenta());
            stmt.setDate(6, fnac);
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
    public void modificar(Movimiento movimiento) {
        String sql = "UPDATE movimiento SET cantidad=?,concepto=?,emisor=?,destinatario=?,numeroCuenta=?,fecha=?  WHERE idMovimiento=?";
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            java.sql.Date fnac = java.sql.Date.valueOf(movimiento.getFechaMovimiento());
            stmt.setDouble(1, movimiento.getCantidad());
            stmt.setString(2, movimiento.getConcepto());
            stmt.setString(3, movimiento.getEmisor());
            stmt.setInt(4, movimiento.getDestinatario().getIdCliente());
            stmt.setString(5, movimiento.getNumeroCuenta());
            stmt.setDate(6, fnac);
            stmt.setInt(7, movimiento.getIdMovimiento());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha modificado el cliente");
            } else {
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
        String sql = "DELETE FROM movimiento WHERE idMovimientos=?";
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

    public static Movimiento crearMovimiento(ResultSet rs) throws SQLException {
        ClienteDAOImp a = new ClienteDAOImp();
        return new Movimiento(rs.getInt("idMovimientos"), rs.getDouble("cantidad"), rs.getString("concepto"), rs.getString("emisor"), a.porId(rs.getInt("destinatario")), rs.getDate("fecha").toLocalDate(), rs.getString("numeroCuenta"));
    }

}
