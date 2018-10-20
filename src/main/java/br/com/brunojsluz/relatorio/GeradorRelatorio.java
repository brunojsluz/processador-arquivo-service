package br.com.brunojsluz.relatorio;

import br.com.brunojsluz.Util.DiretorioUtil;
import br.com.brunojsluz.model.RelatorioVenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeradorRelatorio {

    private static final String SUFIXO_NOME_RELATORIO = ".done.dat";

    public static void gerarRelatorio(RelatorioVenda relatorioVenda, Path path) throws IOException {

        String nomeArquivoEntrada = path.getFileName().toString().replace(".dat", "");

        String nomeArquivoSaida = DiretorioUtil.obterDiretorioSaida()
                .concat(nomeArquivoEntrada)
                .concat(SUFIXO_NOME_RELATORIO);

        Files.write(Paths.get(nomeArquivoSaida), relatorioVenda.toString().getBytes());

        if(Files.exists(path)) {
            Files.delete(path);
        }
    }
}
