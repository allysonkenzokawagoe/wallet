package com.wallet.modules.comum.exceptions;

public record ErrorResponse(
        Integer code,
        String message
) {
}
