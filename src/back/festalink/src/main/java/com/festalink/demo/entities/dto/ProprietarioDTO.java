package com.festalink.demo.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProprietarioDTO {
	
	@NotNull(message = "O campo nome nâo pode ser nulo")
	@NotEmpty(message = "O campo nome nâo pode estar vazio")
	@NotBlank(message = "O campo nome nâo pode estar em branco")
	@Size(max = 255)
	private String nome;
	
	@NotNull(message = "O campo email nâo pode ser nulo")
	@NotEmpty(message = "O campo email nâo pode estar vazio")
	@NotBlank(message = "O campo email nâo pode estar em branco")
	@Size(max = 255)
	private String email;

	@NotNull(message = "O campo senha nâo pode ser nulo")
	@NotEmpty(message = "O campo senha nâo pode estar vazio")
	@NotBlank(message = "O campo senha nâo pode estar em branco")
	@Size(max = 255, min = 8, message = "Sua senha deve ter no minimo 8 caracteres")
	private String senha;
	
	@NotNull(message = "O campo telefone nâo pode ser nulo")
	@NotEmpty(message = "O campo telefone nâo pode estar vazio")
	@NotBlank(message = "O campo telefone nâo pode estar em branco")
	@Size(max = 15)
	private String telefone;


	public ProprietarioDTO() {
	}


	public ProprietarioDTO(String nome, String email, String senha, String telefone) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
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




}
