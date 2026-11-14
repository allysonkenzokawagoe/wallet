package com.wallet.modules.carteira.service;

import com.wallet.modules.carteira.model.Carteira;
import com.wallet.modules.carteira.repository.CarteiraRepository;
import com.wallet.modules.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void save(Usuario usuario) {
        repository.save(Carteira.of(usuario));
    }

    public Carteira findByUsuarioId(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .orElse(null);
    }
}
