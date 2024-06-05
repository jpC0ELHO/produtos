package com.coelhostore.produtos.api.service.endereco;

import com.coelhostore.produtos.api.dto.EnderecoRequest;
import com.coelhostore.produtos.api.dto.EnderecoResponse;
import com.coelhostore.produtos.domain.exceptions.EnderecoNotFoundException;
import com.coelhostore.produtos.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.EnderecoRequest.toEntidade;

@Service
@Log4j2
@RequiredArgsConstructor
public class EnderecoImpl implements EnderecoService{
    private final EnderecoRepository enderecoRepository;
    @Override
    public List<EnderecoResponse> listaEnderecos() {
        try {
            var buscarEndereco=enderecoRepository.findAll();
            if (buscarEndereco.isEmpty()){
                log.info("Lista vazia ou valor nulo!");
                throw new EnderecoNotFoundException("Lista nao encontrada!");
            }else{
                log.info("Lista encontrada!");
                return buscarEndereco.stream()
                        .map(EnderecoResponse::toResponse)
                        .toList();

            }

        }catch (RuntimeException e){
            log.warn("Error:{} .",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<EnderecoResponse> enderecoId(UUID uuid) {
       var buscarEndereco=enderecoRepository.findById(uuid);
       if (buscarEndereco.isEmpty()){
           log.info("Endereco com id:{} nao encontrado!",uuid);
           throw new EnderecoNotFoundException("Nao encontrado!");

       }
       return buscarEndereco.map(EnderecoResponse::toResponse);
    }

    @Override
    public void criarEndereco(EnderecoRequest enderecoRequest) {
        var buscarEndereco=enderecoRepository.findByCep(enderecoRequest.cep());
        if (buscarEndereco.isPresent()){
            log.info("Endereco com CEP:{} ja cadastrado!",enderecoRequest.cep());
        }else {
            enderecoRepository.save(toEntidade(enderecoRequest));
        }
    }

    @Override
    public void atualizarEndereco(UUID uuid, EnderecoRequest enderecoRequest) {
        var buscarEndereco=enderecoRepository.findById(uuid);
        if (buscarEndereco.isEmpty()){
            log.info("Endereco com id:{} nao encontrado!",uuid);
        }
        buscarEndereco.map(endereco -> {
            endereco.setLogradouro(enderecoRequest.logradouro());
            endereco.setNumero(enderecoRequest.numero());
            endereco.setComplemento(enderecoRequest.complemento());
            endereco.setBairro(enderecoRequest.bairro());
            endereco.setCidade(enderecoRequest.cidade());
            endereco.setEstado(enderecoRequest.estado());
            endereco.setCep(enderecoRequest.cep());
            return enderecoRepository.save(endereco);
        });
    }

    @Override
    public void deletarEndereco(UUID uuid) {
        try {
            var buscarEndereco=enderecoRepository.findById(uuid)
                    .orElseThrow(()-> new EnderecoNotFoundException("Endereco nao encontrado!"));
            enderecoRepository.delete(buscarEndereco);
        }catch (RuntimeException e){
            log.warn("Error:{} .",e.getMessage());
        }
    }
}
