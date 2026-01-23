package dev.ademarneto.OrderHub.controller.api;

import dev.ademarneto.OrderHub.dto.PedidoDTO;
import dev.ademarneto.OrderHub.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedido){
        PedidoDTO novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(201).body(novoPedido);
    }

    @Operation(summary = "Listar pedidos", description = "Retorna todos os pedidos cadastrados")
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos(){
        List<PedidoDTO> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }


    @Operation(summary = "Buscar pedidos",
               description = "Recupera os detalhes completos de um pedido específico utilizando o numero do pedido")
    @GetMapping("/{numeropedido}")
    public ResponseEntity<PedidoDTO> buscarPorNumeroPedido(@PathVariable("numeroPedido") String numeroPedido){
        PedidoDTO pedido = pedidoService.buscarPorNumeroPedido(numeroPedido);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Atualizar pedido",description = "Atualiza total ou parcialmente os dados de um pedido existente, baseado no numero do pedido ")
    @PatchMapping("/{numeropedido}")
    public ResponseEntity<PedidoDTO> atualizarPedido(
            @PathVariable("numeroPedido") String numeroPedido,
            @RequestBody PedidoDTO pedidoAtualizado){
        PedidoDTO pedidoSalvo = pedidoService.atualizarPorNumeroPedido(numeroPedido,pedidoAtualizado);
        return ResponseEntity.ok(pedidoSalvo);

    }

    @Operation(summary = "Deletar pedido", description = "Remove um pedido do sistema, baseado no numero do pedido")
    @DeleteMapping("{numeropedido}")
    public ResponseEntity<Void> deletarPorNumeroPedido(@PathVariable("numeropedido") String numeroPedido){
        pedidoService.deletarPorNumeroPedido(numeroPedido);
        return ResponseEntity.noContent().build();

    }

}
