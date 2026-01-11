package dev.ademarneto.OrderHub.repository;

import dev.ademarneto.OrderHub.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
