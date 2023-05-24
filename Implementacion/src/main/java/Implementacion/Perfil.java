/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

/**
 *
 * @author Ginés
 */
public class Perfil {
    private int idPerfil;
    private String usuario;
    private String contrasena;
    private EstadoCivil estadoCiv;
    private EstadoLaboral estadolab;
    private boolean moroso;
    private int idPareja; 

    public Perfil() {
    }
    // encaso de que no este casado se guarda -1
    public Perfil(int idPerfil, String usuario, String contrasena, EstadoCivil estadoCiv, EstadoLaboral estadolab, boolean moroso, int idPareja) {
        this.idPerfil = idPerfil;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estadoCiv = estadoCiv;
        this.estadolab = estadolab;
        this.moroso = moroso;
        if (EstadoCivil.CASADO==estadoCiv) {
            this.idPareja = idPareja;
        }else{
            this.idPareja = -1;
        }
        
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public EstadoCivil getEstadoCiv() {
        return estadoCiv;
    }

    public void setEstadoCiv(EstadoCivil estadoCiv) {
        this.estadoCiv = estadoCiv;
    }

    public EstadoLaboral getEstadolab() {
        return estadolab;
    }

    public void setEstadolab(EstadoLaboral estadolab) {
        this.estadolab = estadolab;
    }

    public boolean isMoroso() {
        return moroso;
    }

    public void setMoroso(boolean moroso) {
        this.moroso = moroso;
    }

    public int getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(int idPareja) {
        this.idPareja = idPareja;
    }

    @Override
    public String toString() {
        String act;
        if (moroso) {
            act="Si";
        }else{
            act="No";
        }
        return "Perfil{" + "idPerfil=" + idPerfil + ", usuario=" + usuario + ", contrasena=" + contrasena + ", estadoCiv=" + estadoCiv + ", estadolab=" + estadolab + ", moroso=" + act + ", idPareja=" + idPareja + '}';
    }
    
    
}
