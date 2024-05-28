package com.coelhostore.produtos.domain.entities;

import com.coelhostore.produtos.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Set;

public class EntidadeCpf extends Entidade{
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
