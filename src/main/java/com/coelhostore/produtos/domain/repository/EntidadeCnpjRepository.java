package com.coelhostore.produtos.domain.repository;

import com.coelhostore.produtos.domain.entities.EntidadeCnpj;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EntidadeCnpjRepository extends JpaRepository<EntidadeCnpj, UUID> {

    Optional<EntidadeCnpj>findById(UUID uuid);

    Optional<EntidadeCnpj>findByCnpj(String cnpj);
}
