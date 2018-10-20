package br.com.brunojsluz.mapper;

import br.com.brunojsluz.model.ItemVenda;
import br.com.brunojsluz.model.Venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaMapper {
    public static Venda mapFrom(String venda) {
        String[] camposVenda = venda.split("ç");

        return Venda.builder()
                .codigoVenda(obterCodigoVenda(camposVenda[1]))
                .itensVenda(obterItensVenda(camposVenda[2]))
                .nomeVendedor(obterNomeVendedor(camposVenda[3]))
                .valorVenda(obterValorVenda(camposVenda[2]))
                .build();
    }

    private static Long obterCodigoVenda(String codigo) {
        return Optional.of(codigo)
                .map(cod -> Long.valueOf(cod))
                .orElse(null);
    }

    private static String obterNomeVendedor(String nome) {
        return Optional.of(nome)
                .orElse(new String());
    }

    private static List<ItemVenda> obterItensVenda(String itensVenda) {
        return Optional.of(itensVenda)
                .map(itens -> ItemVendaMapper.mapFrom(itens))
                .orElse(new ArrayList<>());
    }

    private static BigDecimal obterValorVenda(String itens) {
        List<ItemVenda> itensVenda = obterItensVenda(itens);
        return itensVenda.stream().
                map(itemVenda -> obterValorTotalUnitario(itemVenda))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal obterValorTotalUnitario(ItemVenda itemVenda) {
        return itemVenda.getValorUnitario().multiply(BigDecimal.valueOf(itemVenda.getQuantidade()))
;    }
}
