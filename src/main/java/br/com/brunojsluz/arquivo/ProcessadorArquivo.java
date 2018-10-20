package br.com.brunojsluz.arquivo;

import br.com.brunojsluz.Util.DiretorioUtil;
import br.com.brunojsluz.mapper.VendaMapper;
import br.com.brunojsluz.model.RelatorioVenda;
import br.com.brunojsluz.model.Venda;
import br.com.brunojsluz.relatorio.GeradorRelatorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessadorArquivo {

    private static final String IDENTIFICADOR_DADOS_VENDEDOR = "001";
    private static final String IDENTIFICADOR_DADOS_CLIENTE = "002";
    private static final String IDENTIFICADOR_DADOS_VENDA = "003";

    public void processar() {
        try {
            DiretorioUtil.obterListaArquivos()
                    .forEach(path -> tratarArquivoLido(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tratarArquivoLido(Path path) {
        try {
            List<Venda> vendas = Files.lines(path)
                    .filter(line -> line.startsWith(IDENTIFICADOR_DADOS_VENDA))
                    .map(venda -> VendaMapper.mapFrom(venda))
                    .collect(Collectors.toList());

            Venda maiorVenda = obterMaiorVenda(vendas);
            Venda menorVenda = obterMenorVenda(vendas);

            RelatorioVenda relatorio = RelatorioVenda.builder()
                    .quantidadeClientes(obterQuantidadeRegistros(path, IDENTIFICADOR_DADOS_CLIENTE))
                    .quantidadeVendedor(obterQuantidadeRegistros(path, IDENTIFICADOR_DADOS_VENDEDOR))
                    .codigoMaiorVenda(maiorVenda.getCodigoVenda())
                    .nomeVendedorMenorVenda(menorVenda.getNomeVendedor())
                    .build();

            GeradorRelatorio.gerarRelatorio(relatorio, path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Venda obterMaiorVenda(List<Venda> vendas) {
        return vendas.stream()
                .max(Comparator.comparing(Venda::getValorVenda))
                .get();
    }

    private Venda obterMenorVenda(List<Venda> vendas) {
        return vendas.stream()
                .min(Comparator.comparing(Venda::getValorVenda))
                .get();
    }

    private Long obterQuantidadeRegistros(Path path, String filtro) throws IOException {
        return Files.lines(path)
                .filter(line -> line.startsWith(filtro))
                .count();
    }
}
