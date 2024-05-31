package com.coelhostore.produtos.api.service.produtoEstoque;

import com.coelhostore.produtos.api.dto.ProdutosEstoqueRequest;
import com.coelhostore.produtos.api.dto.ProdutosEstoqueResponse;
import com.coelhostore.produtos.domain.exceptions.ProdutoEstoqueNotFoundException;
import com.coelhostore.produtos.domain.repository.ProdutosEstoqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.coelhostore.produtos.api.dto.ProdutosEstoqueRequest.toEntidade;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProdutosEstoqueImpl implements ProdutosEstoqueService{

    private final ProdutosEstoqueRepository produtosEstoqueRepository;

    @Override
    public List<ProdutosEstoqueResponse> buscarListaProdutos() {
        try {
            var buscarProdutos=produtosEstoqueRepository.findAll();
            log.info("Lista de produtos encontrada!");
            return buscarProdutos
                    .stream()
                    .map(ProdutosEstoqueResponse::toResponse)
                    .toList();
        }catch (RuntimeException e){
            log.info("Error:{}",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<ProdutosEstoqueResponse> buscarProdutoId(UUID uuid) {
       var buscarProduto=produtosEstoqueRepository.findById(uuid);
       if (buscarProduto.isEmpty()){
           log.warn("Produto com id: {} não encontrado!",uuid);
           throw new ProdutoEstoqueNotFoundException("Produto não encontrado!");
       }
       return buscarProduto.map(ProdutosEstoqueResponse::toResponse);
    }

    @Override
    public void criarProduto(ProdutosEstoqueRequest produtosEstoqueRequest) {
            var buscarProdutoSku=produtosEstoqueRepository.findBySku(produtosEstoqueRequest.sku());
            if (buscarProdutoSku.isPresent()){
                log.warn("Sku:{} já cadastrado!",produtosEstoqueRequest.sku());
                throw new ProdutoEstoqueNotFoundException("Produto com sku já está cadastrado");
            }
            produtosEstoqueRepository.save(toEntidade(produtosEstoqueRequest));
    }

    @Override
    public void atualizarProduto(UUID uuid, ProdutosEstoqueRequest produtosEstoqueRequest) {
        var buscarProdutos=produtosEstoqueRepository.findById(uuid);
        if (buscarProdutos.isEmpty()){
            log.warn("Id:{} não encontrado!",uuid);
            throw new ProdutoEstoqueNotFoundException("Produto não encontrado!");
        }
        buscarProdutos.map(produtosEstoque -> {
            produtosEstoque.setSku(produtosEstoqueRequest.sku());
            produtosEstoque.setNomeProduto(produtosEstoqueRequest.nomeProduto());
            produtosEstoque.setValorProduto(produtosEstoqueRequest.valorProduto());
            produtosEstoque.setQtdEstoque(produtosEstoqueRequest.qtdEstoque());
            produtosEstoque.setProdutosReservad(produtosEstoqueRequest.produtosReservad());
            produtosEstoque.setQtdReservados(produtosEstoqueRequest.qtdReservados());
            produtosEstoque.setQtdProdutosTotal(produtosEstoqueRequest.qtdProdutosTotal());
           return produtosEstoqueRepository.save(produtosEstoque);
        });
    }

    @Override
    public void deletarProdutos(UUID uuid) {
            try {
                var buscarProdutos=produtosEstoqueRepository
                        .findById(uuid)
                        .orElseThrow(()-> new ProdutoEstoqueNotFoundException("Produto não encontrado!"));
                produtosEstoqueRepository.delete(buscarProdutos);
                log.info("Produto com id: {} deletado!",uuid);
            }catch (RuntimeException e){
                log.warn("Error: {}",e.getMessage());
                throw new ProdutoEstoqueNotFoundException("Error");
            }
    }
}
