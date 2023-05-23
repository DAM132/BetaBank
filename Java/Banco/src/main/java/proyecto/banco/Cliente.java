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
public class Cliente {
    private int idCliente;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private LocalDate fnac;
    private String domicilio;
    private String localidad;
    private Sexo sexo;
    private boolean casado;
    private double mediaIngreso;
    private boolean activo;

    public Cliente(int idCliente, String dni, String nombre, String apellidos, LocalDate fnac, String domicilio, String localidad, Sexo sexo, boolean casado, double mediaIngreso, boolean activo) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fnac = fnac;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.sexo = sexo;
        this.casado = casado;
        this.mediaIngreso = mediaIngreso;
        this.activo = activo;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFnac() {
        return fnac;
    }

    public void setFnac(LocalDate fnac) {
        this.fnac = fnac;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public double getMediaIngreso() {
        return mediaIngreso;
    }

    public void setMediaIngreso(double mediaIngreso) {
        this.mediaIngreso = mediaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        DateTimeFormatter ldf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String act;
        if (activo) {
            act="Si";
        }else{
            act="No";
        }
        return "Cliente{" + "idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fnac=" + fnac.format(ldf) + ", domicilio=" + domicilio + ", localidad=" + localidad + ", sexo=" + sexo + ", casado=" + casado + ", mediaIngreso=" + mediaIngreso + ", activo=" + act + '}';
    }
    
    
}
