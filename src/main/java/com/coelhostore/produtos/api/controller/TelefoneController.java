package com.coelhostore.produtos.api.controller;

import com.coelhostore.produtos.api.dto.TelefoneRequest;
import com.coelhostore.produtos.api.dto.TelefoneResponse;
import com.coelhostore.produtos.api.service.telefone.TelefoneService;
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
@RequestMapping(value = "/api/telefone/v1")
public class TelefoneController {
    private final TelefoneService telefoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TelefoneResponse>>buscarListaTelefones(){
        return  ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarListaTelefones());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<TelefoneResponse>>buscarTelefoneId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarTelefoneId(uuid));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarTelefone(@RequestBody @Valid TelefoneRequest telefoneRequest){
        telefoneService.criarTelefone(telefoneRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarTelefone(@PathVariable UUID uuid,@RequestBody @Valid TelefoneRequest telefoneRequest){
        telefoneService.atualizarTelefone(uuid,telefoneRequest);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTelefone(@PathVariable UUID uuid){
        telefoneService.deletarTelefone(uuid);
    }
}
