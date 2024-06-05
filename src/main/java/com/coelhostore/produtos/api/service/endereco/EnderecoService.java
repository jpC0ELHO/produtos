package com.coelhostore.produtos.api.service.endereco;

import com.coelhostore.produtos.api.dto.EnderecoRequest;
import com.coelhostore.produtos.api.dto.EnderecoResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoService {

    List<EnderecoResponse>listaEnderecos();
    Optional<EnderecoResponse>enderecoId(UUID uuid);
    void criarEndereco(EnderecoRequest enderecoRequest);
    void atualizarEndereco(UUID uuid,EnderecoRequest enderecoRequest);
    void deletarEndereco(UUID uuid);
}
