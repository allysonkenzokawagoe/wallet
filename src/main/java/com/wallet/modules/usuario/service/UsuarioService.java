package com.wallet.modules.usuario.service;

import com.wallet.modules.auth.dto.LoginRequest;
import com.wallet.modules.auth.service.AutenticacaoService;
import com.wallet.modules.carteira.dto.PagamentoRequest;
import com.wallet.modules.carteira.model.Carteira;
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
    private final AutenticacaoService autenticacaoService;

    public String login(LoginRequest request) {
        var usuario = findByEmail(request.email());
        if (!encoder.matches(request.password(), usuario.getSenha())) {
            throw new ValidacaoException("Senha incorreta");
        }
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

    public Usuario findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmailIgnoreCase(username).orElseThrow(() -> new NotFoundException("Usuário não encontrado com esse email!"));
    }
}
