package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Endereco;
import com.coelhostore.produtos.domain.entities.EntidadeCpf;
import com.coelhostore.produtos.domain.entities.Telefone;
import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EntidadeCpfRequest(
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNassci,
        @NotBlank
        Sexo sexo,
        @NotBlank
        Set<String> emails,
        @NotNull
        Set<Telefone>telefones,
        @NotBlank
        Endereco endereco
) {
    public static EntidadeCpf toEntidade(EntidadeCpfRequest entidadeCpfRequest){
        if (entidadeCpfRequest==null){
            return null;
        }

        return new EntidadeCpf(
                entidadeCpfRequest.cpf,
                entidadeCpfRequest.nome,
                entidadeCpfRequest.dataNassci,
                entidadeCpfRequest.sexo,
                entidadeCpfRequest.emails,
                entidadeCpfRequest.telefones,
                entidadeCpfRequest.endereco
        );
    }
}
