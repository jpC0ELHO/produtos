package com.coelhostore.produtos.domain.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_entidadepj")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EntidadeCnpj extends Entidade{

    @Column(nullable = false,unique = true,length = 20)
    private String cnpj;
    @Column(nullable = false,length = 255)
    private String rasaosocial;

    @ElementCollection
    @CollectionTable(name = "tab_entidadepj_email",
            joinColumns = @JoinColumn(name = "entidadepj_cnpj",referencedColumnName = "cnpj"))
    @Column(nullable = false)
    private Set<String> email;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_cnpj",referencedColumnName = "cnpj")
    @Column(nullable = false)
    private Set<Telefone>telefones;
    @Embedded
    private Endereco endereco;

}
