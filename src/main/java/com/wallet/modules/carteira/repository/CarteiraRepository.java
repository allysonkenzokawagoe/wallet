package com.wallet.modules.carteira.repository;

import com.wallet.modules.carteira.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
    Optional<Carteira> findByUsuarioId(Integer id);
}
