package com.festalink.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festalink.demo.entities.Avaliacao;
import com.festalink.demo.entities.dto.AvaliacaoDTO;
import com.festalink.demo.entities.dto.AvaliacaoResponseDTO;
import com.festalink.demo.entities.mapper.AvaliacaoMapper;
import com.festalink.demo.services.impl.AvaliacaoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {

    @Autowired
    private AvaliacaoServiceImpl avaliacaoService;

    @Autowired
    private AvaliacaoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> getAvaliacoesBySalaoID(@PathVariable int id) {
        List<Avaliacao> avaliacoes = avaliacaoService.findBySalaoDeFesta(id);
        List<AvaliacaoResponseDTO> resultado = avaliacoes.stream()
                .map(avaliacao -> mapper.mapAvaliacaoToAvaliacaoResponseDTO(avaliacao))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> registrarAvaliacao(@Valid @RequestBody AvaliacaoDTO dto) {
        Avaliacao novaAvaliacao = mapper.mapAvaliacaoDtoToAvaliacao(dto);

        novaAvaliacao = avaliacaoService.saveAvaliacao(novaAvaliacao);

        AvaliacaoResponseDTO resultado = mapper.mapAvaliacaoToAvaliacaoResponseDTO(novaAvaliacao);

        return ResponseEntity.ok().body(resultado);
    }
}
 