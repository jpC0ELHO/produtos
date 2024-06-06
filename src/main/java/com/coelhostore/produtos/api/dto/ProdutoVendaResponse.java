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
@JsonPropertyOrder({"id", "nomeCliente", "nomeProduto", "valorProduto","qtdEstoque"
        ,"produtosReservad","qtdReservados","qtdProdutosTotal"
        , "createdBy", "lastModifiedBy","createdAt", "updatedAt"})
public record ProdutoVendaResponse(
        UUID uuid,
        String nomeCliente,
        EntidadeCpf entidadeCpf,
        EntidadeCnpj entidadeCnpj,
        ProdutosEstoque produtosEstoque,
        BigDecimal valorVenda,
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        @JsonDeserialize(using = JsonDeserializer.class)
        @JsonSerialize(using = JsonSerializer.class)
        LocalDate dataVenda,
        Sexo sexo,
        Set<Telefone>telefone,
        Endereco endereco,
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
    public static ProdutoVendaResponse toResponse(ProdutosVenda produtosVenda){
        if (produtosVenda==null){
            return null;
        }
        return new ProdutoVendaResponse(
                produtosVenda.getId(),
                produtosVenda.getNomeCliente(),
                produtosVenda.getEntidadeCpf(),
                produtosVenda.getEntidadeCnpj(),
                produtosVenda.getProdutosEstoque(),
                produtosVenda.getValorVenda(),
                produtosVenda.getDataVenda(),
                produtosVenda.getSexo(),
                produtosVenda.getTelefone(),
                produtosVenda.getEndereco(),
                produtosVenda.getCreatedBy(),
                produtosVenda.getLastModifiedBy(),
                produtosVenda.getCreatedAt(),
                produtosVenda.getUpdateAt()
        );
    }
}
