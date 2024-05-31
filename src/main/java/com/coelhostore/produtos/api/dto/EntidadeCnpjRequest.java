package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Endereco;
import com.coelhostore.produtos.domain.entities.EntidadeCnpj;
import com.coelhostore.produtos.domain.entities.Telefone;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EntidadeCnpjRequest(
        @CNPJ
        @NotBlank
        String cnpj,
        @NotBlank
        String rasaoSocial,
        @NotBlank
        Set<String> email,
        @NotBlank
        Set<Telefone>telefones,
        @NotBlank
        Endereco endereco
) {

    public static EntidadeCnpj toEntidade(EntidadeCnpjRequest entidadeCnpjRequest){
        if (entidadeCnpjRequest==null){
            return null;
        }
        return new EntidadeCnpj(
                entidadeCnpjRequest.cnpj,
                entidadeCnpjRequest.rasaoSocial,
                entidadeCnpjRequest.email,
                entidadeCnpjRequest.telefones,
                entidadeCnpjRequest.endereco
        );
    }
}
