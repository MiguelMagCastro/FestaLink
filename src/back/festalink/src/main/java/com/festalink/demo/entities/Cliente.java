package com.festalink.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    @JsonIgnore
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private Set<Avaliacao> avaliacoes = new HashSet<>();

    // Constructor
    public Cliente(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone,"Cliente");
    }

    // Default constructor for JPA
    protected Cliente() {}


    public Set<Reserva> getReservas() {
        return this.reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void adicionarReserva(Reserva reserva){
        this.reservas.add(reserva);
    }


    @Override
    public String toString() {
        return "{" +
            " reservas='" + getReservas() + "'" +
            "}";
    }


}
