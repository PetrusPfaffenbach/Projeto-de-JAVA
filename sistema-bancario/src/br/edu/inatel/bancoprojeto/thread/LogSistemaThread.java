package br.edu.inatel.bancoprojeto.thread;

public class LogSistemaThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                // System.out.println("[LOG] Sistema rodando normalmente... Verificação de rotina concluída.");
                Thread.sleep(15000); 
            } catch (InterruptedException e) {
                // System.out.println("[LOG] Thread de log do sistema foi interrompida.");
                break;
            }
        }
    }
}

