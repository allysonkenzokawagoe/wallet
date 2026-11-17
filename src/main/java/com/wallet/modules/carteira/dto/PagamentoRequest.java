package com.wallet.modules.carteira.dto;

import java.math.BigDecimal;

public record PagamentoRequest(
        Integer chave,
        BigDecimal valor
){
}
