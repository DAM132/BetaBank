/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementacion;

/**
 *
 * @author Ginés
 */
public class Cuenta {
    private String iban;
    private String tipoCuenta;
    private double saldo;
    private double ingresos; 
    private double media;
    private Cliente cliente;

    public Cuenta() {
    }
    
    public Cuenta(String iban, String tipoCuenta, double saldo, double ingresos, double media, Cliente cliente) {
        this.iban = iban;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.ingresos = ingresos;
        this.media = media;
        this.cliente = cliente;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "iban=" + iban + ", tipoCuenta=" + tipoCuenta + ", saldo=" + saldo + ", ingresos=" + ingresos + ", media=" + media + ", cliente=" + cliente + '}';
    }
}
