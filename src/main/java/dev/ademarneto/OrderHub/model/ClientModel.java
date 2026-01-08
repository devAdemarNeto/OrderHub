package dev.ademarneto.OrderHub.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_client")
public class ClientModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String nome;


    String cpf;


    String email;


    LocalDate dataCadastro;


    public ClientModel() {
    }

    public ClientModel(String nome, String cpf, String email, LocalDate dataCadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
