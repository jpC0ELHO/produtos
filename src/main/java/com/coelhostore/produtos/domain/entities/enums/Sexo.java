package com.coelhostore.produtos.domain.entities.enums;

import lombok.Getter;

@Getter
public enum Sexo {

    MASCULINO("MASCULINO"),
    FEMININO("FEMININO"),
    OUTRO("OUTRO");
    private final String nome;
    Sexo(String nome){
        this.nome=nome;
    }
}
