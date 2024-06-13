package com.festalink.demo.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idCliente" , referencedColumnName = "id")
	@JsonManagedReference
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idSalaoDeFesta", referencedColumnName = "id" )
	@JsonManagedReference
	private SalaoDeFesta salao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "data_do_evento")
	private LocalDate dataEvento;
	
	@Column(name = "status_da_reserva")
	private StatusDaReserva statusDaReserva;
	
	@Column(name = "numero_convidados")
	private int numeroConvidados;

	@Column(name = "duracao")
	private int duracao;
	
	public Reserva() {
		// TODO Auto-generated constructor stub
	}


	
	public Reserva(Cliente cliente, SalaoDeFesta salao, LocalDate dataEvento, int numeroConvidados,  int duracao) {
		super();
		this.cliente = cliente;
		this.salao = salao;
		this.dataEvento = dataEvento;
		this.statusDaReserva = StatusDaReserva.Pendente;
		this.numeroConvidados = numeroConvidados;
		this.duracao = duracao;
	}



	public int getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SalaoDeFesta getSalao() {
		return salao;
	}

	public void setSalao(SalaoDeFesta salao) {
		this.salao = salao;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public StatusDaReserva getStatusDaReserva() {
		return statusDaReserva;
	}

	public void setStatusDaReserva(StatusDaReserva statusDaReserva) {
		this.statusDaReserva = statusDaReserva;
	}

	public int getNumeroConvidados() {
		return this.numeroConvidados;
	}

	public void setNumeroConvidados(int numeroConvidados) {
		this.numeroConvidados = numeroConvidados;
	}

	public int getDuracao() {
		return this.duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	public Double calculaValorDaReserva(){
		return this.salao.getPrecoBase() * this.duracao;
	}

	public boolean confirmarReserva(){
		if(this.statusDaReserva != StatusDaReserva.Confirmada){
			this.statusDaReserva = StatusDaReserva.Confirmada;
			return true;
		}
		return false;
	}



	@Override
	public String toString() {
		return "Reserva [id = " + id + ", cliente = " + cliente + ", salao = " + salao + ", dataEvento = " + dataEvento
				+ ", statusDaReserva = " + statusDaReserva + "]";
	}
}


