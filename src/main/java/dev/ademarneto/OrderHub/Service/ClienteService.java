package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.model.ClienteModel;
import dev.ademarneto.OrderHub.repository.ClienteRepository;
import dev.ademarneto.OrderHub.validation.ValidadorCpf;


import java.util.List;
import java.util.Optional;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clientRepository) {
        this.clienteRepository = clientRepository;
    }

    //Criar um novo cliente
    public ClienteModel criarCliente(ClienteModel client){

        //Regra de negócio: CPF válido
        if(!ValidadorCpf.isValid(client.getCpf())){
            throw new IllegalArgumentException("CPF inválido");
        }

        //Regra de negócio: CPF único
        if(clienteRepository.existsByCpf(client.getCpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        return clienteRepository.save(client);
    }

    //Listar Clientes
    public List<ClienteModel> listarClientes(){
        return clienteRepository.findAll();
    }

    //Buscar cliente por CPF
    public ClienteModel buscarPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf).orElse(null);
    }

    //Atualizar cliente por CPF
    public ClienteModel atualizarPorCpf(String cpf, ClienteModel clienteAtualizado){
        Optional<ClienteModel> clienteExistente = clienteRepository.findByCpf(cpf);

        if (clienteExistente.isPresent()){
            ClienteModel cliente = clienteExistente.get();

            if (clienteAtualizado.getNome() != null && !clienteAtualizado.getNome().isBlank()){
                cliente.setNome(clienteAtualizado.getNome());
            }

            if (clienteAtualizado.getEmail() != null && !clienteAtualizado.getNome().isBlank()){
                cliente.setEmail(clienteAtualizado.getEmail());
            }
            return clienteRepository.save(cliente);
        }
        return null;
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
