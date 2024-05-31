package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Telefone;
import com.coelhostore.produtos.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TelefoneRequest(
        @NotBlank
        TelefoneTipo tipo,
        @NotBlank
        String numero

) {
    public static Telefone toEntidade(TelefoneRequest telefoneRequest){
        if (telefoneRequest==null){
            return null;
        }
        return new Telefone(
                telefoneRequest.tipo,
                telefoneRequest.numero
        );
    }
}
