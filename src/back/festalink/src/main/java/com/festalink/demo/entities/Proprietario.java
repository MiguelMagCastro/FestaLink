package com.festalink.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proprietario")
public class Proprietario extends Usuario {

    @OneToMany(mappedBy = "proprietario", orphanRemoval = true)
    @JsonIgnore
    private Set<SalaoDeFesta> saloesDeFesta = new HashSet<>();

    
    public Proprietario(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone, "Proprietario");
    }

    
    protected Proprietario() {}


    public Set<SalaoDeFesta> getSaloesDeFesta() {
        return this.saloesDeFesta;
    }

    public void setSaloesDeFesta(Set<SalaoDeFesta> saloesDeFesta) {
        this.saloesDeFesta = saloesDeFesta;
    }
    

    @Override
    public String toString() {
        return "{" +
            " saloesDeFesta='" + getSaloesDeFesta() + "'" +
            "}";
    }



}
