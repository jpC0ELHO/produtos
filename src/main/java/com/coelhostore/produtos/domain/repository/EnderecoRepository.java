package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    Optional<Endereco>findById(UUID uuid);

    Optional<Endereco>findByCep(String cep);
}
