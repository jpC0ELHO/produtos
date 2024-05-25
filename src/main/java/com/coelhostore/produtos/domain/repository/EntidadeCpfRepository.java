package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.EntidadeCpf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EntidadeCpfRepository extends JpaRepository<EntidadeCpf,UUID > {

    Optional<EntidadeCpf>findById(UUID uuid);

    Optional<EntidadeCpf>findByNumero(String cep);
}
