package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Telefone;
import com.coelhostore.produtos.domain.entities.enums.TelefoneTipo;
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
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "tipo","numero","createdBy"
        , "lastModifiedBy","createdAt", "updatedAt"})
public record TelefoneResponse(
        UUID uuid,
        TelefoneTipo tipo,
        String numero,
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
    public static TelefoneResponse toResponse(Telefone telefone){
        if (telefone==null){
            return null;
        }
        return new TelefoneResponse(
                telefone.getId(),
                telefone.getTipo(),
                telefone.getNumero(),
                telefone.getCreatedBy(),
                telefone.getLastModifiedBy(),
                telefone.getCreatedAt(),
                telefone.getUpdateAt()
        );
    }
}
