package com.festalink.demo.entities.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AvaliacaoDTO {

    @NotNull(message = "O usuario nâo pode ser nulo")
    private int usuario;

    @NotNull(message = "O salão de festa nâo pode ser nulo")
    private int salaoDeFesta;

    @NotNull(message = "O campo nota nâo pode ser nulo")
    private Double notaAtribuida;

    @NotNull(message = "O campo cometario nâo pode ser nulo")
    @NotEmpty(message = "O campo cometario nâo pode estar vazio")
    @NotBlank(message = "O campo cometario nâo pode estar em branco")
    @Size(max = 500)
    private String comentario;


    public AvaliacaoDTO() {
    }



    public AvaliacaoDTO(int usuario, int salaoDeFesta, Double notaAtribuida, String cometario) {
        this.usuario = usuario;
        this.salaoDeFesta = salaoDeFesta;
        this.notaAtribuida = notaAtribuida;
        this.comentario = cometario;
    }


    public int getUsuario() {
        return this.usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getSalaoDeFesta() {
        return this.salaoDeFesta;
    }

    public void setSalaoDeFesta(int salaoDeFesta) {
        this.salaoDeFesta = salaoDeFesta;
    }

    public Double getNotaAtribuida() {
        return this.notaAtribuida;
    }

    public void setNotaAtribuida(Double notaAtribuida) {
        this.notaAtribuida = notaAtribuida;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String cometario) {
        this.comentario = cometario;
    }




}
