package com.wallet.modules.auth.service;

import com.nimbusds.jwt.SignedJWT;
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
        var token = TokenUtils.getHeaderToken();
        var jwt = SignedJWT.parse(token);
        var claims = jwt.getJWTClaimsSet().getClaims();
        return new UsuarioAutenticado(claims);
    }
}
