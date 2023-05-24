/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.banco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ginés
 */
public class Prestamo {

    private int idPrestamo;
    private int idCliente;
    private LocalDate fechaFirma;
    private double cantidad;
    private int PeriodoMes;
    private double tipoInteres;
    private int PlazoDias;
    private EstadoPrestamo EstadoPrest;

    public Prestamo(int idPrestamo, int idCliente, LocalDate fechaFirma, double cantidad, int PeriodoMes, double tipoInteres, int PlazoDias, EstadoPrestamo EstadoPrest) {
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.fechaFirma = fechaFirma;
        this.cantidad = cantidad;
        this.PeriodoMes = PeriodoMes;
        this.tipoInteres = tipoInteres;
        this.PlazoDias = PlazoDias;
        this.EstadoPrest = EstadoPrest;
    }

    public Prestamo() {
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(LocalDate fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getPeriodoMes() {
        return PeriodoMes;
    }

    public void setPeriodoMes(int PeriodoMes) {
        this.PeriodoMes = PeriodoMes;
    }

    public double getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public int getPlazoDias() {
        return PlazoDias;
    }

    public void setPlazoDias(int PlazoDias) {
        this.PlazoDias = PlazoDias;
    }

    public EstadoPrestamo getEstadoPrest() {
        return EstadoPrest;
    }

    public void setEstadoPrest(EstadoPrestamo EstadoPrest) {
        this.EstadoPrest = EstadoPrest;
    }

    @Override
    public String toString() {
        
        DateTimeFormatter ldf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", idCliente=" + idCliente + ", fechaFirma=" + fechaFirma.format(ldf) + ", cantidad=" + cantidad + ", PeriodoMes=" + PeriodoMes + ", tipoInteres=" + tipoInteres + ", PlazoDias=" + PlazoDias + ", EstadoPrest=" + EstadoPrest + '}';
    }
    
    
    
}
