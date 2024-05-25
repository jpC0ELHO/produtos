package com.coelhostore.produtos.domain.exceptions;

public class EntidadeCpfNotFoundException extends ModelNotFoundException{
    public EntidadeCpfNotFoundException(String message) {
        super(message);
    }
    public EntidadeCpfNotFoundException(){
        super("Entidade não encontrada!");
    }
}
