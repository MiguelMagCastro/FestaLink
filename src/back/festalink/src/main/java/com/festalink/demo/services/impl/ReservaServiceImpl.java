package com.festalink.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.StatusDaReserva;
import com.festalink.demo.repositories.ReservaRepository;
import com.festalink.demo.services.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

        @Autowired
        ReservaRepository reservaRepository;

        public List<Reserva> getReservaSalaoDeFesta(int id) {
                return reservaRepository.getReservasBySalaoId(id);
        }

        public List<Reserva> getReservaCliente(int id) {
                return reservaRepository.getReservasByClienteId(id);
        }

        public Reserva save(Reserva reserva) {
                return reservaRepository.save(reserva);
        }

        @SuppressWarnings("deprecation")
        public Reserva getReservaById(int id){
                return reservaRepository.getById(id);        
        }

        public void atualizarReserva(Reserva reserva){
                reservaRepository.save(reserva);
        }

        @Override
        public void cancelarReserva(int id) {
                Optional<Reserva> reservaOptional = reservaRepository.findById(id);
                if(reservaOptional.isPresent()){
                        Reserva reserva = reservaOptional.get();
                        reserva.setStatusDaReserva(StatusDaReserva.Cancelada);
                        reservaRepository.save(reserva);
                }

        }

        }

