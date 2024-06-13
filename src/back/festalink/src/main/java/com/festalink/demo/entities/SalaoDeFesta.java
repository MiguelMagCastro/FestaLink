package com.festalink.demo.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "salao_de_festa")
public class SalaoDeFesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "localizacao")
	private String localizacao;

	@Column(name = "capacidade")
	private int capacidade;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco_base")
	private Double precoBase;

	@ManyToOne
	@JoinColumn(name = "idProprietario")
	@JsonManagedReference
	private Proprietario proprietario;

	@OneToMany(mappedBy = "salao")
	@JsonManagedReference
	private Set<Reserva> reserva = new HashSet<>();

	@OneToMany(mappedBy = "salaoDeFesta")
	@JsonIgnore
	private Set<Avaliacao> avaliacoes;

	@OneToMany(mappedBy = "salaoDeFestas")
	@JsonIgnore
	private Set<DataBloqueada> datasBloqueadas;

	public SalaoDeFesta() {
		// TODO Auto-generated constructor stub
	}

	public SalaoDeFesta(String nome, String localizacao, int capacidade, String descricao, Double precoBase,
			Proprietario proprietario) {
		super();
		this.nome = nome;
		this.localizacao = localizacao;
		this.capacidade = capacidade;
		this.descricao = descricao;
		this.precoBase = precoBase;
		this.proprietario = proprietario;
		this.avaliacoes = new HashSet<>();
		this.datasBloqueadas = new HashSet<>();
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

	public int getId() {
		return id;
	}

	public Set<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(Set<Reserva> reserva) {
		this.reserva = reserva;
	}

	public Set<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Set<DataBloqueada> getDatasBloqueadas() {
		return this.datasBloqueadas;
	}

	public void setDatasBloqueadas(Set<DataBloqueada> dataBloqueada) {
		this.datasBloqueadas = dataBloqueada;
	}

	private boolean dataEstaDisponivel(LocalDate data) {
		boolean isDataBloqueada = datasBloqueadas.stream()
				.map(DataBloqueada::getData)
				.anyMatch(dataBloqueadaExistente -> dataBloqueadaExistente.equals(data));

		if (isDataBloqueada) {
			return false;
		}

		return reserva.stream()
				.map(Reserva::getDataEvento)
				.noneMatch(dataReservaExistente -> dataReservaExistente.equals(data));
	}

	public boolean registrarReserva(Reserva reservas) {
		if (dataEstaDisponivel(reservas.getDataEvento()) && !dataEstaBloqueada(reservas.getDataEvento())) {
			reserva.add(reservas);
			return true;
		}
		return false;
	}

	public boolean registraDataBloqueada(DataBloqueada dataParaBloqueio) {
		if (!dataEstaBloqueada(dataParaBloqueio.getData())) {
			this.datasBloqueadas.add(dataParaBloqueio);
			return true;
		}
		return false;
	}

	public boolean removerDataBloqueada(DataBloqueada dataParaDesbloquear) {
		if(this.datasBloqueadas.isEmpty() || this.datasBloqueadas == null){
			return false;
		}
		
		if(!existeReservaNesteDia(dataParaDesbloquear.getData())){
			this.datasBloqueadas.remove(dataParaDesbloquear);
			return true;
		}
		return false;
	
	}

	private boolean dataEstaBloqueada(LocalDate dataParaConferir) {
		return this.datasBloqueadas.stream()
				.anyMatch(dataExistente -> dataExistente.getData().equals(dataParaConferir));
	}

	public boolean cancelarReserva(Reserva reserva, DataBloqueada dataBloqueada) {
		if (this.reserva != null && this.reserva.contains(reserva)) {
			this.reserva.remove(reserva);
			return true;
		}
		return false;
	}

	public boolean existeReservaNesteDia(LocalDate dataParaConferir) {
		for (Reserva a : this.reserva) {
			if (a.getDataEvento().equals(dataParaConferir)) {
				return true;
			}
		}
		return false;
	}

	public int quantidadeReservas() {
		int quantidade = 0;
		for (Reserva res : reserva) {
			if (res.getStatusDaReserva() != StatusDaReserva.Cancelada) {
				quantidade++;
			}
		}
		return quantidade;
	}

	public int quantidadeAvaliacoes() {

		return this.avaliacoes.size();

	}

	public Double taxaCancelamento() {
		Double canceladas = 0.0;
		if (this.reserva.isEmpty() || this.reserva.size() == 0 || this.reserva == null) {
			return 0.0;
		}

		for (Reserva a : this.reserva) {
			if (a.getStatusDaReserva().equals(StatusDaReserva.Cancelada)) {
				canceladas++;
			}
		}
		return (canceladas / this.reserva.size()) * 100;
	}

	public Double mediaAvaliacoes() {
		double mediaSalao = 0.0;
		for (Avaliacao a : this.avaliacoes) {
			mediaSalao += a.getNotaAtribuida();
		}
		return mediaSalao / this.avaliacoes.size();
	}

	public Double valorArrecadado() {
		Double valorArrecado = 0.0;
		for (Reserva res : this.reserva) {
			if (res.getDataEvento().isBefore(LocalDate.now())
					&& res.getStatusDaReserva() == StatusDaReserva.Confirmada) {
				valorArrecado += res.calculaValorDaReserva();
			}
		}
		return valorArrecado;
	}

	public Double ganhosPotenciais() {
		Double ganhosPotenciais = 0.0;
		for (Reserva res : this.reserva) {
			if (!res.getDataEvento().isBefore(LocalDate.now())  && res.getStatusDaReserva() == StatusDaReserva.Confirmada) {
				ganhosPotenciais += res.calculaValorDaReserva();
			}
		}
		return ganhosPotenciais;

	}

	@Override
	public String toString() {
		return "SalaoDeFesta [id = " + id + ", nome = " + nome + ", localizacao = " + localizacao + ", capacidade = "
				+ capacidade + ", descricao = " + descricao + ", precoBase = " + precoBase + ", proprietario = "
				+ proprietario + ", reserva = " + reserva + "]";
	}
}
