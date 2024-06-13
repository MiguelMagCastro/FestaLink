package com.festalink.demo.entities;



import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class Avaliacao{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @ManyToOne
	@JoinColumn(name = "idUsuario")
	@JsonManagedReference
    private Cliente cliente;

    @ManyToOne
	@JoinColumn(name = "idSalaoDeFesta")
	@JsonManagedReference
    private SalaoDeFesta salaoDeFesta;

    @Column(name = "nota", nullable = false)
    private Double notaAtribuida;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
    @Column(name = "horaAvaliacao", nullable = false)
    private LocalDateTime horaAvaliacao;

     public Avaliacao(){}


    public Avaliacao(Cliente usuario, SalaoDeFesta salaoDeFesta, Double notaAtribuida, String cometario) {
        this.cliente = usuario;
        this.salaoDeFesta = salaoDeFesta;
        this.notaAtribuida = notaAtribuida;
        this.comentario = cometario;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente usuario) {
        this.cliente = usuario;
    }

    public SalaoDeFesta getSalaoDeFesta() {
        return this.salaoDeFesta;
    }

    public void setSalaoDeFesta(SalaoDeFesta salaoDeFesta) {
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
    

    public LocalDateTime getHoraAvaliacao() {
        return this.horaAvaliacao;
    }


}