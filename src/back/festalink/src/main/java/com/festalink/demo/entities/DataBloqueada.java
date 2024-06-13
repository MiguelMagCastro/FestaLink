package com.festalink.demo.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "datas_restringidas")
public class DataBloqueada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private LocalDate data;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "idSalaoDeFesta")
    private SalaoDeFesta salaoDeFestas;

    public DataBloqueada() {
    }

    public DataBloqueada(LocalDate data, SalaoDeFesta salaoDeFesta) {
        this.data = data;
        this.salaoDeFestas = salaoDeFesta;
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setSalaoDeFesta(SalaoDeFesta salaoDeFesta) {
        this.salaoDeFestas = salaoDeFesta;
    }

    public SalaoDeFesta getSalaoDeFestas() {
        return this.salaoDeFestas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DataBloqueada that = (DataBloqueada) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(salaoDeFestas, that.salaoDeFestas);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", data='" + getData() + "'" +
                "}";
    }

}
