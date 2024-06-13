package com.festalink.demo.entities.dto;

public class SalaoDeFestaResponseDTO {
    

    private int id;

    private String nome;

    private String localizacao;

    private int capacidade;

    private String descricao;

    private Double PrecoBase;

    private UsuarioResponseDTO usuario;


    public SalaoDeFestaResponseDTO() {
    }

    public SalaoDeFestaResponseDTO(int id, String nome, String localizacao, int capacidade, String descricao, Double PrecoBase, UsuarioResponseDTO usuario) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
        this.descricao = descricao;
        this.PrecoBase = PrecoBase;
        this.usuario = usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoBase() {
        return this.PrecoBase;
    }

    public void setPrecoBase(Double PrecoBase) {
        this.PrecoBase = PrecoBase;
    }

    public UsuarioResponseDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
    


}
