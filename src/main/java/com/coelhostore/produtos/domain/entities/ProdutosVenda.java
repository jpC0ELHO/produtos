package com.coelhostore.produtos.domain.entities;

import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "produtos_venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutosVenda extends Entidade{

    @Column(nullable = false)
    private String nomeCliente;
    @Column(nullable = false,precision = 1)
    private BigDecimal valorVenda;
    @Embedded
    private EntidadeCpf entidadeCpf;
    @Embedded
    private EntidadeCnpj entidadeCnpj;
    @Embedded
    private ProdutosEstoque produtosEstoque;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataVenda;
    @Column(nullable = false,unique = false,length = 20)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(nullable = false)
    @JoinColumn(name = "produtos_venda",referencedColumnName = "venda")
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private Set<Telefone> telefone;
    @Embedded
    private Endereco endereco;
}
