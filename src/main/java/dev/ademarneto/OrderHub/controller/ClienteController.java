package dev.ademarneto.OrderHub.controller;

import dev.ademarneto.OrderHub.dto.ClienteDTO;
import dev.ademarneto.OrderHub.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Endipoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Criar novo cliente", description = "Cadastra um cliente com CPF ùnico")
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO cliente) {
        ClienteDTO novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(201).body(novoCliente);
    }

    @Operation(summary = "Listar clientes", description = "Retorna todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Buscar cliente",
            description = "Recupera os detalhes completos de um cliente específico utilizando seu numero do CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable("cpf") String cpf) {
        ClienteDTO cliente = clienteService.buscarPorCpf(cpf);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza total ou parcialmente os dados de um cliente existente, baseado no numero do CPF")
    @PatchMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> atualizarPorCpf(
            @PathVariable("cpf") String cpf,
            @RequestBody ClienteDTO clienteAtualizado) {
        ClienteDTO clienteSalvo = clienteService.atualizarPorCpf(cpf,clienteAtualizado);

        return ResponseEntity.ok(clienteSalvo);


    }

    @Operation(summary = "Delatar cliente", description = "Remove um cliente do sistema, baseado no numero seu CPF")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarPorCpf(@PathVariable("cpf") String cpf) {
        clienteService.deletarPorCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
