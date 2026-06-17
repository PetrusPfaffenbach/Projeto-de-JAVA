package br.edu.inatel.bancoprojeto.model;

import br.edu.inatel.bancoprojeto.exception.SaldoInsuficienteException;

public abstract class Conta {

    protected String numeroConta;
    protected String nomeTitular;
    protected double limite;
    protected double saldo;
    private int numero;

    public void sacar(double quantia) throws SaldoInsuficienteException {
    if(this.saldo >= quantia) {
        this.saldo -= quantia;
        System.out.println("Saque realizado com sucesso!");
    } else {
        // Lança a tua exceção personalizada
        throw new SaldoInsuficienteException("Saldo insuficiente para efetuar o saque de R$ " + quantia);
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
