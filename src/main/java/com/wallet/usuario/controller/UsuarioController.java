package com.wallet.usuario.controller;

import com.wallet.usuario.dto.UsuarioRequest;
import com.wallet.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public void save(@RequestBody @Valid UsuarioRequest request) {
        service.save(request);
    }
}
