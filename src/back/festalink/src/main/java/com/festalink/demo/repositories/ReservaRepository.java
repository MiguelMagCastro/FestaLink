package com.festalink.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festalink.demo.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	public List<Reserva> getReservasBySalaoId(int id);

    public List<Reserva> getReservasByClienteId(int id);

}
