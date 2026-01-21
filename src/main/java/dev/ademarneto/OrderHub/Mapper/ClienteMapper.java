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
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setCpf(clienteModel.getCpf());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setDataCadastro(clienteModel.getDataCadastro());
        clienteDTO.setPedidos(clienteModel.getPedidos());
        return clienteDTO;
    }
}
