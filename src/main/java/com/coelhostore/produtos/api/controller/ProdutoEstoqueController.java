package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.ProdutosEstoqueRequest;
import com.coelhostore.produtos.api.dto.ProdutosEstoqueResponse;
import com.coelhostore.produtos.api.service.produtoEstoque.ProdutosEstoqueImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/produtos/estoque/v1")
public class ProdutoEstoqueController {
    private final ProdutosEstoqueImpl produtosEstoque;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutosEstoqueResponse>>buscarListaProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtosEstoque.buscarListaProdutos());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ProdutosEstoqueResponse>>buscarProdutoId(UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(produtosEstoque.buscarProdutoId(uuid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarProduto(@RequestBody @Valid ProdutosEstoqueRequest produtosEstoqueRequest){
        produtosEstoque.criarProduto(produtosEstoqueRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void atualizarProduto(@PathVariable UUID uuid,@RequestBody @Valid ProdutosEstoqueRequest produtosEstoqueRequest){
        produtosEstoque.atualizarProduto(uuid,produtosEstoqueRequest);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable UUID uuid){
        produtosEstoque.deletarProdutos(uuid);
    }
}
