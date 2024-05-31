package com.coelhostore.produtos.api.service.entidadeCnpj;


import com.coelhostore.produtos.api.dto.EntidadeCnpjRequest;
import com.coelhostore.produtos.api.dto.EntidadeCnpjResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntidadeCnpjService {

    List<EntidadeCnpjResponse> buscarListaCnpj();
    Optional<EntidadeCnpjResponse>buscarEntidadeId(UUID uuid);
    void criarEntidade(EntidadeCnpjRequest entidadeCnpRequest);
    void atualizarEntidade(UUID uuid,EntidadeCnpjRequest entidadeCnpjRequest);
    void deletarEntidade(UUID uuid);
}
