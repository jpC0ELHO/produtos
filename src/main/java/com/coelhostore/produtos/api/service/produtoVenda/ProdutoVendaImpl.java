package com.coelhostore.produtos.api.service.produtoVenda;

import com.coelhostore.produtos.api.dto.ProdutoVendaRequest;
import com.coelhostore.produtos.api.dto.ProdutoVendaResponse;
import com.coelhostore.produtos.domain.exceptions.ProdutoVendaNotFoundException;
import com.coelhostore.produtos.domain.repository.ProdutosVendaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.ProdutoVendaRequest.toEntidade;

@Log4j2
@Service
@AllArgsConstructor
public class ProdutoVendaImpl implements ProdutoVendaService {

    private final ProdutosVendaRepository produtosVendaRepository;
    @Override
    public List<ProdutoVendaResponse> listaDeProdutos() {
        try {
            var buscarProdutos=produtosVendaRepository.findAll();
            if (buscarProdutos.isEmpty()){
                log.warn("Lista nao encontrada.");
                throw new ProdutoVendaNotFoundException("Lista nula ou nao encontrada!");
            }
            return buscarProdutos.stream()
                    .map(ProdutoVendaResponse::toResponse)
                    .toList();
        }catch (RuntimeException e){
            log.info("Error:{} .",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<ProdutoVendaResponse> buscarProdutoId(UUID uuid) {
        var buscarProdutos=produtosVendaRepository.findById(uuid);
        if (buscarProdutos.isEmpty()){
            log.info("Produto com id:{} nao encontrado!",uuid);
            throw new ProdutoVendaNotFoundException("Id nao encontrado!");
        }
        return buscarProdutos.map(ProdutoVendaResponse::toResponse);
    }

    @Override
    public void criarProduto(ProdutoVendaRequest produtoVendaRequest) {
        var buscarProduto=produtosVendaRepository.findBySku(produtoVendaRequest.produtosEstoque().getSku());
        if (buscarProduto.isPresent()){
            log.info("Produto com sku :{} ja cadastrado!",produtoVendaRequest.produtosEstoque().getSku());
            throw new ProdutoVendaNotFoundException("Produto venda ja cadastrado!");
        }
        produtosVendaRepository.save(toEntidade(produtoVendaRequest));
    }

    @Override
    public void atualizarProduto(UUID uuid, ProdutoVendaRequest produtoVendaRequest) {
        var bucarProduto=produtosVendaRepository.findById(uuid);
        if (bucarProduto.isEmpty()){
            log.info("Produto com id : {}, não encontrado!",uuid);
            throw new ProdutoVendaNotFoundException("Nao encontrado!");

        }
        bucarProduto.map(produtosVenda -> {
            produtosVenda.setNomeCliente(produtoVendaRequest.nomeCliente());
            produtosVenda.setValorVenda(produtoVendaRequest.valorVenda());
            produtosVenda.setEntidadeCpf(produtoVendaRequest.entidadeCpf());
            produtosVenda.setEntidadeCnpj(produtoVendaRequest.entidadeCnpj());
            produtosVenda.setDataVenda(produtoVendaRequest.dataVenda());
            produtosVenda.setSexo(produtoVendaRequest.sexo());
            produtosVenda.setTelefone(produtoVendaRequest.telefone());
            produtosVenda.setEndereco(produtoVendaRequest.endereco());
            return produtosVendaRepository.save(produtosVenda);
        });
    }

    @Override
    public void deletarProduto(UUID uuid) {

        try {
            var buscarProduto=produtosVendaRepository
                    .findById(uuid)
                    .orElseThrow(()-> new ProdutoVendaNotFoundException("Produto nao encontrado!"));
            produtosVendaRepository.delete(buscarProduto);
            log.info("Produto de id:{} deletado!",uuid);
        }catch (RuntimeException e){
            log.info("Error: {} .",e.getMessage());
        }

    }
}
