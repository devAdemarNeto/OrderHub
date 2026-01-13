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

    //Listar Clientes
    public List<ClientModel> listarClientes(){
        return clientRepository.findAll();
    }

    //Listar Clientes por Id
    public ClientModel listarClientesID(Long id){
        Optional<ClientModel> clientID = clientRepository.findById(id);
        return clientID.orElse(null);
    }




}
