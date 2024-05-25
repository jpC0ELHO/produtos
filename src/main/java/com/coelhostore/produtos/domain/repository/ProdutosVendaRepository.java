package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.ProdutosVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosVendaRepository extends JpaRepository<ProdutosVenda, UUID> {
    Optional<ProdutosVenda>findById(UUID uuid);

    Optional<ProdutosVenda>findBySku(String sku);
}
