package dev.ademarneto.OrderHub.Service;

import dev.ademarneto.OrderHub.model.ClientModel;
import dev.ademarneto.OrderHub.repository.ClientRepository;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Listar Clientes
    public List<ClientModel> listarClientes(){
        return clientRepository.findAll();
    }




}
