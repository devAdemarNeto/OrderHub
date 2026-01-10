package dev.ademarneto.OrderHub.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String cpf;


    private String email;


    private LocalDate dataCadastro;


    @OneToMany (mappedBy = "client")
    private List<OrderModel> order;
}
