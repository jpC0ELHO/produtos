package com.coelhostore.produtos.api.service.entidadeCpf;

import com.coelhostore.produtos.api.dto.EntidadeCpfRequest;
import com.coelhostore.produtos.api.dto.EntidadeCpfResponse;
import com.coelhostore.produtos.domain.exceptions.EntidadeCnpjNotFoundException;
import com.coelhostore.produtos.domain.exceptions.EntidadeCpfNotFoundException;
import com.coelhostore.produtos.domain.repository.EntidadeCpfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.EntidadeCpfRequest.toEntidade;

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
        var buscarEntidadeCpf=entidadeCpfRepository.findById(uuid);
        if (buscarEntidadeCpf.isEmpty()){
            log.warn("Pessoa fisica nao encontrada! ID: ",uuid);
            throw new EntidadeCpfNotFoundException("Nao encontrado!");
        }
        return buscarEntidadeCpf.map(EntidadeCpfResponse::toResponse);
    }

    @Override
    public void criarEntidade(EntidadeCpfRequest entidadeCpfRequest) {
        var buscarEntidadeCpf=entidadeCpfRepository.findByCpf(entidadeCpfRequest.cpf());
        if (buscarEntidadeCpf.isPresent()){
            log.info("Entidade com cpf: {} ja cadastrada!",entidadeCpfRequest.cpf());
        }
        entidadeCpfRepository.save(toEntidade(entidadeCpfRequest));
    }

    @Override
    public void atualizarCpf(UUID uuid, EntidadeCpfRequest entidadeCpfRequest) {
        var buscarEntidadeCpf=entidadeCpfRepository.findById(uuid);
        if (buscarEntidadeCpf.isEmpty()){
            log.warn("Pessoa fisica com ID: {} nao encontrada!",uuid);
            throw new EntidadeCpfNotFoundException("Nao encontrada!");
        }
        buscarEntidadeCpf.map(entidadeCpf -> {
            entidadeCpf.setCpf(entidadeCpfRequest.cpf());
            entidadeCpf.setNome(entidadeCpfRequest.nome());
            entidadeCpf.setDataNasci(entidadeCpfRequest.dataNassci());
            entidadeCpf.setSexo(entidadeCpfRequest.sexo());
            entidadeCpf.setEmails(entidadeCpfRequest.emails());
            entidadeCpf.setEndereco(entidadeCpfRequest.endereco());
            entidadeCpf.setEndereco(entidadeCpfRequest.endereco());
            return entidadeCpfRepository.save(entidadeCpf);
        });

    }

    @Override
    public void deletarEntidade(UUID uuid) {
        try{
            var buscarEntidadeCpf=entidadeCpfRepository
                    .findById(uuid)
                    .orElseThrow(()-> new EntidadeCnpjNotFoundException("Entidade não encontrada!"));
            entidadeCpfRepository.delete(buscarEntidadeCpf);
        }catch (RuntimeException e){
            log.warn("Error:{} .",e.getMessage());
            throw new EntidadeCpfNotFoundException("Error.");
        }
    }
}
