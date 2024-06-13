package com.festalink.demo.entities.mapper;

import org.springframework.stereotype.Service;

import com.festalink.demo.entities.Proprietario;
import com.festalink.demo.entities.dto.ProprietarioDTO;
import com.festalink.demo.entities.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

@Service
public class ProprietarioMapper {

    public Proprietario mapProprietarioDtoToProprietario(@Valid ProprietarioDTO dto) {
		
		Proprietario proprietario = new Proprietario(dto.getNome(),dto.getEmail(),dto.getSenha(),dto.getTelefone());
		
		return proprietario;
	}

	public UsuarioResponseDTO mapProprietarioToProprietarioResponseDTO(@Valid Proprietario proprietario) {
	
		return new UsuarioResponseDTO(proprietario.getId(),proprietario.getNome(),proprietario.getTelefone(), "Proprietario");
	}

}
