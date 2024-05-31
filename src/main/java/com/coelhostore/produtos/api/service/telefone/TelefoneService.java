package com.coelhostore.produtos.api.service.telefone;

import com.coelhostore.produtos.api.dto.TelefoneRequest;
import com.coelhostore.produtos.api.dto.TelefoneResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelefoneService {

    List<TelefoneResponse>buscarListaTelefones();
    Optional<TelefoneResponse>buscarTelefoneId(UUID uuid);
    void criarTelefone(TelefoneRequest telefoneRequest);
    void atualizarTelefone(UUID uuid,TelefoneRequest telefoneRequest);
    void deletarTelefone(UUID uuid);

}
