package com.wallet.modules.auth.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.wallet.modules.auth.dto.UsuarioAutenticado;
import com.wallet.modules.comum.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    @SneakyThrows
    public UsuarioAutenticado getUsuarioAutenticado() {
        var username = TokenUtils.getHeaderToken();
        var usuario = JWTClaimsSet.parse(username).getClaims();
        return new UsuarioAutenticado(usuario);
    }
}
