package dev.ademarneto.OrderHub.dto;


import dev.ademarneto.OrderHub.model.PedidoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataCadastro;
    private List<PedidoModel> pedidos;
}
