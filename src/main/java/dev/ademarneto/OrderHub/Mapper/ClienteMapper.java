package dev.ademarneto.OrderHub.Mapper;

import dev.ademarneto.OrderHub.DTO.ClienteDTO;
import dev.ademarneto.OrderHub.model.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteModel map(ClienteDTO clienteDTO){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setCpf(clienteDTO.getCpf());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setDataCadastro(clienteDTO.getDataCadastro());
        clienteModel.setPedidos(clienteDTO.getPedidos());

        return clienteModel;
    }

    public ClienteDTO map(ClienteModel clienteModel){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteDTO.getId());
        clienteDTO.setNome(clienteDTO.getNome());
        clienteDTO.setCpf(clienteDTO.getCpf());
        clienteDTO.setEmail(clienteDTO.getEmail());
        clienteDTO.setDataCadastro(clienteDTO.getDataCadastro());
        clienteDTO.setPedidos(clienteDTO.getPedidos());
        return clienteDTO;
    }
}
