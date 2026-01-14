package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.model.OrderModel;
import dev.ademarneto.OrderHub.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //Criar um novo Pedido
    public OrderModel criarPedido(OrderModel order){
        return orderRepository.save(order);
    }


    //Listar pedidos
    public List<OrderModel>  listarPedidos(){
        return orderRepository.findAll();
    }

    //Listar pedidos por id
    public OrderModel listarPedidosId(Long id){
        Optional<OrderModel> orderID = orderRepository.findById(id);
        return orderID.orElse(null);
    }


    // Deletar pedido
    public void deletarPedido(Long id){
        orderRepository.deleteById(id);
    }

    //Atualizar Pedido
    public OrderModel atualizarPedido(Long id, OrderModel pedidoAtualizado){
        if(orderRepository.existsById(id)){
            return orderRepository.save(pedidoAtualizado);
        }
        return null;
    }


}
