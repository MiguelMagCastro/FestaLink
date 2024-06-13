package com.festalink.demo.entities.dto;

public class MetricasResponseDTO {
    
    private Double taxaCancelamento;

    private int numeroAvaliacoes;

    private Double mediaAvaliacoes;

    private Double valorArrecadado;

    private Double valorAReceber;

    private SalaoDeFestaResponseDTO melhorSalaoDeFesta;

    private int numeroReservasMelhorSalao;

    public MetricasResponseDTO() {
    }

    public MetricasResponseDTO(Double taxaCancelamento, int numeroAvaliacoes, Double avaliacoes, Double valorArrecadado, Double valorAReceber, SalaoDeFestaResponseDTO salaoDeFesta, int numeroReservasMelhorSalao) {
        this.taxaCancelamento = taxaCancelamento;
        this.numeroAvaliacoes = numeroAvaliacoes;
        this.mediaAvaliacoes = avaliacoes;
        this.valorArrecadado = valorArrecadado;
        this.valorAReceber = valorAReceber;
        this.melhorSalaoDeFesta = salaoDeFesta;
        this.numeroReservasMelhorSalao = numeroReservasMelhorSalao;
    }


    public Double getTaxaCancelamento() {
        return this.taxaCancelamento;
    }

    public void setTaxaCancelamento(Double taxaCancelamento) {
        this.taxaCancelamento = taxaCancelamento;
    }


    public int getNumeroAvaliacoes() {
        return this.numeroAvaliacoes;
    }

    public void setNumeroAvaliacoes(int numeroAvaliacoes) {
        this.numeroAvaliacoes = numeroAvaliacoes;
    }


    public Double getMediaAvaliacoes() {
        return this.mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(Double avaliacoes) {
        this.mediaAvaliacoes = avaliacoes;
    }

    public Double getValorArrecadado() {
        return this.valorArrecadado;
    }

    public void setValorArrecadado(Double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public Double getValorAReceber() {
        return this.valorAReceber;
    }

    public void setValorAReceber(Double valorAReceber) {
        this.valorAReceber = valorAReceber;
    }

    public SalaoDeFestaResponseDTO getMelhorSalaoDeFesta() {
        return this.melhorSalaoDeFesta;
    }

    public void setMelhorSalaoDeFesta(SalaoDeFestaResponseDTO salaoDeFesta) {
        this.melhorSalaoDeFesta = salaoDeFesta;
    }


    public int getNumeroReservasMelhorSalao() {
        return this.numeroReservasMelhorSalao;
    }

    public void setNumeroReservasMelhorSalao(int numeroReservasMelhorSalao) {
        this.numeroReservasMelhorSalao = numeroReservasMelhorSalao;
    }



    @Override
    public String toString() {
        return "{" +
            " taxaCancelamento='" + getTaxaCancelamento() + "'" +
            ", avaliacoes='" + getMediaAvaliacoes() + "'" +
            ", valorArrecadado='" + getValorArrecadado() + "'" +
            ", valorAReceber='" + getValorAReceber() + "'" +
            ", salaoDeFesta='" + getMelhorSalaoDeFesta() + "'" +
            "}";
    }


}
