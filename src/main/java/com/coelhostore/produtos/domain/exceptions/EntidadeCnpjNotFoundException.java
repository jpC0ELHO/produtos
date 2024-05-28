package com.coelhostore.produtos.domain.exceptions;



public class EntidadeCnpjNotFoundException extends ModelNotFoundException{
    public EntidadeCnpjNotFoundException(String message){
        super(message);
    }
    public EntidadeCnpjNotFoundException(){
        super("Entidade não encontrada!");
    }
}
