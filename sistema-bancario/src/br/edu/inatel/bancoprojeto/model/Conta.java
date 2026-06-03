package br.edu.inatel.bancoprojeto.model;

public abstract class Conta {
    private int numero;
    private double saldo;

    public void sacar(double quantia) {
        if(this.saldo >= quantia) {
            this.saldo -= quantia;
            System.out.println("Saque realizado com suceso");
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void depositar(double quantia) {
        if(quantia > 0) {
            this.saldo += quantia;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
