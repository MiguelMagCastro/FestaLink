package com.festalink.demo.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "telefone", nullable = false)
    private String telefone;

	@Column(name = "tipo", nullable = false)
    private String tipoUsuario;

    // Constructor
    public Usuario(String nome, String email, String senha, String telefone, String tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
		this.tipoUsuario = tipoUsuario;
    }

    // Default constructor for JPA
    protected Usuario() {}


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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
    
	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			", email='" + getEmail() + "'" +
			", telefone='" + getTelefone() + "'" +
			"}";
	}

}
