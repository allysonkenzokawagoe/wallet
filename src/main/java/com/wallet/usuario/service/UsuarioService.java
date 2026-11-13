package com.wallet.usuario.service;

import com.wallet.carteira.service.CarteiraService;
import com.wallet.comum.exceptions.NotFoundException;
import com.wallet.usuario.dto.UsuarioRequest;
import com.wallet.usuario.model.Usuario;
import com.wallet.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final CarteiraService carteiraService;

    @Transactional
    public void save(UsuarioRequest request) {
        validarEmail(request);
        var usuarioSalvo = repository.save(Usuario.of(request));
        carteiraService.save(usuarioSalvo);
    }

    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));
    }

    private void validarEmail(UsuarioRequest request) {
        if (repository.existsByEmailIgnoreCase(request.email())) {
            throw new ValidationException("Esse email ja está cadastrado no sistema!");
        }
    }
}
