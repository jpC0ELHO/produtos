package com.coelhostore.produtos.domain.entities.enums;

import lombok.Getter;

@Getter
public enum TelefoneTipo {
    PESSOAL("PESSOAL"),
    COMERCIAL("COMERCIAL"),
    RESIDENCIAL("RESIDENCIAL"),
    OUTROS("OUTROS");

    private final String name;

    TelefoneTipo(String name){
        this.name=name;
    }
}
