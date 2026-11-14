package com.wallet.modules.token.service;

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

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWSSigner signer;
    private final JWSVerifier jwsVerifier;

    @SneakyThrows
    public String gerarToken(String email) {
        var header = new JWSHeader(JWSAlgorithm.HS256);
        var claims = new JWTClaimsSet.Builder()
                .subject(email)
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .issueTime(new Date())
                .build();

        var jwt = new SignedJWT(header, claims);
        jwt.sign(signer);

        return jwt.serialize();
    }
}
