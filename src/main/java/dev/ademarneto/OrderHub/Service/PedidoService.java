package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.DTO.PedidoDTO;
import dev.ademarneto.OrderHub.Mapper.PedidoMapper;
import dev.ademarneto.OrderHub.model.PedidoModel;
import dev.ademarneto.OrderHub.repository.PedidoRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private PedidoMapper pedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    //Criar um novo Pedido
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO){
        PedidoModel pedido = pedidoMapper.map(pedidoDTO);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.map(pedido);
    }


    //Listar pedidos
    public List<PedidoDTO>  listarPedidos(){
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedidoMapper::map)
                .collect(Collectors.toList());

    }

    //Listar pedidos por id
    public PedidoDTO listarPedidosId(Long id){
        Optional<PedidoModel> pedidoId = pedidoRepository.findById(id);
        return pedidoId.map(pedidoMapper::map).orElse(null);

    }


    // Deletar pedido
    public void deletarPedido(Long id){
        pedidoRepository.deleteById(id);
    }

    //Atualizar Pedido
    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO){
        Optional<PedidoModel> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
            PedidoModel pedidoAtualizado = pedidoMapper.map(pedidoDTO);
            pedidoAtualizado.setId(id);
            PedidoModel pedidoSalvo = pedidoRepository.save(pedidoAtualizado);
            return pedidoMapper.map(pedidoSalvo);
        }
        return null;
    }

    //Busca pedido pelo numero do pedido
    public PedidoDTO buscarPorNumeroPedido(String numeroPedido){
        Optional<PedidoModel> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
        return pedido.map(pedidoMapper::map).orElse(null);
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
    public PedidoDTO atualizarPorNumeroPedido(String numeroPedido, PedidoDTO pedidoDTO){
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findByNumeroPedido(numeroPedido);

        if(pedidoExistente.isPresent()){
            PedidoModel pedidoAtualizado = pedidoMapper.map(pedidoDTO);
            pedidoAtualizado.setNumeroPedido(numeroPedido);
            PedidoModel pedidoSalvo = pedidoRepository.save(pedidoAtualizado);

            if (pedidoDTO.getDescricao() != null) {
                pedidoSalvo.setDescricao(pedidoDTO.getDescricao());
            }

            if (pedidoDTO.getValorTotal() != null){
                pedidoSalvo.setValorTotal(pedidoDTO.getValorTotal());
            }

            if (pedidoDTO.getDataPedido() != null){
                pedidoSalvo.setDataPedido(pedidoDTO.getDataPedido());
            }

            if (pedidoDTO.getCliente() != null){
                pedidoSalvo.setCliente(pedidoDTO.getCliente());
            }

            return pedidoMapper.map(pedidoSalvo);
        }
        return null;
    }


}
