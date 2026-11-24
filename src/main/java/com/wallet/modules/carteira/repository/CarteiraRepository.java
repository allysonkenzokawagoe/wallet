package com.wallet.modules.carteira.repository;

import com.wallet.modules.carteira.model.Carteira;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Carteira> findByUsuarioId(Integer id);
}
