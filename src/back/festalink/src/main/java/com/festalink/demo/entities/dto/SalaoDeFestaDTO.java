package com.festalink.demo.entities.dto;

import com.festalink.demo.entities.Proprietario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SalaoDeFestaDTO {

	private int id;
	
	@NotNull(message = "O campo nome nâo pode ser nulo")
	@NotEmpty(message = "O campo nome nâo pode estar vazio")
	@NotBlank(message = "O campo nome nâo pode estar em branco")
	private String nome;
	
	@NotNull(message = "O campo localizacao nâo pode ser nulo")
	@NotEmpty(message = "O campo localizacao nâo pode estar vazio")
	@NotBlank(message = "O campo localizacao nâo pode estar em branco")
	private String localizacao;
	
	@NotNull(message = "O campo capacidade nâo pode ser nulo")
	private int capacidade;
	
	
	private String descricao;
	
	@NotNull(message = "O campo precoBase nâo pode ser nulo")
	private Double precoBase;
	
	@NotNull(message = "O campo proprietario nâo pode ser nulo")
	 private Proprietario proprietario;
	
	public SalaoDeFestaDTO() {
		// TODO Auto-generated constructor stub
	}

	public SalaoDeFestaDTO(String nome, String localizacao, int capacidade, String descricao, Double precoBase,
	Proprietario proprietario) {
		super();
		this.nome = nome;
		this.localizacao = localizacao;
		this.capacidade = capacidade;
		this.descricao = descricao;
		this.precoBase = precoBase;
		this.proprietario = proprietario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(Double precoBase) {
		this.precoBase = precoBase;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	
	
	
	
	
}
