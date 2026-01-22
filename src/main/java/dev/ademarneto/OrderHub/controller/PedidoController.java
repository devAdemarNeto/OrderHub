package dev.ademarneto.OrderHub.controller;

import dev.ademarneto.OrderHub.dto.PedidoDTO;
import dev.ademarneto.OrderHub.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos", description = "Gestão de pedidos de venda")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Criar pedido", description = "Registra um novo pedido para um cliente existente")
    @PostMapping
    public PedidoDTO criarPedido(@RequestBody PedidoDTO pedido){
        return pedidoService.criarPedido(pedido);
    }

    @Operation(summary = "Listar pedidos", description = "Retorna todos os pedidos cadastrados")
    @GetMapping
    public List<PedidoDTO> listarPedidos(){
        return pedidoService.listarPedidos();
    }


    @Operation(summary = "Buscar pedidos",
               description = "Recupera os detalhes completos de um pedido específico utilizando o numero do pedido")
    @GetMapping("/{numeropedido}")
    public PedidoDTO buscarPorNumeroPedido(@PathVariable("numeroPedido") String numeroPedido){
        return pedidoService.buscarPorNumeroPedido(numeroPedido);
    }

    @Operation(summary = "Atualizar pedido",description = "Atualiza total ou parcialmente os dados de um pedido existente, baseado no numero do pedido ")
    @PatchMapping("/{numeropedido}")
    public PedidoDTO atualizarPedido(@PathVariable("numeroPedido") String numeroPedido, @RequestBody PedidoDTO pedidoAtualizado){
        return pedidoService.atualizarPorNumeroPedido(numeroPedido,pedidoAtualizado);
    }

    @Operation(summary = "Deletar pedido", description = "Remove um pedido do sistema, baseado no numero do pedido")
    @DeleteMapping("{numeropedido}")
    public void deletarPorNumeroPedido(@PathVariable("numeropedido") String numeroPedido){
        pedidoService.deletarPorNumeroPedido(numeroPedido);
    }

}
