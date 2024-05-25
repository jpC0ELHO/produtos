package com.coelhostore.produtos.domain.exceptions;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}
