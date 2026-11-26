package com.wallet.modules.pagamento.service;

import com.wallet.modules.auth.service.AutenticacaoService;
import com.wallet.modules.pagamento.dto.PagamentoRequest;
import com.wallet.modules.carteira.model.Carteira;
import com.wallet.modules.carteira.service.CarteiraService;
import com.wallet.modules.comum.exceptions.ValidacaoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final AutenticacaoService autenticacaoService;
    private final CarteiraService carteiraService;

    @Transactional
    public void realizarPagamento(PagamentoRequest request) {
        var usuario = autenticacaoService.getUsuarioAutenticado();
        var carteiraUsuarioLogado = carteiraService.findByUsuarioId(usuario.getId());
        var carteiraDestino = carteiraService.findByUsuarioId(request.chave());
        validarSaldoCarteira(request, carteiraUsuarioLogado);

        carteiraUsuarioLogado.setSaldo(carteiraUsuarioLogado.getSaldo().min(request.valor()));
        carteiraDestino.setSaldo(carteiraDestino.getSaldo().add(request.valor()));
    }

    private void validarSaldoCarteira(PagamentoRequest request, Carteira carteiraAtual) {
        if (carteiraAtual.getSaldo().compareTo(request.valor()) < 0) {
            throw new ValidacaoException("Você não possui saldo para realizar essa transação");
        }
    }
}
