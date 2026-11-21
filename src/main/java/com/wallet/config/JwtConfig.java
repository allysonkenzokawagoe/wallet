package com.wallet.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${app-config.token-secret}")
    private String secret;

    @Bean
    public JWSSigner jwsSigner() throws JOSEException {
        return new MACSigner(secret);
    }

    @Bean
    public JWSVerifier jwsVerifier() throws JOSEException {
        return new MACVerifier(secret);
    }
}
