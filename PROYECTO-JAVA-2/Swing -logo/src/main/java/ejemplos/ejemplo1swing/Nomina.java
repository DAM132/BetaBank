/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import java.time.LocalDate;

/**
 *
 * @author DAM122
 */
public class Nomina {
    private int idNomina;
    private LocalDate fechaNomina;
    private double cantidad; 
    private Usuario usuario;

    public Nomina() {
    }

    public Nomina(int idNomina, LocalDate fechaNomina, double cantidad, Usuario usuario) {
        this.idNomina = idNomina;
        this.fechaNomina = fechaNomina;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }

    public LocalDate getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(LocalDate fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Nomina{" + "idNomina=" + idNomina + ", fechaNomina=" + fechaNomina + ", cantidad=" + cantidad + ", usuario=" + usuario.toString() + '}';
    }
}
