package br.edu.inatel.bancoprojeto.model;

import br.edu.inatel.bancoprojeto.exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta implements Tributavel {

    @Override
    public double calcularTributos() {
        // Exemplo: O imposto é 1% do saldo da conta corrente
        return this.saldo * 0.01;
    }

    // Iniciando a conta com valores
    public ContaCorrente(String numeroConta, String nomeTitular, double saldo) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
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
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= this.getSaldoTotal()) {
            this.saldo -= valor;
            System.out.println("Saque de R$: " + valor + " realizado com sucesso!!");
        } else {
            throw new SaldoInsuficienteException("Saldo e limite insuficientes para o saque de R$ " + valor);
        }
    }


    public boolean transferir(ContaCorrente contaDestino, double valor) throws SaldoInsuficienteException {
        this.sacar(valor); 
        contaDestino.depositar(valor);
        System.out.println("Transferência realizada com sucesso!!");
        return true;
    }
}
