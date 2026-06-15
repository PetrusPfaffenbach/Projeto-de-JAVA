package br.edu.inatel.bancoprojeto.main;

import br.edu.inatel.bancoprojeto.model.Conta;
import br.edu.inatel.bancoprojeto.model.ContaCorrente;
import br.edu.inatel.bancoprojeto.model.ContaPoupanca;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Agora você pode instanciar a Conta diretamente
        Conta minhaConta = null;

        int opcao = 0;

        do {
    System.out.println("\n--- MENU BANCÁRIO ---");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Consultar Conta (Dados)");
            System.out.println("3. Consultar Saldo Corrente");
            System.out.println("4. Depositar");
            System.out.println("5. Sacar");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o número da conta: ");
                    String numero = scanner.nextLine();
                    
                    System.out.print("Digite o nome do titular: ");
                    String titular = scanner.nextLine();
                    
                    System.out.print("Digite o limite: R$ ");
                    double limite = scanner.nextDouble();
                    
                    System.out.print("Digite o saldo inicial: R$ ");
                    double saldoInicial = scanner.nextDouble();


                    minhaConta = new ContaCorrente(numero, titular, limite, saldoInicial);
                    System.out.println("Conta criada com sucesso, " + titular + "!");
                    break;

                case 2:
                    if (minhaConta != null) {
                        System.out.println("\n--- DADOS DA CONTA ---");
                        System.out.println("Titular: " + minhaConta.getNomeTitular());
                        System.out.println("Número da Agência/Conta: " + minhaConta.getNumeroConta());
                        System.out.println("----------------------");
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta primeiro (Opção 1).");
                    }
                    break;

                case 3:
                    if (minhaConta != null) {
                        System.out.println("Saldo da Conta Corrente: R$ " + minhaConta.getSaldo());
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta primeiro (Opção 1).");
                    }
                    break;

                case 4:
                    if (minhaConta != null) {
                        System.out.print("Digite o valor do depósito: R$ ");
                        double valorDeposito = scanner.nextDouble();
                        minhaConta.depositar(valorDeposito);
                        System.out.println("Depósito processado.");
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta primeiro (Opção 1).");
                    }
                    break;

                case 5:
                    if (minhaConta != null) {
                        System.out.print("Digite o valor do saque: R$ ");
                        double valorSaque = scanner.nextDouble();
                        minhaConta.sacar(valorSaque);
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta primeiro (Opção 1).");
                    }
                    break;

                case 6: if (minhaConta != null) {
                        System.out.println("\n⏳ Avançando o tempo em 30 dias...");
                        
                        if (minhaConta instanceof ContaPoupanca) {
                            
                            ContaPoupanca cp = (ContaPoupanca) minhaConta;
                            double rendimentoGerado = cp.aplicarRendimento();
                            System.out.println("Rendimento aplicado! Sua poupança rendeu: R$ " + rendimentoGerado);
                            
                        } else if (minhaConta instanceof ContaCorrente) {
                            
                            ContaCorrente cc = (ContaCorrente) minhaConta;
                            double imposto = cc.calcularTributos();
                            
                            cc.sacar(imposto); 
                            System.out.println("Taxa de manutenção cobrada! Valor debitado: R$ " + imposto);
                        }
                        
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta primeiro.");
                    }
                    break; 
                case 7:
                    System.out.println("Sessão encerrada. Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
