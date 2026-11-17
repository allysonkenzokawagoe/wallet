package com.wallet.modules.auth.dto;

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
        this.id = (Integer) attributes.get("id");
        this.email = (String) attributes.get("email");
        this.nome = (String) attributes.get("nome");
    }
}
