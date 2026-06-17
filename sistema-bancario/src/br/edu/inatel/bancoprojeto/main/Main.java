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
        ContaPoupanca meuInvestimento = null;

        int opcao = 0;

        do {
    System.out.println("\n--- MENU BANCÁRIO ---");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Consultar Conta (Dados)");
            System.out.println("3. Consultar Saldo Corrente");
            System.out.println("4. Depositar");
            System.out.println("5. Sacar");
            System.out.println("6. Investimentos (Poupança)");
            System.out.println("7. Sair");
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

                case 6: 
                    meuInvestimento = exibirMenuInvestimentos(scanner, meuInvestimento);
                    break;
                    
                case 7:
                    System.out.println("Sessão encerrada. Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 7);

        scanner.close();
    }

    private static ContaPoupanca exibirMenuInvestimentos(Scanner scanner, ContaPoupanca investimento) {
        int opcaoInv = 0;

        do {
            System.out.println("\n--- MENU INVESTIMENTOS (POUPANÇA) ---");
            System.out.println("1. Criar Conta Poupança");
            System.out.println("2. Consultar Dados do Investimento");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Depositar na Poupança");
            System.out.println("5. Sacar da Poupança");
            System.out.println("6. Aplicar Rendimento (30 dias)");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcaoInv = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoInv) {
                case 1:
                    System.out.print("Digite o número da conta poupança: ");
                    String numero = scanner.nextLine();
                    
                    System.out.print("Digite o nome do titular: ");
                    String titular = scanner.nextLine();
                    
                    System.out.print("Digite o saldo inicial: R$ ");
                    double saldoInicial = scanner.nextDouble();
                    
                    System.out.print("Digite a taxa de rendimento (ex: 0.005 para 0.5%): ");
                    double taxaRendimento = scanner.nextDouble();
                    scanner.nextLine();

                    investimento = new ContaPoupanca(numero, titular, saldoInicial, taxaRendimento);
                    System.out.println("Conta Poupança criada com sucesso, " + titular + "!");
                    break;

                case 2:
                    if (investimento != null) {
                        System.out.println("\n--- DADOS DO INVESTIMENTO ---");
                        System.out.println("Titular: " + investimento.getNomeTitular());
                        System.out.println("Número da Conta: " + investimento.getNumeroConta());
                        System.out.println("Taxa de Rendimento: " + (investimento.getTaxaRendimento() * 100) + "%");
                        System.out.println("----------------------");
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta poupança primeiro (Opção 1).");
                    }
                    break;

                case 3:
                    if (investimento != null) {
                        System.out.println("Saldo da Poupança: R$ " + investimento.getSaldo());
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta poupança primeiro (Opção 1).");
                    }
                    break;

                case 4:
                    if (investimento != null) {
                        System.out.print("Digite o valor do depósito: R$ ");
                        double valorDeposito = scanner.nextDouble();
                        investimento.depositar(valorDeposito);
                        System.out.println("Depósito processado.");
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta poupança primeiro (Opção 1).");
                    }
                    break;

                case 5:
                    if (investimento != null) {
                        System.out.print("Digite o valor do saque: R$ ");
                        double valorSaque = scanner.nextDouble();
                        investimento.sacar(valorSaque);
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta poupança primeiro (Opção 1).");
                    }
                    break;

                case 6:
                    if (investimento != null) {
                        System.out.println("Avançando o tempo em 30 dias...");
                        double rendimentoGerado = investimento.aplicarRendimento();
                        System.out.println("Rendimento aplicado! Sua poupança rendeu: R$ " + rendimentoGerado);
                    } else {
                        System.out.println("Erro: Você precisa criar uma conta poupança primeiro (Opção 1).");
                    }
                    break;

                case 7:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcaoInv != 7);
        
        return investimento;
    }
}
