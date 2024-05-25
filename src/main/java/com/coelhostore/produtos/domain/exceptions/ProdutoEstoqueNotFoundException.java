package com.coelhostore.produtos.domain.exceptions;

public class ProdutoEstoqueNotFoundException extends ModelNotFoundException{
    public ProdutoEstoqueNotFoundException(String message){
        super(message);
    }
    public ProdutoEstoqueNotFoundException(){
        super("Produto do estoque não encontrado!");
    }
}
