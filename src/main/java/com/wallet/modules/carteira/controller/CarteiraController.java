package com.wallet.modules.carteira.controller;

import com.wallet.modules.carteira.dto.CarteiraRequest;
import com.wallet.modules.carteira.service.CarteiraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/carteira")
public class CarteiraController {

    private final CarteiraService service;

    @PostMapping
    public void adicionarSaldo(@RequestBody @Valid CarteiraRequest request) {
        service.adicionarSaldo(request);
    }

}
