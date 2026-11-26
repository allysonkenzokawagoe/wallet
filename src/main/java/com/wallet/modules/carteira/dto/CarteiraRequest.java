package com.wallet.modules.carteira.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarteiraRequest(
        @NotNull
        Integer carteiraId,
        @NotNull
        BigDecimal valor
) {
}
