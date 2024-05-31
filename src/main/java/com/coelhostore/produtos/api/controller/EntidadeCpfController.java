package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.EntidadeCpfRequest;
import com.coelhostore.produtos.api.dto.EntidadeCpfResponse;
import com.coelhostore.produtos.api.service.entidadeCpf.EntidadeCpfService;
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
@RequestMapping(value = "/api/entidade_cpf/v1")
public class EntidadeCpfController {
    private final EntidadeCpfService entidadeCpfService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntidadeCpfResponse>>buscarListaCpf(){
        return ResponseEntity.status(HttpStatus.OK).body(entidadeCpfService.buscarListaCpf());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<EntidadeCpfResponse>>buscarCpfId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(entidadeCpfService.entidadeCpfId(uuid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarEntidade(@RequestBody @Valid EntidadeCpfRequest entidadeCpfRequest){
        entidadeCpfService.criarEntidade(entidadeCpfRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEntidade(@PathVariable UUID uuid,@RequestBody @Valid EntidadeCpfRequest entidadeCpfRequest){
        entidadeCpfService.atualizarCpf(uuid,entidadeCpfRequest);
    }
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEntidade(@PathVariable UUID uuid){
        entidadeCpfService.deletarEntidade(uuid);
    }
}
