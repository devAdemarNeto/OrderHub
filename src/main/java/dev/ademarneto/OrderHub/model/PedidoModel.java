package dev.ademarneto.OrderHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Numero do pedido é obrigatório")
    @Column(name = "numero_pedido", nullable = false,unique = true,length = 20)
    private String numeroPedido;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false,length = 500)
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0,01", message = "Valor deve ser maior que zero")
    @Column(name = "valor_total",nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @NotNull(message = "Data do pedido é obrigatório")
    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClienteModel client;


}
