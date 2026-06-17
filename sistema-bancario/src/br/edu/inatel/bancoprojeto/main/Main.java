package br.edu.inatel.bancoprojeto.main;

import br.edu.inatel.bancoprojeto.exception.ContaNaoEncontradaException;
import br.edu.inatel.bancoprojeto.exception.SaldoInsuficienteException;
import br.edu.inatel.bancoprojeto.model.Conta;
import br.edu.inatel.bancoprojeto.model.ContaCorrente;
import br.edu.inatel.bancoprojeto.model.ContaPoupanca;
import br.edu.inatel.bancoprojeto.thread.LogSistemaThread;
import br.edu.inatel.bancoprojeto.thread.RendimentoPoupancaThread;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {    
        LogSistemaThread logThread = new LogSistemaThread();
        logThread.start();

        Scanner scanner = new Scanner(System.in);

        
        ContaCorrente contaCorrente = null;
        ContaPoupanca contaPoupanca = null;
        
        int opcao = 0;

        do {
            System.out.println("\n--- MENU BANCÁRIO ---");
            System.out.println("1. Criar Conta");
            System.out.println("2. Consultar Conta (Dados)");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Depositar");
            System.out.println("5. Sacar");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                Conta contaAlvo = null; 

                switch (opcao) {
                    case 1:
                        System.out.println("Qual tipo de conta deseja criar?");
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Poupança");
                        System.out.print("Escolha: ");
                        int tipoConta = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Digite o número da conta: ");
                        String numero = scanner.nextLine();
                        System.out.print("Digite o nome do titular: ");
                        String titular = scanner.nextLine();
                        System.out.print("Digite o saldo inicial: R$ ");
                        double saldoInicial = scanner.nextDouble();

                        if (tipoConta == 1) {
                            System.out.print("Digite o limite: R$ ");
                            double limite = scanner.nextDouble();
                            
                            contaCorrente = new ContaCorrente(numero, titular, limite, saldoInicial);
                            System.out.println("Conta Corrente criada com sucesso, " + titular + "!");
                            
                        } else if (tipoConta == 2) {
                            System.out.print("Digite a taxa de rendimento (ex: 0.05 para 5%): ");
                            double taxa = scanner.nextDouble();

                            contaPoupanca = new ContaPoupanca(numero, titular, saldoInicial, taxa);
                            System.out.println("Conta Poupança criada com sucesso, " + titular + "!");

                            Thread threadPoupanca = new Thread(new RendimentoPoupancaThread(contaPoupanca));
                            threadPoupanca.start();
                            
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        break;

                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        System.out.println("Em qual conta deseja realizar a operação?");
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Poupança");
                        System.out.print("Escolha: ");
                        int escolhaConta = scanner.nextInt();
                        
                        if (escolhaConta == 1) {
                            contaAlvo = contaCorrente;
                        } else if (escolhaConta == 2) {
                            contaAlvo = contaPoupanca;
                        } else {
                            System.out.println("Opção inválida!");
                            break;
                        }
                        validarConta(contaAlvo); 
                        if (opcao == 2) {
                            System.out.println("\n--- DADOS DA CONTA ---");
                            System.out.println("Titular: " + contaAlvo.getNomeTitular());
                            System.out.println("Número da Agência/Conta: " + contaAlvo.getNumeroConta());
                            System.out.println("----------------------");
                        } else if (opcao == 3) {
                            System.out.println("Saldo da Conta: R$ " + contaAlvo.getSaldo());
                        } else if (opcao == 4) {
                            System.out.print("Digite o valor do depósito: R$ ");
                            double valorDeposito = scanner.nextDouble();
                            contaAlvo.depositar(valorDeposito);
                        } else if (opcao == 5) {
                            System.out.print("Digite o valor do saque: R$ ");
                            double valorSaque = scanner.nextDouble();
                            contaAlvo.sacar(valorSaque);
                        }
                        break;

                    case 6:
                        System.out.println("Sessão encerrada. Obrigado!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (ContaNaoEncontradaException | SaldoInsuficienteException e) {
                System.out.println("\n[ALERTA DO SISTEMA] " + e.getMessage());
            } catch (java.util.InputMismatchException e) {
                System.out.println("\n[ALERTA DO SISTEMA] Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine(); 
            }

        } while (opcao != 6);

        scanner.close();
        System.exit(0); 
    }
    
    private static void validarConta(Conta conta) throws ContaNaoEncontradaException {
        if (conta == null) {
            throw new ContaNaoEncontradaException("Operação cancelada: O tipo de conta escolhido ainda não foi criado. Use a opção 1 primeiro.");
        }
    }
}