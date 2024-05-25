package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {

    Optional<Telefone>findById(UUID uuid);

    Optional<Telefone>findByNumber(String numero);
}
