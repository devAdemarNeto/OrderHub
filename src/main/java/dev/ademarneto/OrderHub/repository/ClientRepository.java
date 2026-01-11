package dev.ademarneto.OrderHub.repository;

import dev.ademarneto.OrderHub.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
