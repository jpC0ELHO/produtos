package com.coelhostore.produtos.domain.entities;

import com.coelhostore.produtos.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_telefone")
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Telefone extends Entidade{

    @Enumerated
    @Column(nullable = false)
    private TelefoneTipo tipo=TelefoneTipo.PESSOAL;

    @Column(nullable = false,unique = true,length = 16)
    private String numero;
}
