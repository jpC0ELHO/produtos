package com.coelhostore.produtos.domain.exceptions;

public class EnderecoNotFoundException extends ModelNotFoundException{
    public EnderecoNotFoundException(String message){
        super(message);
    }
    public EnderecoNotFoundException(){
        super("Endereco não encontrado");
    }
}
