package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.ProdutoVendaRequest;
import com.coelhostore.produtos.api.dto.ProdutoVendaResponse;
import com.coelhostore.produtos.api.service.produtoVenda.ProdutoVendaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/produto_venda/v1")
@AllArgsConstructor
public class ProdutoVendaController {
    private final ProdutoVendaService produtoVendaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutoVendaResponse>>buscarListaProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoVendaService.listaDeProdutos());
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ProdutoVendaResponse>>buscarProdutoId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(produtoVendaService.buscarProdutoId(uuid));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarProduto(@RequestBody @Valid ProdutoVendaRequest produtoVendaRequest){
        produtoVendaService.criarProduto(produtoVendaRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private void atualizarProduto(@PathVariable UUID uuid,@RequestBody @Valid ProdutoVendaRequest produtoVendaRequest){
        produtoVendaService.atualizarProduto(uuid,produtoVendaRequest);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable UUID uuid){
        produtoVendaService.deletarProduto(uuid);
    }
}
