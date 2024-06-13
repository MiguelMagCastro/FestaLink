package com.festalink.demo.services;

import java.util.List;

import com.festalink.demo.entities.Reserva;

public interface ReservaService {

    public List<Reserva> getReservaSalaoDeFesta(int id);

    public List<Reserva> getReservaCliente(int id);

    public Reserva save(Reserva reserva);

    public Reserva getReservaById(int id);

    public void cancelarReserva(int id);

}
