package com.festalink.demo.entities.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.entities.dto.ReservaDTO;
import com.festalink.demo.entities.dto.ReservaResponseDTO;
import com.festalink.demo.repositories.ClienteRepository;
import com.festalink.demo.repositories.SalaoDeFestaRepository;

@Service
public class ReservaMapper {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SalaoDeFestaRepository salaoDeFestaRepository;

    public Reserva mapReservaDtoToReserva(ReservaDTO dto) throws DateTimeParseException {
        Cliente cliente = clienteRepository.findById(dto.getClienteId());
        SalaoDeFesta salaoDeFesta = salaoDeFestaRepository.findById(dto.getSalaoId());

        // Cria um DateTimeFormatter para converter a string para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataEvento = LocalDate.parse(dto.getDataEvento(), formatter);

        // Retorna a nova reserva com os dados convertidos para LocalDate
        return new Reserva(cliente, salaoDeFesta, dataEvento, dto.getNumeroConvidados(), dto.getDuracao());
    }

    public ReservaResponseDTO mapReservaToReservaResponseDto(Reserva reserva) {
        return new ReservaResponseDTO(reserva.getId(), reserva.getCliente().getNome(), reserva.getSalao().getNome(),
                reserva.getDataEvento(),reserva.getNumeroConvidados() ,reserva.getDuracao(), reserva.calculaValorDaReserva(), reserva.getStatusDaReserva().toString());
    }

}
