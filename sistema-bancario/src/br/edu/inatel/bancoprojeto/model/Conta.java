package br.edu.inatel.bancoprojeto.model;

public abstract class Conta {

    protected String numeroConta;
    protected String nomeTitular;
    protected double limite;
    protected double saldo;
    private int numero;

    public boolean sacar(double quantia) {
        if(this.saldo >= quantia) {
            this.saldo -= quantia;
            System.out.println("Saque realizado com suceso");
            return true;
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }
    }

    public boolean depositar(double quantia) {
        if(quantia > 0) {
            this.saldo += quantia;
            return true;
        }
        return false;
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

    public String getNomeTitular() {
    return nomeTitular;
    }

    public String getNumeroConta() {
    return numeroConta;
    }

}
