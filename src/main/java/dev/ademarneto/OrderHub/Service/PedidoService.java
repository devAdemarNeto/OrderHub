package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.model.PedidoModel;
import dev.ademarneto.OrderHub.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    //Criar um novo Pedido
    public PedidoModel criarPedido(PedidoModel order){
        return pedidoRepository.save(order);
    }


    //Listar pedidos
    public List<PedidoModel>  listarPedidos(){
        return pedidoRepository.findAll();
    }

    //Listar pedidos por id
    public PedidoModel listarPedidosId(Long id){
        Optional<PedidoModel> orderID = pedidoRepository.findById(id);
        return orderID.orElse(null);
    }


    // Deletar pedido
    public void deletarPedido(Long id){
        pedidoRepository.deleteById(id);
    }

    //Atualizar Pedido
    public PedidoModel atualizarPedido(Long id, PedidoModel pedidoAtualizado){
        if(pedidoRepository.existsById(id)){
            return pedidoRepository.save(pedidoAtualizado);
        }
        return null;
    }

    //Busca pedido pelo numero do pedido
    public PedidoModel buscarPorNumeroPedido(String numeroPedido){
        return pedidoRepository.findByNumeroPedido(numeroPedido)
                .orElse(null);
    }

    // Deletar pedido pelo numero do pedido
    public boolean deletarPorNumeroPedido(String numeroPedido){
        if(pedidoRepository.existsByNumeroPedido(numeroPedido)){
            pedidoRepository.deleteByNumeroPedido(numeroPedido);
            return true;
        }
        return false;
    }

    //Atualizar pedido pelo numero do pedido
    public PedidoModel atualizarPorNumeroPedido(String numeroPedido, PedidoModel pedidoAtualizado){
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findByNumeroPedido(numeroPedido);

        if(pedidoExistente.isPresent()){
            PedidoModel pedido = pedidoExistente.get();

            if (pedidoAtualizado.getDescricao() != null) {
                pedido.setDescricao(pedidoAtualizado.getDescricao());
            }

            if (pedidoAtualizado.getValorTotal() != null){
                pedido.setValorTotal(pedidoAtualizado.getValorTotal());
            }

            if (pedidoAtualizado.getDataPedido() != null){
                pedido.setDataPedido(pedidoAtualizado.getDataPedido());
            }

            if (pedidoAtualizado.getClient() != null){
                pedido.setClient(pedidoAtualizado.getClient());
            }

            return pedidoRepository.save(pedido);
        }
        return null;
    }


}
