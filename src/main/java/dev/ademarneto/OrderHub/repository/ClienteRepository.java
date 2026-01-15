package dev.ademarneto.OrderHub.repository;

import dev.ademarneto.OrderHub.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    Optional<ClienteModel>findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    void  deleteByCpf(String cpf);
}
