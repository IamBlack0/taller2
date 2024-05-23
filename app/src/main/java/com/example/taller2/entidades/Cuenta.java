package com.example.taller2.entidades;

import android.os.Bundle;

public class Cuenta {

    private String Usuario;
    private String Transferencia;
    private Double Saldo;
    private Double Suma;
    private String Banco;




    public Cuenta(String usuario, String transferencia, String banco, double saldo, double suma) {
        Transferencia = transferencia;
        Saldo = saldo;
        Suma = suma;
        Usuario = usuario;
        Banco = banco;
    }



    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getTransferencia() {
        return Transferencia;
    }

    public void setTransferencia(String transferencia) {
        Transferencia = transferencia;
    }

    public Double getSuma() {
        return Suma;
    }

    public void setSuma(Double suma) {
        Suma = suma;
    }


    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }


    public String getBanco() {
        return Banco;
    }

    public void setBanco(String banco) {
        Banco = banco;
    }

    public Bundle toBundle(){
        Bundle b = new Bundle();

        b.putString("usuario", getUsuario());
        b.putString("transferencia",getTransferencia());
        b.putString("tipo", getBanco());
        b.putDouble("saldo",getSaldo());
        b.putDouble("suma",getSuma());


        return b;
    }

    public static Cuenta toClass(Bundle b) {
        return new Cuenta(
                b.getString("usuario"),
                b.getString("transferencia"),
                b.getString("tipo"),
                b.getDouble("saldo"),
                b.getDouble("suma")
        );
    }

}