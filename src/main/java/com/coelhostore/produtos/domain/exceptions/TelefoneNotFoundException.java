package com.coelhostore.produtos.domain.exceptions;

public class TelefoneNotFoundException extends ModelNotFoundException{
    public TelefoneNotFoundException(String message){
        super(message);
    }
    public TelefoneNotFoundException(){
        super("Telefone não encontrado!");
    }
}
