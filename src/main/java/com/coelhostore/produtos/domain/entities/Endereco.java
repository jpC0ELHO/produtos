package com.coelhostore.produtos.domain.entities;

import com.coelhostore.produtos.domain.entities.enums.Estado;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class Endereco extends Entidade{
    @Column(nullable = false,unique = false,length = 100)
    private String logradouro;
    @Column(nullable = false,unique = false,length = 10)
    private String numero;
    @Column(nullable = false,unique = false,length = 100)
    private String complemento;
    @Column(nullable = false,unique = false,length = 100)
    private String bairro;
    @Column(nullable = false,unique = false,length = 100)
    private String cidade;
    @Column(nullable = false,unique = false,length = 20)
    private Estado estado;

    @NotBlank
    @NotNull
    @Column(nullable = false,unique = false,length = 10)
    @Pattern(regexp = "\\d{5}-\\d{3}",message = "o cep deve ter o formato de '12345-678'")
    private String cep;
}
