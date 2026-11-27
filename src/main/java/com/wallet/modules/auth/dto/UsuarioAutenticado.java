package com.wallet.modules.auth.dto;

import com.wallet.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAutenticado {

    private Integer id;
    private String email;
    private String nome;

    public UsuarioAutenticado(Map<String, Object> attributes) {
        this.id = Math.toIntExact((Long) attributes.get("id"));
        this.email = (String) attributes.get("email");
        this.nome = (String) attributes.get("nome");
    }

    public UsuarioAutenticado(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
    }
}
