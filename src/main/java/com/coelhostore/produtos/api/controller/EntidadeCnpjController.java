package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.EntidadeCnpjRequest;
import com.coelhostore.produtos.api.dto.EntidadeCnpjResponse;
import com.coelhostore.produtos.api.service.entidadeCnpj.EntidadeCnpjService;
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
@RequestMapping(value = "/api/entidade_cnpj/v1")
public class EntidadeCnpjController {

    private final EntidadeCnpjService entidadeCnpjService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntidadeCnpjResponse>>buscarListaEntidades(){
        return ResponseEntity.status(HttpStatus.OK).body(entidadeCnpjService.buscarListaCnpj());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<EntidadeCnpjResponse>>buscarEntidadeId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(entidadeCnpjService.buscarEntidadeId(uuid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarEntidadeCnpj(@RequestBody @Valid EntidadeCnpjRequest entidadeCnpjRequest){
        entidadeCnpjService.criarEntidade(entidadeCnpjRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEntidadeCnpj(@PathVariable UUID uuid, @RequestBody @Valid EntidadeCnpjRequest entidadeCnpjRequest){
        entidadeCnpjService.atualizarEntidade(uuid,entidadeCnpjRequest);
    }

}
