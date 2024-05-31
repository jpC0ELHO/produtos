package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Endereco;
import com.coelhostore.produtos.domain.entities.EntidadeCnpj;
import com.coelhostore.produtos.domain.entities.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "cnpj", "rasaoSocial", "email"
        ,"telefones","enderecos", "createdBy"
        , "lastModifiedBy","createdAt", "updatedAt"})
public record EntidadeCnpjResponse (
        UUID uuid,
        String cnpj,
        String rasaoSocial,
        Set<String> email,
        Set<Telefone>telefones,
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

){
    public static EntidadeCnpjResponse toResponse(EntidadeCnpj entidadeCnpj){
        if (entidadeCnpj == null){
            return null;
        }
        return new EntidadeCnpjResponse(
                entidadeCnpj.getId(),
                entidadeCnpj.getCnpj(),
                entidadeCnpj.getCnpj(),
                entidadeCnpj.getEmail(),
                entidadeCnpj.getTelefones(),
                entidadeCnpj.getEndereco(),
                entidadeCnpj.getCreatedBy(),
                entidadeCnpj.getLastModifiedBy(),
                entidadeCnpj.getCreatedAt(),
                entidadeCnpj.getUpdateAt()
        );
    }
}
