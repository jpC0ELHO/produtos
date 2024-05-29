package com.coelhostore.produtos.api.service.entidadeCpf;

import com.coelhostore.produtos.api.dto.EntidadeCpfRequest;
import com.coelhostore.produtos.api.dto.EntidadeCpfResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntidadeCpfService {

    List<EntidadeCpfResponse>buscarListaCpf();
    Optional<EntidadeCpfResponse>entidadeCpfId(UUID uuid);
    void criarEntidade(EntidadeCpfRequest entidadeCpfRequest);
    void atualizarCpf(UUID uuid,EntidadeCpfRequest entidadeCpfRequest);
    void deletarEntidade(UUID uuid);
}
