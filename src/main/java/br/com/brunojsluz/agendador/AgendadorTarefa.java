package br.com.brunojsluz.agendador;

import br.com.brunojsluz.arquivo.ProcessadorArquivo;

import java.util.TimerTask;

public class AgendadorTarefa extends TimerTask {

    @Override
    public void run() {
        new ProcessadorArquivo().processar();
    }
}
