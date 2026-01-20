package dev.ademarneto.OrderHub.controller;

import dev.ademarneto.OrderHub.DTO.ClienteDTO;
import dev.ademarneto.OrderHub.Service.ClienteService;
import dev.ademarneto.OrderHub.model.ClienteModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Criar cliente
    @PostMapping
    public ClienteDTO criarCliente(@RequestBody ClienteDTO cliente) {
        return clienteService.criarCliente(cliente);
    }

    // Listar clientes
    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    // Buscar cliente pelo CPF (chave de negócio)
    @GetMapping("/{cpf}")
    public ClienteDTO buscarPorCpf(@PathVariable("cpf") String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }

    // Atualizar cliente parcialmente pelo CPF
    @PatchMapping("/{cpf}")
    public ClienteDTO atualizarPorCpf(
            @PathVariable("cpf") String cpf,
            @RequestBody ClienteDTO clienteAtualizado) {

        return clienteService.atualizarPorCpf(cpf, clienteAtualizado);
    }

    // Deletar cliente pelo CPF
    @DeleteMapping("/{cpf}")
    public void deletarPorCpf(@PathVariable("cpf") String cpf) {
        clienteService.deletarPorCpf(cpf);
    }
}
