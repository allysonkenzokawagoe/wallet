package com.wallet.modules.auth.service;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.wallet.modules.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWSSigner signer;

    @SneakyThrows
    public String gerarToken(Usuario usuario) {
        var header = new JWSHeader(JWSAlgorithm.HS256);
        var claims = new JWTClaimsSet.Builder()
                .subject(usuario.getEmail())
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .issueTime(new Date())
                .claim("id", usuario.getId())
                .claim("email", usuario.getEmail())
                .claim("nome", usuario.getNome())
                .build();

        var jwt = new SignedJWT(header, claims);
        jwt.sign(signer);

        return jwt.serialize();
    }

    @SneakyThrows
    public String validarToken(String token) {
        var jwt = SignedJWT.parse(token);
        return jwt.getJWTClaimsSet().getSubject();
    }
}
