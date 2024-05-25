package com.coelhostore.produtos.domain.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos_varejo")
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutosEstoque extends Entidade{

    @Column(name = "sku",nullable = false)
    private String sku;

    @Column(name = "nome_produto",nullable = false)
    private String nameProduto;

    @Column(name = "valor_produto",nullable = false)
    private BigDecimal valorProduto;
    @Column(name = "qtd_estoque",nullable = false)
    private BigDecimal qtdEstoque;
    @Column(name = "produto_reservado",nullable = false)
    private Boolean produtosReservad;
    @Column(name = "qtd_reservados",nullable = false)
    private Double qtdReservados;
    @Column(name = "qtdprotudos_total",nullable = false)
    private BigDecimal qtdProdutosTotal;

}
