package com.coelhostore.produtos.api.service.produtoEstoque;

import com.coelhostore.produtos.api.dto.ProdutosEstoqueRequest;
import com.coelhostore.produtos.api.dto.ProdutosEstoqueResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutosEstoqueService {

    List<ProdutosEstoqueResponse>buscarListaProdutos();
    Optional<ProdutosEstoqueResponse>buscarProdutoId(UUID uuid);
    void criarProduto(ProdutosEstoqueRequest produtosEstoqueRequest);
    void atualizarProduto(UUID uuid,ProdutosEstoqueRequest produtosEstoqueRequest);
    void deletarProdutos(UUID uuid);
}
