package com.wallet.comum.exceptions;

public record ErrorResponse(
        Integer code,
        String message
) {
}
