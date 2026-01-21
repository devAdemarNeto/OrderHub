package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.DTO.ClienteDTO;
import dev.ademarneto.OrderHub.Mapper.ClienteMapper;
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

    //Criar um novo cliente
    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
        ClienteModel cliente = clienteMapper.map(clienteDTO);

        //Regra de negócio: CPF válido
        if(!ValidadorCpf.isValid(clienteDTO.getCpf())){
            throw new IllegalArgumentException("CPF inválido");
        }

        //Regra de negócio: CPF único
        if(clienteRepository.existsByCpf(clienteDTO.getCpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        cliente = clienteRepository.save(cliente);

        return clienteMapper.map(cliente);
    }

    //Listar Clientes
    public List<ClienteDTO> listarClientes(){
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }

    //Buscar cliente por CPF
    public ClienteDTO buscarPorCpf(String cpf){
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


    //Deletar cliente por cpf
    public boolean deletarPorCpf(String cpf){
        if (clienteRepository.existsByCpf(cpf)){
            clienteRepository.deleteByCpf(cpf);
            return true;
        }
        return false;
    }





    // USO INTERNO (NÃO EXPOR NA API)

    //Listar Clientes por Id
    public ClienteModel listarClientesID(Long id){
        Optional<ClienteModel> clientID = clienteRepository.findById(id);
        return clientID.orElse(null);
    }

    //Deletar Cliente por ID
    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    //Atualizar Pedido por ID
    public ClienteModel atualizarCliente(Long id, ClienteModel clienteAtualizado){
        if(clienteRepository.existsById(id)){
            return clienteRepository.save(clienteAtualizado);
        }
        return null;
    }


}
