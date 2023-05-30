/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

/**
 *
 * @author DAM122
 */
public class MovPrestamo {
    private int idMovPrestamo;
    private Prestamo prestamo;
    private Movimiento movimiento;

    public MovPrestamo() {
    }

    public MovPrestamo(int idMovPrestamo, Prestamo prestamo, Movimiento movimiento) {
        this.idMovPrestamo = idMovPrestamo;
        this.prestamo = prestamo;
        this.movimiento = movimiento;
    }

    public int getIdMovPrestamo() {
        return idMovPrestamo;
    }

    public void setIdMovPrestamo(int idMovPrestamo) {
        this.idMovPrestamo = idMovPrestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public String toString() {
        return "MovPrestamo{" + "MovPrestamo=" + idMovPrestamo + ", prestamo=" + prestamo.getCantidad() + ", movimiento=" + movimiento.getIdMovimiento()+ '}';
    }
}
