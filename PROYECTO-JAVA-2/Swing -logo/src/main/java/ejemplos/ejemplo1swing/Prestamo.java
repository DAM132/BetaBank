/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ginés
 */
public class Prestamo {

    private int idPrestamo;
    private Usuario usuario;
    private LocalDate fechaFirma;
    private double cantidad;
    private int periodoMes;
    private double tipoInteres;
    private int plazoDias;
    private EstadoPrestamo estadoPrest;

    public Prestamo(Usuario idUsuario, LocalDate fechaFirma, double cantidad, int PeriodoMes, double tipoInteres, int PlazoDias, EstadoPrestamo EstadoPrest) {
        this.usuario = idUsuario;
        this.fechaFirma = fechaFirma;
        this.cantidad = cantidad;
        this.periodoMes = PeriodoMes;
        this.tipoInteres = tipoInteres;
        this.plazoDias = PlazoDias;
        this.estadoPrest = EstadoPrest;
    }

    public Prestamo(int idPrestamo, Usuario idUsuario, LocalDate fechaFirma, double cantidad, int PeriodoMes, double tipoInteres, int PlazoDias, EstadoPrestamo EstadoPrest) {
        this.idPrestamo = idPrestamo;
        this.usuario = idUsuario;
        this.fechaFirma = fechaFirma;
        this.cantidad = cantidad;
        this.periodoMes = PeriodoMes;
        this.tipoInteres = tipoInteres;
        this.plazoDias = PlazoDias;
        this.estadoPrest = EstadoPrest;
    }

    public Prestamo() {
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        return periodoMes;
    }

    public void setPeriodoMes(int PeriodoMes) {
        this.periodoMes = PeriodoMes;
    }

    public double getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int PlazoDias) {
        this.plazoDias = PlazoDias;
    }

    public EstadoPrestamo getEstadoPrest() {
        return estadoPrest;
    }

    public void setEstadoPrest(EstadoPrestamo EstadoPrest) {
        this.estadoPrest = EstadoPrest;
    }
    
    @Override
    public String toString() {
        
        DateTimeFormatter ldf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", idUsuario=" + usuario + ", fechaFirma=" + fechaFirma.format(ldf) + ", cantidad=" + cantidad + ", PeriodoMes=" + periodoMes + ", tipoInteres=" + tipoInteres + ", PlazoDias=" + plazoDias + ", EstadoPrest=" + estadoPrest +'}';
    }
    
    
    
}
