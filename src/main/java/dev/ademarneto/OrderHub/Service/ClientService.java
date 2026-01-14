package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.model.ClientModel;
import dev.ademarneto.OrderHub.model.OrderModel;
import dev.ademarneto.OrderHub.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Criar um novo cliente
    public ClientModel criarCliente(ClientModel client){
        return clientRepository.save(client);
    }

    //Listar Clientes
    public List<ClientModel> listarClientes(){
        return clientRepository.findAll();
    }

    //Listar Clientes por Id
    public ClientModel listarClientesID(Long id){
        Optional<ClientModel> clientID = clientRepository.findById(id);
        return clientID.orElse(null);
    }

    //Deletar Cliente
    public void deletarCliente(Long id){
        clientRepository.deleteById(id);
    }

    //Atualizar Pedido
    public ClientModel atualizarCliente(Long id, ClientModel  clienteAtualizado){
        if(clientRepository.existsById(id)){
            return clientRepository.save(clienteAtualizado);
        }
        return null;
    }


}
