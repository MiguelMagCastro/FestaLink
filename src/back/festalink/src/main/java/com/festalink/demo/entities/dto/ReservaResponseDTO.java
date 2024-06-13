package com.festalink.demo.entities.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaResponseDTO {
    
    int id;

    String clienteNome;

    String salaoDeFestaNome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    private int numeroConvidados;

    private int duracao;

    private Double preco;

    private String statusReserva;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(int id, String clienteNome, String salaoDeFestaNome, LocalDate dataEvento, int numeroConvidados, int duracao,  Double preco, String statusReserva) {
        this.id = id;
        this.clienteNome = clienteNome;
        this.salaoDeFestaNome = salaoDeFestaNome;
        this.dataEvento = dataEvento;
        this.numeroConvidados = numeroConvidados;
        this.duracao = duracao;
        this.preco = preco;
        this.statusReserva = statusReserva;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteNome() {
        return this.clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getSalaoDeFestaNome() {
        return this.salaoDeFestaNome;
    }

    public void setSalaoDeFestaNome(String salaoDeFestaNome) {
        this.salaoDeFestaNome = salaoDeFestaNome;
    }

    public LocalDate getDataEvento() {
        return this.dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    public int getNumeroConvidados() {
        return this.numeroConvidados;
    }

    public void setNumeroConvidados(int numeroConvidados) {
        this.numeroConvidados = numeroConvidados;
    }

    public Double getPreco(){
        return this.preco;
    }

    public String getStatusReserva(){
        return this.statusReserva;
    }

    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", clienteNome='" + getClienteNome() + "'" +
            ", salaoDeFestaNome='" + getSalaoDeFestaNome() + "'" +
            ", dataEvento='" + getDataEvento() + "'" +
            "}";
    }

}
