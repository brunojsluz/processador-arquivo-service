package br.com.brunojsluz.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemVenda {

    private Long codigoItem;
    private BigDecimal valorUnitario;
    private Double quantidade;
}
