package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.ProdutosVarejoEstoque;
import com.coelhostore.produtos.domain.entities.ProdutosVarejoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosVendaRepository extends JpaRepository<ProdutosVarejoVenda, UUID> {
    Optional<ProdutosVarejoVenda>findById(UUID uuid);

    Optional<ProdutosVarejoVenda>findBySku(String sku);
}
