package com.coelhostore.produtos.domain.exceptions;

public class ProdutoVendaNotFoundException extends ModelNotFoundException{
    public ProdutoVendaNotFoundException(String message){
        super(message);
    }
    public ProdutoVendaNotFoundException(){
        super("Produto venda não encontrado!");
    }
}
