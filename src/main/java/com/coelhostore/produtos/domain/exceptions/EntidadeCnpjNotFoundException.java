package com.coelhostore.produtos.domain.exceptions;

import com.coelhostore.produtos.domain.entities.EntidadeCnpj;

public class EntidadeCnpjNotFoundException extends ModelNotFoundException{
    public EntidadeCnpjNotFoundException(String message){
        super(message);
    }
    public EntidadeCnpjNotFoundException(){
        super("Entidade não encontrada!");
    }
}
