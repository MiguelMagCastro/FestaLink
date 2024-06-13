package com.festalink.demo.entities.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvaliacaoResponseDTO {
    
    private int id;

    private String usuarioNome;

    private double notaAtribuida;

    private String comentario;
    
    private LocalDateTime horaAvaliacao;

    public AvaliacaoResponseDTO() {
    }




    public AvaliacaoResponseDTO(int id, String usuarioNome, double notaAtribuida, String comentario, LocalDateTime horaAvaliacao) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.notaAtribuida = notaAtribuida;
        this.comentario = comentario;
        this.horaAvaliacao = horaAvaliacao;
    }



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuarioNome() {
        return this.usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public double getNotaAtribuida() {
        return this.notaAtribuida;
    }

    public void setNotaAtribuida(double notaAtribuida) {
        this.notaAtribuida = notaAtribuida;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getHoraAvaliacao() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return this.horaAvaliacao.format(formatter);
}

    public void setHoraAvaliacao(LocalDateTime horaAvaliacao) {
        this.horaAvaliacao = horaAvaliacao;
    }

}
