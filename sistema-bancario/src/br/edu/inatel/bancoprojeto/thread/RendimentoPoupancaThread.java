package br.edu.inatel.bancoprojeto.thread;

import br.edu.inatel.bancoprojeto.model.ContaPoupanca;

public class RendimentoPoupancaThread implements Runnable {

    private ContaPoupanca conta;

    public RendimentoPoupancaThread(ContaPoupanca conta) {
        this.conta = conta;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000); 
                
                double rendimento = conta.aplicarRendimento();
                if (rendimento > 0) {
                    System.out.println("\n[RENDIMENTO] R$ " + String.format("%.2f", rendimento) + 
                                       " aplicado na Conta Poupança " + conta.getNumeroConta());
                }
            } catch (InterruptedException e) {
                System.out.println("[RENDIMENTO] Aplicação de rendimentos interrompida para a conta " + conta.getNumeroConta());
                break;
            }
        }
    }
}