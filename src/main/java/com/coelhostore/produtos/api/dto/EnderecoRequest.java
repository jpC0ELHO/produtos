package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Endereco;
import com.coelhostore.produtos.domain.entities.enums.Estado;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EnderecoRequest(
        @NotBlank
        String logradouro,
        @NotBlank
        @NotNull
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        Estado estado,
        @NotBlank
        String cep
) {
    public static Endereco toEntidade(EnderecoRequest enderecoRequest){
        if (enderecoRequest==null){
            return null;
        }
        return new Endereco(
                enderecoRequest.logradouro,
                enderecoRequest.numero,
                enderecoRequest.complemento,
                enderecoRequest.bairro,
                enderecoRequest.cidade,
                enderecoRequest.estado,
                enderecoRequest.cep
        );
    }
}
