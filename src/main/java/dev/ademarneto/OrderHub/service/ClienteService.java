package dev.ademarneto.OrderHub.service;

import dev.ademarneto.OrderHub.dto.ClienteDTO;
import dev.ademarneto.OrderHub.mapper.ClienteMapper;
import dev.ademarneto.OrderHub.model.ClienteModel;
import dev.ademarneto.OrderHub.repository.ClienteRepository;
import dev.ademarneto.OrderHub.validation.ValidadorCpf;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    // Criar um novo cliente
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        // Limpar CPF antes de qualquer processamento
        if (clienteDTO.getCpf() != null) {
            clienteDTO.setCpf(clienteDTO.getCpf().replaceAll("\\D", ""));
        }

        ClienteModel cliente = clienteMapper.map(clienteDTO);

        // Preencher data de cadastro se estiver nula
        if (cliente.getDataCadastro() == null) {
            cliente.setDataCadastro(java.time.LocalDate.now());
        }

        // Regra de negócio: CPF válido
        if (!ValidadorCpf.isValid(clienteDTO.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }

        // Regra de negócio: CPF único
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        cliente = clienteRepository.save(cliente);

        return clienteMapper.map(cliente);
    }

    // Listar Clientes
    public List<ClienteDTO> listarClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }

    // Buscar cliente por CPF
    public ClienteDTO buscarPorCpf(String cpf) {
        Optional<ClienteModel> cliente = clienteRepository.findByCpf(cpf);
        return cliente.map(clienteMapper::map).orElse(null);

    }

    // Atualizar cliente parcialmente pelo CPF
    public ClienteDTO atualizarPorCpf(String cpf, ClienteDTO clienteDTO) {

        ClienteModel cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (clienteDTO.getNome() != null && !clienteDTO.getNome().isBlank()) {
            cliente.setNome(clienteDTO.getNome());
        }

        if (clienteDTO.getEmail() != null && !clienteDTO.getEmail().isBlank()) {
            cliente.setEmail(clienteDTO.getEmail());
        }

        ClienteModel clienteSalvo = clienteRepository.save(cliente);
        return clienteMapper.map(clienteSalvo);
    }

    // Deletar cliente por cpf
    public boolean deletarPorCpf(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            clienteRepository.deleteByCpf(cpf);
            return true;
        }
        return false;
    }



    // Buscar cliente por ID (Para uso no Frontend/Web)
    public ClienteDTO buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
    }

    // Atualizar cliente por ID (Para uso no Frontend/Web)
    public ClienteDTO atualizarPorId(Long id, ClienteDTO clienteDTO) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        if (clienteDTO.getCpf() != null) {
            String cpfLimpo = clienteDTO.getCpf().replaceAll("\\D", "");
            clienteDTO.setCpf(cpfLimpo);
            cliente.setCpf(cpfLimpo);
        }

        ClienteModel clienteSalvo = clienteRepository.save(cliente);
        return clienteMapper.map(clienteSalvo);
    }

    // Deletar Cliente por ID
    public void deletarPorId(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }



}
