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
public class Movimiento {
    private int idMovimiento;
    private double cantidad;
    private String concepto;
    private String emisor;
    private String destinatario;
   

    public Movimiento(int idMovimiento, double cantidad, String concepto, String emisor, String destinatario) {
        this.idMovimiento = idMovimiento;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.emisor = emisor;
        this.destinatario = destinatario;
     
    }

    public Movimiento() {
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    
    @Override
    public String toString() {
        
          DateTimeFormatter ldf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Movimiento{" + "idMovimiento=" + idMovimiento + ", cantidad=" + cantidad + ", concepto=" + concepto + ", emisor=" + emisor + ", destinatario=" + destinatario;
    }
    
    
    
}
