package br.com.brunojsluz.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioVenda {

    private Long quantidadeClientes;
    private Long quantidadeVendedor;
    private Long codigoMaiorVenda;
    private String nomeVendedorMenorVenda;

    public String toString() {
        return new StringBuilder()
                .append(quantidadeClientes)
                .append("\n")
                .append(quantidadeVendedor)
                .append("\n")
                .append(codigoMaiorVenda)
                .append("\n")
                .append(nomeVendedorMenorVenda)
                .toString();
    }
}
