package com.wallet.modules.usuario.dto;

import com.wallet.modules.carteira.dto.CarteiraResponse;
import com.wallet.modules.usuario.model.Usuario;
import lombok.Builder;

@Builder
public record UsuarioResponse(
        Integer id,
        String nome,
        String email,
        CarteiraResponse carteira
) {
    public static UsuarioResponse of(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .carteira(CarteiraResponse.of(usuario.getCarteira()))
                .build();
    }
}
