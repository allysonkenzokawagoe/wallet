package com.wallet.carteira.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARTEIRA")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARTEIRA")
    @SequenceGenerator(name = "SEQ_CARTEIRA", sequenceName = "SEQ_CARTEIRA", allocationSize = 1)
    private Integer id;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "FK_USUARIO", foreignKey = @ForeignKey(name = "FK_USUARIO"), referencedColumnName = "ID", nullable = false)
    private Usuario usuario;

    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    public static Carteira of(Usuario usuario) {
        return Carteira.builder()
                .saldo(BigDecimal.ZERO)
                .usuario(usuario)
                .dataCadastro(LocalDateTime.now())
                .build();
    }
}
