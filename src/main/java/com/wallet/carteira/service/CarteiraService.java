package com.wallet.carteira.service;

import com.wallet.carteira.model.Carteira;
import com.wallet.carteira.repository.CarteiraRepository;
import com.wallet.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void save(Usuario usuario) {
        repository.save(Carteira.of(usuario));
    }
}
