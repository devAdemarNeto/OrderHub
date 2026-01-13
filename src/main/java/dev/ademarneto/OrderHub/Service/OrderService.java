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


    //Listar pedidos
    public List<OrderModel>  listarPedidos(){
        return orderRepository.findAll();
    }

    //Listar pedidos por id
    public OrderModel listarPedidosId(Long id){
        Optional<OrderModel> orderID = orderRepository.findById(id);
        return orderID.orElse(null);
    }



}
