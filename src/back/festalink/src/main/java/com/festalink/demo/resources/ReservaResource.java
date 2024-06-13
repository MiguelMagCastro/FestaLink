package com.festalink.demo.resources;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festalink.demo.entities.DataBloqueada;
import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.StatusDaReserva;
import com.festalink.demo.entities.dto.ReservaDTO;
import com.festalink.demo.entities.dto.ReservaResponseDTO;
import com.festalink.demo.entities.mapper.ReservaMapper;
import com.festalink.demo.services.DataBloqueadaService;
import com.festalink.demo.services.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaResource {

    @Autowired
    private ReservaMapper mapper;

    @Autowired
    ReservaService reservaService;

    @Autowired
    DataBloqueadaService dataBloqueadaService;

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> getReservasByClinteId(@PathVariable Integer id) {
        List<Reserva> reservas = reservaService.getReservaCliente(id);

        List<ReservaResponseDTO> reservasResponse = new ArrayList<ReservaResponseDTO>();

        for (Reserva a : reservas) {
            if (a.getStatusDaReserva() != StatusDaReserva.Cancelada) {
                reservasResponse.add(mapper.mapReservaToReservaResponseDto(a));
            }
        }
        return ResponseEntity.ok().body(reservasResponse);
    }

    @GetMapping("/salao/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> getReservasBySalaoId(@PathVariable Integer id) {
        List<Reserva> reservas = reservaService.getReservaSalaoDeFesta(id);

        List<ReservaResponseDTO> reservasResponse = new ArrayList<ReservaResponseDTO>();

        for (Reserva a : reservas) {
            reservasResponse.add(mapper.mapReservaToReservaResponseDto(a));
        }
        return ResponseEntity.ok().body(reservasResponse);
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<String> confirmarReserva(@PathVariable Integer id){
        Reserva reserva = reservaService.getReservaById(id);
        try{
            if(reserva.confirmarReserva()){
                reservaService.save(reserva);
                return ResponseEntity.ok().body("Reserva confirmada com sucesso!");
            }else{
                return ResponseEntity.ok().body("Reserva já estava confirmada!");
            }
        }catch(NullPointerException e){
            return ResponseEntity.ok().body("Reserva não encontrada!");
        }
            
        }


    @PostMapping
    public ResponseEntity<String> registrarReserva(@Valid @RequestBody ReservaDTO dto) {
        try {
            Reserva novaReserva = mapper.mapReservaDtoToReserva(dto);
            if (novaReserva.getSalao().registrarReserva(novaReserva)) {
                novaReserva.getCliente().adicionarReserva(novaReserva);
                reservaService.save(novaReserva);
                dataBloqueadaService.saveDataBloqueada(new DataBloqueada(novaReserva.getDataEvento(), novaReserva.getSalao()));
                return ResponseEntity.ok("Reserva registrada com sucesso!");
            } else {
                return ResponseEntity.ok("Data escolhida não está disponivel!");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Erro de formatação:  O padrão de formatação é inválido.");
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Erro de formatação:  O padrão de formatação é inválido.");
        }

    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Integer id){
        Reserva reserva = reservaService.getReservaById(id);
        try{
            List<DataBloqueada> dataBloqueada = dataBloqueadaService.getDataBloqueadaByidSalaoDeFesta(reserva.getSalao().getId());
            if(dataBloqueada != null && !dataBloqueada.isEmpty()){
                for(DataBloqueada a: dataBloqueada){
                    if(a.getData().equals(reserva.getDataEvento())){
                        reserva.getSalao().cancelarReserva(reserva, a);
                        dataBloqueadaService.liberarData(a);
                        reservaService.cancelarReserva(id);
                        return ResponseEntity.ok().body("Reserva Cancelada!");
                    }
                }
            }
           

          
           
            
            return ResponseEntity.ok().body("Reserva não encontrada!");
        }catch(IllegalArgumentException e) {
            return ResponseEntity.ok().body("Reserva não encontrada!");
        }
        
    }


}
