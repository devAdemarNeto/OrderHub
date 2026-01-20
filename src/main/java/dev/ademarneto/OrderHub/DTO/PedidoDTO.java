package dev.ademarneto.OrderHub.DTO;

import dev.ademarneto.OrderHub.model.ClienteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private String numeroPedido;
    private String descricao;
    private BigDecimal valorTotal;
    private LocalDate dataPedido;
    private ClienteModel cliente;
}
