package dev.ademarneto.OrderHub.controller.web;

import dev.ademarneto.OrderHub.dto.PedidoDTO;
import dev.ademarneto.OrderHub.service.ClienteService;
import dev.ademarneto.OrderHub.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos-view")
public class PedidoWebController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    public PedidoWebController(PedidoService pedidoService, ClienteService clienteService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.listarPedidos());
        return "pedidos/lista";
    }

    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("pedido", new PedidoDTO());
        model.addAttribute("clientes", clienteService.listarClientes()); // Para o dropdown
        return "pedidos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarPedido(@ModelAttribute("pedido") PedidoDTO pedidoDTO, RedirectAttributes redirectAttributes) {
        try {
            if (pedidoDTO.getId() == null) {
                pedidoService.criarPedido(pedidoDTO);
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Pedido criado com sucesso!");
            } else {
                pedidoService.atualizarPedido(pedidoDTO.getId(), pedidoDTO);
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Pedido atualizado com sucesso!");
            }
            return "redirect:/pedidos-view";
        } catch (Exception e) {
            e.printStackTrace(); // Para debug no console
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar pedido: " + e.getMessage());
            return "redirect:/pedidos-view/novo";
        }
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            PedidoDTO pedido = pedidoService.listarPedidosId(id);
            if (pedido == null)
                throw new IllegalArgumentException("Pedido não encontrado");

            model.addAttribute("pedido", pedido);
            model.addAttribute("clientes", clienteService.listarClientes());
            return "pedidos/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao carregar pedido: " + e.getMessage());
            return "redirect:/pedidos-view";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.deletarPedido(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Pedido removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao remover pedido: " + e.getMessage());
        }
        return "redirect:/pedidos-view";
    }
}
