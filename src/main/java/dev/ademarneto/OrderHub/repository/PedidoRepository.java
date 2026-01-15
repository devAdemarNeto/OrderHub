package dev.ademarneto.OrderHub.repository;

import dev.ademarneto.OrderHub.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

    Optional<PedidoModel> findByNumeroPedido(String numeroPedido);
    void deleteByNumeroPedido(String numeroPedido);
    boolean existsByNumeroPedido(String numeroPedido);

}
