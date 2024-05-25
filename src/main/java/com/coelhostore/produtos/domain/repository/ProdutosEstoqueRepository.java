package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.ProdutosEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosEstoqueRepository extends JpaRepository<ProdutosEstoque, UUID> {

    Optional<ProdutosEstoque>findById(UUID uuid);

    Optional<ProdutosEstoque>findBySku(String sku);
}
