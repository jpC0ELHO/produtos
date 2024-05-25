package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.ProdutosVarejoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosEstoqueRepository extends JpaRepository<ProdutosVarejoEstoque, UUID> {

    Optional<ProdutosVarejoEstoque>findById(UUID uuid);

    Optional<ProdutosVarejoEstoque>findBySku(String sku);
}
