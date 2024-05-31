package com.coelhostore.produtos.domain.entities;

import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "tb_entidadepf")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
public class EntidadeCpf extends Entidade{
    @CPF
    @Column(nullable = false,unique = true,length = 20)
    private String cpf;

    @Column(nullable = false,length = 200)
    private String nome;

    @Column(name = "data_nascimento",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNasci;
    @Column(nullable = false,unique = false,length = 20)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @ElementCollection
    @Column(name = "emails",nullable = false)
    @CollectionTable(name = "tab_entidadepf_email",
            joinColumns = @JoinColumn(name = "entidadepf_cpf",referencedColumnName = "cpf"))
    private Set<String> emails;
    @Column(nullable = false)
    @JoinColumn(name = "entidadepf_cpf",referencedColumnName = "cpf")
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private Set<Telefone>telefones;

    @Embedded
    private Endereco endereco;
}
