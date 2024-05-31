package com.coelhostore.produtos.api.service.entidadeCnpj;


import com.coelhostore.produtos.api.dto.EntidadeCnpjRequest;
import com.coelhostore.produtos.api.dto.EntidadeCnpjResponse;
import com.coelhostore.produtos.domain.exceptions.EntidadeCnpjNotFoundException;
import com.coelhostore.produtos.domain.repository.EntidadeCnpjRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.EntidadeCnpjRequest.toEntidade;

@Service
@Log4j2
@RequiredArgsConstructor
public class EntidadeCnpjImpl implements EntidadeCnpjService{

    private final EntidadeCnpjRepository entidadeCnpjRepository;

    @Override
    public List<EntidadeCnpjResponse> buscarListaCnpj() {
        try {
            var buscarEntidadeCnpj=entidadeCnpjRepository.findAll();
            if (buscarEntidadeCnpj==null){
                throw new EntidadeCnpjNotFoundException("Lista vazia.");
            }else{
                log.info("Lista encontrada!");
                return buscarEntidadeCnpj
                        .stream()
                        .map(EntidadeCnpjResponse::toResponse)
                        .toList();
            }
        }catch (RuntimeException e){
            log.info("Error:{}.",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<EntidadeCnpjResponse> buscarEntidadeId(UUID uuid) {
            var buscarEntidadeCnpj=entidadeCnpjRepository.findById(uuid);
            if (buscarEntidadeCnpj.isEmpty()){
                log.warn("Entidade com id:{} nao encontrada.",uuid);
                throw new EntidadeCnpjNotFoundException("Id inexistente ou errado.");
            }
            return buscarEntidadeCnpj.map(EntidadeCnpjResponse::toResponse);
    }

    @Override
    public void criarEntidade(EntidadeCnpjRequest entidadeCnpRequest) {
        var buscarEntidadeCnpj=entidadeCnpjRepository.findByCnpj(entidadeCnpRequest.cnpj());
        if (buscarEntidadeCnpj.isPresent()){
            log.info("Cnpj:{} ja cadastrado!",entidadeCnpRequest.cnpj());
            throw new EntidadeCnpjNotFoundException("Entidade ja cadastrada!");
        }
        entidadeCnpjRepository.save(toEntidade(entidadeCnpRequest));
    }

    @Override
    public void atualizarEntidade(UUID uuid, EntidadeCnpjRequest entidadeCnpjRequest) {
        var buscarEntidadeId=entidadeCnpjRepository.findById(uuid);
        if (buscarEntidadeId.isEmpty()){
            log.info("Id:{} nao encontrado!",uuid);
            throw new EntidadeCnpjNotFoundException("Id nao encontrado!");
        }
        buscarEntidadeId.map(entidadeCnpj -> {
            entidadeCnpj.setCnpj(entidadeCnpjRequest.cnpj());
            entidadeCnpj.setRasaosocial(entidadeCnpjRequest.rasaoSocial());
            entidadeCnpj.setEmail(entidadeCnpjRequest.email());
            entidadeCnpj.setTelefones(entidadeCnpjRequest.telefones());
            entidadeCnpj.setEndereco(entidadeCnpjRequest.endereco());
            return entidadeCnpjRepository.save(entidadeCnpj);
        });
    }

    @Override
    public void deletarEntidade(UUID uuid) {
        try {
            var buscarEntidade=entidadeCnpjRepository
                    .findById(uuid)
                    .orElseThrow(()-> new EntidadeCnpjNotFoundException("Não encontrado!"));
            entidadeCnpjRepository.delete(buscarEntidade);
        }catch (RuntimeException e){
            log.info("Error: {}.",e.getMessage());
            throw new EntidadeCnpjNotFoundException("Error.");
        }
    }
}
