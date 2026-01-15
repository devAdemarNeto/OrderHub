package dev.ademarneto.OrderHub.controller;

import dev.ademarneto.OrderHub.Service.PedidoService;
import dev.ademarneto.OrderHub.model.ClienteModel;
import dev.ademarneto.OrderHub.model.PedidoModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //Criar Pedido
    @PostMapping
    public PedidoModel criarPedido(@RequestBody PedidoModel pedido){
        return pedidoService.criarPedido(pedido);
    }

    //Listar pedidos
    @GetMapping
    public List<PedidoModel> listarPedidos(){
        return pedidoService.listarPedidos();
    }


    //buscar pedido pelo numero do pedido
    @GetMapping("/{numeropedido}")
    public PedidoModel buscarPorNumeroPedido(@PathVariable("numeroPedio") String numeroPedido){
        return pedidoService.buscarPorNumeroPedido(numeroPedido);
    }

    //Atualizar pedido pelo numero do pedido
    @PatchMapping("/{numeropedido}")
    public PedidoModel atualizar(@PathVariable("numeroPedio") String numeroPedido, @RequestBody PedidoModel pedidoAtualizado){
        return pedidoService.atualizarPorNumeroPedido(numeroPedido,pedidoAtualizado);
    }

    //Deletar pedido pelo numero do pedido
    @DeleteMapping("{numeropedido}")
    public void deletarPorNumeroPedido(@PathVariable("numeropedido") String numeroPedido){
        pedidoService.deletarPorNumeroPedido(numeroPedido);
    }

}
