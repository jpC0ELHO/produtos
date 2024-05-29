package com.coelhostore.produtos.api.dto;

import com.coelhostore.produtos.domain.entities.Endereco;
import com.coelhostore.produtos.domain.entities.EntidadeCpf;
import com.coelhostore.produtos.domain.entities.Telefone;
import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "cpf", "dataNasci", "sexo","emails"
        ,"telefones","enderecos", "createdBy"
        , "lastModifiedBy","createdAt", "updatedAt"})
public record EntidadeCpfResponse(
        UUID id,
        String cpf,
        String nome,
        LocalDate dataNassci,
        Sexo sexo,
        Set<String> emails,
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

) {
    public static EntidadeCpfResponse toResponse(EntidadeCpf entidadeCpf){
        if (entidadeCpf==null){
            return null;
        }
        return new EntidadeCpfResponse(
                entidadeCpf.getId(),
                entidadeCpf.getCpf(),
                entidadeCpf.getNome(),
                entidadeCpf.getDataNasci(),
                entidadeCpf.getSexo(),
                entidadeCpf.getEmails(),
                entidadeCpf.getTelefones(),
                entidadeCpf.getEndereco(),
                entidadeCpf.getCreatedBy(),
                entidadeCpf.getLastModifiedBy(),
                entidadeCpf.getCreatedAt(),
                entidadeCpf.getUpdateAt()
        );
    }
}
