package com.wallet.modules.carteira.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PagamentoRequest(
        @NotNull
        Integer chave,
        @NotNull
        BigDecimal valor
){
}
