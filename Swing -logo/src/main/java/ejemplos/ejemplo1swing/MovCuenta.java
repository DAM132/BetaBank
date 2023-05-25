/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

/**
 *
 * @author DAM122
 */
public class MovCuenta {
    private int idMovCuenta;
    private Cuenta cuenta;
    private Movimiento movimiento;

    public MovCuenta() {
    }
    
    public MovCuenta(int idMovCuenta, Cuenta cuenta, Movimiento movimiento) {
        this.idMovCuenta = idMovCuenta;
        this.cuenta = cuenta;
        this.movimiento = movimiento;
    }

    public int getIdMovCuenta() {
        return idMovCuenta;
    }

    public void setIdMovCuenta(int idMovCuenta) {
        this.idMovCuenta = idMovCuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public String toString() {
        return "MovCuenta{" + "idMovCuenta=" + idMovCuenta + ", cuenta=" + cuenta.getIban() + ", movimiento=" + movimiento.getIdMovimiento() + '}';
    }
}
