package com.wallet.modules.carteira.dto;

import com.wallet.modules.carteira.model.Carteira;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarteiraResponse(
        Integer id,
        BigDecimal saldo
) {
    public static CarteiraResponse of(Carteira carteira) {
        return CarteiraResponse.builder()
                .id(carteira.getId())
                .saldo(carteira.getSaldo())
                .build();
    }
}
