package br.com.brunojsluz;

import br.com.brunojsluz.agendador.AgendadorTarefa;

import java.util.Timer;

public class Application {

    private static final long PERIODO_AGENDAMENTO = 60000;
    private static final long ATRASO_INICIALIZACAO = 1000;

    public Application() {
        Timer timer = new Timer();
        timer.schedule(new AgendadorTarefa(), ATRASO_INICIALIZACAO, PERIODO_AGENDAMENTO);
    }

    public static void main(String[] args) {
        new Application();
    }
}
