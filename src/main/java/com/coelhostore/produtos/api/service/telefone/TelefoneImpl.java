package com.coelhostore.produtos.api.service.telefone;

import com.coelhostore.produtos.api.dto.TelefoneRequest;
import com.coelhostore.produtos.api.dto.TelefoneResponse;
import com.coelhostore.produtos.domain.exceptions.TelefoneNotFoundException;
import com.coelhostore.produtos.domain.repository.TelefoneRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.TelefoneRequest.toEntidade;

@Service
@Log4j2
@RequiredArgsConstructor
public class TelefoneImpl implements TelefoneService{
    private final TelefoneRepository telefoneRepository;
    @Override
    public List<TelefoneResponse> buscarListaTelefones() {
        try {
            var buscarTelefone=telefoneRepository.findAll();
            if (buscarTelefone==null){
                throw new TelefoneNotFoundException("Telefones nao encontrados!");
            }
            return buscarTelefone.stream()
                    .map(TelefoneResponse::toResponse)
                    .toList();
        }catch (RuntimeException e){
            log.info("Error:{}",e.getMessage());
        }

       return new ArrayList<>();
    }

    @Override
    public Optional<TelefoneResponse> buscarTelefoneId(UUID uuid) {
        var buscarTelefone=telefoneRepository.findById(uuid);
        if (buscarTelefone.isEmpty()){
            log.info("Telefone com id:{} nao encontrado!",uuid);
            throw new TelefoneNotFoundException("Id nao encontrado!");
        }
        return buscarTelefone.map(TelefoneResponse::toResponse);
    }

    @Override
    public void criarTelefone(TelefoneRequest telefoneRequest) {
        var buscarTelefone=telefoneRepository.findByNumber(telefoneRequest.numero());
        if (buscarTelefone.isPresent()){
            log.info("Numero já registrado:{}",telefoneRequest.numero());
        }
        telefoneRepository.save(toEntidade(telefoneRequest));
    }

    @Override
    public void atualizarTelefone(UUID uuid, TelefoneRequest telefoneRequest) {
        var buscarTelefone=telefoneRepository.findById(uuid);
        if (buscarTelefone.isEmpty()){
            log.info("Telefone com id:{} nao encontrado!",uuid);
            throw new TelefoneNotFoundException("Nao encontrado!");
        }
        buscarTelefone.map(telefone -> {
            telefone.setTipo(telefoneRequest.tipo());
            telefone.setNumero(telefoneRequest.numero());
            return telefoneRepository.save(telefone);
        });
    }

    @Override
    public void deletarTelefone(UUID uuid) {
        try{
            var buscarTelefone=telefoneRepository
                    .findById(uuid)
                    .orElseThrow(()-> new TelefoneNotFoundException("Telefone nao encontrado!"));
            telefoneRepository.delete(buscarTelefone);
        }catch (RuntimeException e){
            log.info("Error:{} .",e.getMessage());
        }
    }
}
