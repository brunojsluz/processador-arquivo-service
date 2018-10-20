package br.com.brunojsluz.mapper;

import br.com.brunojsluz.model.ItemVenda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemVendaMapper {

    public static List<ItemVenda> mapFrom(String itensVenda) {
        String[] itens = itensVenda
                .replace("[", "")
                .replace("]", "")
                .split(",");

        return Arrays.stream(itens)
                .map(item -> obterItemVenda(item))
                .collect(Collectors.toList());
    }

    private static ItemVenda obterItemVenda(String item) {
        String[] itemSplit = item.split("-");
        return ItemVenda.builder()
                .codigoItem(obterCodigoItem(itemSplit[0]))
                .quantidade(obterQuantidade(itemSplit[1]))
                .valorUnitario(obterValor(itemSplit[2]))
                .build();
    }

    private static Long obterCodigoItem(String codigo) {
        return Optional.of(codigo)
                .map(cod -> Long.valueOf(cod))
                .orElse(null);
    }

    private static Double obterQuantidade(String quantidade) {
        return Optional.of(quantidade)
                .map(qtde -> Double.valueOf(qtde))
                .orElse(Double.valueOf("0.0"));
    }

    private static BigDecimal obterValor(String valor) {
        return Optional.of(valor)
                .map(vlr -> new BigDecimal(vlr))
                .orElse(BigDecimal.ZERO);
    }
}
