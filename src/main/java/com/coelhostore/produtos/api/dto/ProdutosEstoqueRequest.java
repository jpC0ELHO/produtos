package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.ProdutosEstoque;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProdutosEstoqueRequest(
        @NotBlank
        String sku,
        @NotBlank
        String nomeProduto,
        @NotNull
        BigDecimal valorProduto,
        @NotNull
        BigDecimal qtdEstoque,
        @NotBlank
        Boolean produtosReservad,
        @NotNull
        Double qtdReservados,
        @NotNull
        BigDecimal qtdProdutosTotal
) {
    public static ProdutosEstoque toEntidade(ProdutosEstoqueRequest produtosEstoqueRequest){
        if (produtosEstoqueRequest==null){
            return null;
        }
        return new ProdutosEstoque(
                produtosEstoqueRequest.sku,
                produtosEstoqueRequest.nomeProduto,
                produtosEstoqueRequest.valorProduto,
                produtosEstoqueRequest.qtdEstoque,
                produtosEstoqueRequest.produtosReservad,
                produtosEstoqueRequest.qtdReservados,
                produtosEstoqueRequest.qtdProdutosTotal
        );
    }
}
