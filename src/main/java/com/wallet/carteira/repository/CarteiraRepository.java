package com.wallet.carteira.repository;

import com.wallet.carteira.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
}
