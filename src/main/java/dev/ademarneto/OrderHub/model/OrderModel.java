package dev.ademarneto.OrderHub.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_order")
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

    public OrderModel() {
    }

    public OrderModel(Long id, String numeroPedido, String descricao, BigDecimal valorTolal, LocalDate localPedido, ClientModel client) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.descricao = descricao;
        this.valorTolal = valorTolal;
        this.localPedido = localPedido;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTolal() {
        return valorTolal;
    }

    public void setValorTolal(BigDecimal valorTolal) {
        this.valorTolal = valorTolal;
    }

    public LocalDate getLocalPedido() {
        return localPedido;
    }

    public void setLocalPedido(LocalDate localPedido) {
        this.localPedido = localPedido;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }
}
