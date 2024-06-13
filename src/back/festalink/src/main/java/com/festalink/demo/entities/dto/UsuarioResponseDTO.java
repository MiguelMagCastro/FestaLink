package com.festalink.demo.entities.dto;

public class UsuarioResponseDTO {
    private int id;

    private String nome;

    private String telefone;

    private String tipoUsuario;


    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(int id, String nome, String telefone, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
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

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


}
