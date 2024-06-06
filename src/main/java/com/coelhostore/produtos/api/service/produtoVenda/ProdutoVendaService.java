package com.coelhostore.produtos.api.service.produtoVenda;

import com.coelhostore.produtos.api.dto.ProdutoVendaRequest;
import com.coelhostore.produtos.api.dto.ProdutoVendaResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoVendaService {
    List<ProdutoVendaResponse>listaDeProdutos();
    Optional<ProdutoVendaResponse>buscarProdutoId(UUID uuid);
    void criarProduto(ProdutoVendaRequest produtoVendaRequest);
    void atualizarProduto(UUID uuid,ProdutoVendaRequest produtoVendaRequest);
    void deletarProduto(UUID uuid);
}
