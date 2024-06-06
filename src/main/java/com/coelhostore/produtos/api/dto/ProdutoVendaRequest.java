package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.*;
import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProdutoVendaRequest(
        @NotBlank
        String nomeCliente,
        @NotNull
        @NotBlank
        EntidadeCpf entidadeCpf,
        @NotNull
        @NotBlank
        EntidadeCnpj entidadeCnpj,
        @NotNull
        @NotBlank
        ProdutosEstoque produtosEstoque,
        @NotNull
        BigDecimal valorVenda,
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        @JsonDeserialize(using = JsonDeserializer.class)
        @JsonSerialize(using = JsonSerializer.class)
        LocalDate dataVenda,
        @NotNull
        @NotBlank
        Sexo sexo,
        @NotBlank
        @NotNull
        Set<Telefone> telefone,
        @NotNull
        @NotBlank
        Endereco endereco
) {
    public static ProdutosVenda toEntidade(ProdutoVendaRequest produtoVendaRequest){
        if (produtoVendaRequest==null){
            return null;
        }
        return new ProdutosVenda(
                produtoVendaRequest.nomeCliente,
                produtoVendaRequest.valorVenda,
                produtoVendaRequest.entidadeCpf,
                produtoVendaRequest.entidadeCnpj,
                produtoVendaRequest.produtosEstoque,
                produtoVendaRequest.dataVenda,
                produtoVendaRequest.sexo,
                produtoVendaRequest.telefone,
                produtoVendaRequest.endereco
        );
    }
}
