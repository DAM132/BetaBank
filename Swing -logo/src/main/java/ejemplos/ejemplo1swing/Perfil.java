/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos.ejemplo1swing;

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
    private TipoPerfilEnum tipoPerfil;

    public Perfil(int idPerfil, String usuario, String contrasena, EstadoCivil estadoCiv, EstadoLaboral estadolab, boolean moroso, TipoPerfilEnum tipoPerfil) {
        this.idPerfil = idPerfil;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estadoCiv = estadoCiv;
        this.estadolab = estadolab;
        this.moroso = moroso;
        this.tipoPerfil = tipoPerfil;
    }

    public Perfil() {
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

    public TipoPerfilEnum getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfilEnum tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    @Override
    public String toString() {
        return "Perfil{" + "idPerfil=" + idPerfil + ", usuario=" + usuario + ", contrasena=" + contrasena + ", estadoCiv=" + estadoCiv + ", estadolab=" + estadolab + ", moroso=" + moroso + ", tipoPerfil=" + tipoPerfil + '}';
    }

 
    
    
    
    
    
}
