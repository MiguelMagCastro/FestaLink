package com.festalink.demo.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {

    @NotNull(message = "O campo dataEvento nâo pode ser nulo")
	@NotEmpty(message = "O campo dataEvento nâo pode estar vazio")
	@NotBlank(message = "O campo dataEvento nâo pode estar em branco")
    private String dataEvento;

    @NotNull(message = "O campo salaoId nâo pode ser nulo")
    private int salaoId;

    @NotNull(message = "O campo clienteId nâo pode ser nulo")
    private int clienteId;

    @NotNull(message = "O campo dataEvento nâo pode ser nulo")
    private int numeroConvidados;

    @NotNull(message = "O campo dataEvento nâo pode ser nulo")
    private int duracao;

    public ReservaDTO() {
    }

    public ReservaDTO(String dataEvento, int salaoId, int clienteId, int duracao) {
        this.dataEvento = dataEvento;
        this.salaoId = salaoId;
        this.clienteId = clienteId;
        this.duracao = duracao;
    }

    public String getDataEvento() {
        return this.dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getSalaoId() {
        return this.salaoId;
    }

    public void setSalaoId(int salaoId) {
        this.salaoId = salaoId;
    }

    public int getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getNumeroConvidados() {
        return this.numeroConvidados;
    }

    public void setNumeroConvidados(int numeroConvidados) {
        this.numeroConvidados = numeroConvidados;
    }


    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    @Override
    public String toString() {
        return "{" +
            " dataEvento='" + getDataEvento() + "'" +
            ", salaoId='" + getSalaoId() + "'" +
            ", clienteId='" + getClienteId() + "'" +
            "}";
    }

}
