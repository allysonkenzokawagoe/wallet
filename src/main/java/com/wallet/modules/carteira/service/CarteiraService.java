package com.wallet.modules.carteira.service;

import com.wallet.modules.carteira.dto.CarteiraRequest;
import com.wallet.modules.carteira.model.Carteira;
import com.wallet.modules.carteira.repository.CarteiraRepository;
import com.wallet.modules.comum.exceptions.NotFoundException;
import com.wallet.modules.usuario.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void save(Carteira carteira) {
        repository.save(carteira);
    }

    public Carteira findByUsuarioId(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .orElse(null);
    }

    @Transactional
    public void adicionarSaldo(CarteiraRequest request) {
        var carteira = getById(request.carteiraId());

        carteira.setSaldo(carteira.getSaldo().add(request.valor()));
        repository.save(carteira);
    }

    public Carteira getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Carteira não encontrada"));
    }
}
