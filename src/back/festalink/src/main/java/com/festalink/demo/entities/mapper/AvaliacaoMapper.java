package com.festalink.demo.entities.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Avaliacao;
import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.entities.dto.AvaliacaoDTO;
import com.festalink.demo.entities.dto.AvaliacaoResponseDTO;
import com.festalink.demo.repositories.ClienteRepository;
import com.festalink.demo.repositories.ProprietarioRepository;
import com.festalink.demo.repositories.SalaoDeFestaRepository;

import jakarta.validation.Valid;

@Service
public class AvaliacaoMapper {
    
    @Autowired
    SalaoDeFestaRepository salaoRepositorie;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProprietarioRepository proprietarioRepository;

    public Avaliacao mapAvaliacaoDtoToAvaliacao(@Valid AvaliacaoDTO dto) {
        SalaoDeFesta salaoDeFesta = salaoRepositorie.getReferenceById(dto.getSalaoDeFesta());
        
        Cliente cliente = clienteRepository.getReferenceById(dto.getUsuario());
       
        return new Avaliacao(cliente,salaoDeFesta,dto.getNotaAtribuida(),dto.getComentario());
        
	}

    public AvaliacaoResponseDTO mapAvaliacaoToAvaliacaoResponseDTO(Avaliacao avaliacao) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(avaliacao.getId());
        dto.setUsuarioNome(avaliacao.getCliente().getNome());
        dto.setNotaAtribuida(avaliacao.getNotaAtribuida());
        dto.setComentario(avaliacao.getComentario());
        dto.setHoraAvaliacao(avaliacao.getHoraAvaliacao());
        return dto;
    }


}
