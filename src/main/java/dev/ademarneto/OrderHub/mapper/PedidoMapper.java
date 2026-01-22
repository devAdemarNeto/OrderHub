package dev.ademarneto.OrderHub.mapper;

import dev.ademarneto.OrderHub.dto.PedidoDTO;
import dev.ademarneto.OrderHub.model.PedidoModel;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoModel map(PedidoDTO pedidoDTO){
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setId(pedidoDTO.getId());
        pedidoModel.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedidoModel.setDescricao(pedidoDTO.getDescricao());
        pedidoModel.setValorTotal(pedidoDTO.getValorTotal());
        pedidoModel.setDataPedido(pedidoDTO.getDataPedido());
        pedidoModel.setCliente(pedidoDTO.getCliente());

        return pedidoModel;
    }


    public PedidoDTO map(PedidoModel pedidoModel){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoModel.getId());
        pedidoDTO.setNumeroPedido(pedidoModel.getNumeroPedido());
        pedidoDTO.setDescricao(pedidoModel.getDescricao());
        pedidoDTO.setValorTotal(pedidoModel.getValorTotal());
        pedidoDTO.setDataPedido(pedidoModel.getDataPedido());
        pedidoDTO.setCliente(pedidoModel.getCliente());

        return pedidoDTO;
    }

}
