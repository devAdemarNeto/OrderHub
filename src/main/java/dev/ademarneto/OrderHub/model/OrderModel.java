package dev.ademarneto.OrderHub.model;

import jakarta.persistence.*;
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
public class OrderModel {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroPedido;
    private String descricao;
    private BigDecimal valorTolal;
    private LocalDate localPedido;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;


}
