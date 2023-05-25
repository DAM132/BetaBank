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
public class Usuario {
    private int idUsuario;
    private Perfil perfil;
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
    private int idPareja;

    public Usuario(int idUsuario, Perfil perfil, String dni, String nombre, String apellidos, String telefono, LocalDate fnac, String domicilio, String localidad, Sexo sexo, boolean casado, double mediaIngreso, boolean activo, int idPareja) {
        this.idUsuario = idUsuario;
        this.perfil = perfil;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fnac = fnac;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.sexo = sexo;
        this.casado = casado;
        this.mediaIngreso = mediaIngreso;
        this.activo = activo;
        this.idPareja = idPareja;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public int getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(int idPareja) {
        this.idPareja = idPareja;
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
        return "Cliente{" + "idUsuario=" + idUsuario +", perfil" +perfil.getIdPerfil()+ ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono +", fnac=" + fnac.format(ldf) + ", domicilio=" + domicilio + ", localidad=" + localidad + ", sexo=" + sexo + ", casado=" + casado + ", mediaIngreso=" + mediaIngreso + ", activo=" + act + '}';
    }
    
    
}
