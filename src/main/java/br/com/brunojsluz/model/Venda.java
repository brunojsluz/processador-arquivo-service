package br.com.brunojsluz.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Venda {

    private Long codigoVenda;
    private String nomeVendedor;
    private List<ItemVenda> itensVenda;
    private BigDecimal valorVenda;
}
