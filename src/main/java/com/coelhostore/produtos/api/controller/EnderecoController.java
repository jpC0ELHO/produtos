package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.EnderecoRequest;
import com.coelhostore.produtos.api.dto.EnderecoResponse;
import com.coelhostore.produtos.api.service.endereco.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/endereco/v1")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnderecoResponse>>buscarLista(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.listaEnderecos());
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<EnderecoResponse>>buscarEndereco(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.enderecoId(uuid));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarEnderco(@RequestBody @Valid EnderecoRequest enderecoRequest){
        enderecoService.criarEndereco(enderecoRequest);
    }
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEndereco(@PathVariable UUID uuid,@RequestBody @Valid EnderecoRequest enderecoRequest){
        enderecoService.atualizarEndereco(uuid,enderecoRequest);
    }
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEndereco(@PathVariable UUID uuid){
        enderecoService.deletarEndereco(uuid);
    }
}
