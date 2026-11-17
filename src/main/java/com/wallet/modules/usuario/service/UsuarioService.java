package com.wallet.modules.usuario.service;

import com.wallet.modules.carteira.service.CarteiraService;
import com.wallet.modules.comum.exceptions.NotFoundException;
import com.wallet.modules.comum.exceptions.ValidacaoException;
import com.wallet.modules.auth.service.TokenService;
import com.wallet.modules.usuario.dto.UsuarioRequest;
import com.wallet.modules.usuario.dto.UsuarioResponse;
import com.wallet.modules.usuario.model.Usuario;
import com.wallet.modules.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final CarteiraService carteiraService;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    public String login(String email) {
        var usuario = findByEmail(email);
        return tokenService.gerarToken(usuario);
    }

    @Transactional
    public void save(UsuarioRequest request) {
        validarEmail(request);
        var senhaCriptografada = encoder.encode(request.senha());
        var usuarioSalvo = repository.save(Usuario.of(request, senhaCriptografada));
        carteiraService.save(usuarioSalvo);
    }

    public UsuarioResponse getResponseById(Integer id) {
        var carteira = carteiraService.findByUsuarioId(id);
        return UsuarioResponse.of(findById(id), carteira);
    }

    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));
    }

    private void validarEmail(UsuarioRequest request) {
        if (repository.existsByEmailIgnoreCase(request.email())) {
            throw new ValidacaoException("Esse email ja está cadastrado no sistema!");
        }
    }

    private Usuario findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmailIgnoreCase(username).orElseThrow(() -> new NotFoundException("Usuário não encontrado com esse email!"));
    }
}
