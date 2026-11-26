package com.wallet.modules.pagamento.controller;

import com.wallet.modules.carteira.dto.PagamentoRequest;
import com.wallet.modules.pagamento.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/pagamento")
public class PagamentoController {

    private final PagamentoService service;

    @PostMapping
    public void realizarPagamento(@RequestBody @Valid PagamentoRequest request) {
        service.realizarPagamento(request);
    }
}
