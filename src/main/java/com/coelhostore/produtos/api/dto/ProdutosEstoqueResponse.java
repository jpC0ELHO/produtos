package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.ProdutosEstoque;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "sku", "nomeProduto", "valorProduto","qtdEstoque"
        ,"produtosReservad","qtdReservados","qtdProdutosTotal"
        , "createdBy", "lastModifiedBy","createdAt", "updatedAt"})
public record ProdutosEstoqueResponse(
        UUID id,
        String sku,
        String nomeProduto,
        BigDecimal valorProduto,
        BigDecimal qtdEstoque,
        Boolean produtosReservad,
        Double qtdReservados,
        BigDecimal qtdProdutosTotal,
        String createdBy,
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
                
        LocalDateTime updateAt
) {
    public static ProdutosEstoqueResponse toResponse(ProdutosEstoque produtosEstoque){
            if (produtosEstoque==null){
                    return null;
            }
            return new ProdutosEstoqueResponse(
                    produtosEstoque.getId(),
                    produtosEstoque.getSku(),
                    produtosEstoque.getNomeProduto(),
                    produtosEstoque.getValorProduto(),
                    produtosEstoque.getQtdEstoque(),
                    produtosEstoque.getProdutosReservad(),
                    produtosEstoque.getQtdReservados(),
                    produtosEstoque.getQtdProdutosTotal(),
                    produtosEstoque.getCreatedBy(),
                    produtosEstoque.getLastModifiedBy(),
                    produtosEstoque.getCreatedAt(),
                    produtosEstoque.getUpdateAt()
            );
    }
}
