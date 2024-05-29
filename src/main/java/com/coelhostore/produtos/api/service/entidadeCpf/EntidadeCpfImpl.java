package com.coelhostore.produtos.api.service.entidadeCpf;

import com.coelhostore.produtos.api.dto.EntidadeCpfRequest;
import com.coelhostore.produtos.api.dto.EntidadeCpfResponse;
import com.coelhostore.produtos.domain.exceptions.EntidadeCpfNotFoundException;
import com.coelhostore.produtos.domain.repository.EntidadeCpfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class EntidadeCpfImpl implements EntidadeCpfService{

    private final EntidadeCpfRepository entidadeCpfRepository;

    @Override
    public List<EntidadeCpfResponse> buscarListaCpf() {

        try {
            var buscarEntidadeCpf=entidadeCpfRepository.findAll();
            if (buscarEntidadeCpf==null){
                throw new EntidadeCpfNotFoundException("Lista não encontrada, pode não haver dados registrados!");
            }else {
                log.info("Lista encontrada!");
                return buscarEntidadeCpf.stream()
                        .map(EntidadeCpfResponse::toResponse)
                        .toList();
            }
        }catch (RuntimeException e){
            log.info("Error:{} ...",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<EntidadeCpfResponse> entidadeCpfId(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void criarEntidade(EntidadeCpfRequest entidadeCpfRequest) {

    }

    @Override
    public void atualizarCpf(UUID uuid, EntidadeCpfRequest entidadeCpfRequest) {

    }

    @Override
    public void deletarEntidade(UUID uuid) {

    }
}
