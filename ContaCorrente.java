package br.edu.inatel.bancoprojeto.model;

import java.sql.SQLOutput;

public class ContaCorrente {

    private String numeroConta;
    private String nomeTitular;
    private double limite;
    private double saldo;

    // Iniciando a conta com valores
    public ContaCorrente(String numeroConta, String nomeTitular, double limite, double saldo) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.limite = limite;
        this.saldo = saldo;
    }

    // Usando getters para ler os dados
    public String getNumeroConta() {
        return this.numeroConta;
    }
    public String getNomeTitular() {
        return this.nomeTitular;
    }
    public double getLimite() {
        return this.limite;
    }
    public double getSaldo() {
        return this.saldo;
    }

    // retornando saldo disponível somado ao Limite
    public double getSaldoTotal() {
        return this.saldo + this.limite;
    }

    // Adicionando valor ao saldo
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$: " + this.saldo + " Realizado com sucesso!!");
            return true;
        }
        System.out.println("Valor do depósito inválido!");
        return false;
    }

    //Sacando valor da conta
    public boolean sacar(double valor) {
        if ( valor <= this.getSaldoTotal ()) {
            this.saldo -= valor;
            System.out.println("saque de R$: " + valor + " Realizado com sucesso!!");
            return true;
        }
        System.out.println("Saldo insuficiente!");
        return false;
    }

    //Transferindo valor
    public boolean transferir(ContaCorrente contaDestino, double valor) {
        if (this.sacar(valor)) {
            contaDestino.depositar(valor);
            System.out.println("Transferencia realizada com sucesso!!");
            return true;
        }
        System.out.println("Falha na transferência: Saldo insuficiente!");
        return false;

    }
}
