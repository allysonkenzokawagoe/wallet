package com.wallet.modules.usuario.controller;

import com.wallet.modules.auth.dto.LoginRequest;
import com.wallet.modules.usuario.dto.UsuarioRequest;
import com.wallet.modules.usuario.dto.UsuarioResponse;
import com.wallet.modules.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }

    @PostMapping
    public void save(@RequestBody @Valid UsuarioRequest request) {
        service.save(request);
    }

    @GetMapping("{id}")
    public UsuarioResponse getResponseById(@PathVariable Integer id) {
        return service.getResponseById(id);
    }
}
