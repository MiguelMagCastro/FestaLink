package com.festalink.demo.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festalink.demo.entities.Proprietario;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.entities.Usuario;
import com.festalink.demo.entities.dto.LoginDTO;
import com.festalink.demo.entities.dto.MetricasResponseDTO;
import com.festalink.demo.entities.dto.ProprietarioDTO;
import com.festalink.demo.entities.dto.SalaoDeFestaResponseDTO;
import com.festalink.demo.entities.mapper.ProprietarioMapper;
import com.festalink.demo.entities.mapper.SalaoDeFestaMapper;
import com.festalink.demo.services.impl.ProprietarioServiceImpl;
import com.festalink.demo.services.impl.SalaoDeFestaServiceImpl;
import com.festalink.demo.utils.EncryptionUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioResource {
    @Autowired
    private ProprietarioServiceImpl proprietarioService;

    @Autowired
    private SalaoDeFestaServiceImpl salaoService;

    @Autowired
    private ProprietarioMapper mapper;

    @Autowired
    private SalaoDeFestaMapper salaoMapper;

    @GetMapping
    public ResponseEntity<List<Proprietario>> getAll() {
        List<Proprietario> proprietarios = proprietarioService.findAll();
        return ResponseEntity.ok().body(proprietarios);
    }

    @GetMapping("/salao-de-festa/{id}")
    public ResponseEntity<List<SalaoDeFestaResponseDTO>> getSalaoDeFestaByProprietarioId(@PathVariable int id) {
        List<SalaoDeFesta> saloesDeFestas = proprietarioService.findSaloesByProprietarioId(id);
        List<SalaoDeFestaResponseDTO> salaoDeFestaResponseDTO = new ArrayList<>();

        for (SalaoDeFesta a : saloesDeFestas) {
            salaoDeFestaResponseDTO.add(salaoMapper.mapSalaoDeFestaToSalaoDeFestaResponseDTO(a));
        }

        return ResponseEntity.ok().body(salaoDeFestaResponseDTO);
    }

    @GetMapping("/metricas/{id}")
    public ResponseEntity<MetricasResponseDTO> getMetricas(@PathVariable int id) {
        List<SalaoDeFesta> saloesDeFestas = proprietarioService.findSaloesByProprietarioId(id);

        Double taxaCancelamento = 0.0;
        Double avaliacoes = 0.0;
        Double valorArrecadado = 0.0;
        Double ganhosPotenciais = 0.0;

        int maiorNumeroDeReservas = -1;
        int auxReserva = 0;
        int auxAvaliacoes = 0;

        SalaoDeFesta salaoFestaMaisReservado = new SalaoDeFesta();

        for (SalaoDeFesta sal : saloesDeFestas) {
            if (sal.quantidadeReservas() > 0) {
                auxReserva++;
                taxaCancelamento += sal.taxaCancelamento();
                if (sal.quantidadeReservas() > maiorNumeroDeReservas) {
                    maiorNumeroDeReservas = sal.quantidadeReservas();
                    salaoFestaMaisReservado = sal;
                }
            }

            if (sal.quantidadeAvaliacoes() > 0) {
                auxAvaliacoes++;
                avaliacoes += sal.mediaAvaliacoes();
            }

            valorArrecadado += sal.valorArrecadado();
            ganhosPotenciais += sal.ganhosPotenciais();
        }

        if (salaoFestaMaisReservado.getProprietario() == null) {
            salaoFestaMaisReservado = saloesDeFestas.get(0);
        }

        int reservas = salaoFestaMaisReservado.quantidadeReservas();

        SalaoDeFestaResponseDTO salaoFesta = salaoMapper
                .mapSalaoDeFestaToSalaoDeFestaResponseDTO(salaoFestaMaisReservado);

        Double cancelamento = taxaCancelamento / auxReserva;

        avaliacoes = avaliacoes / auxAvaliacoes;

        if (avaliacoes.isNaN()) {
            avaliacoes = 0.0;
        }

        if (cancelamento.isNaN()) {
            cancelamento = 0.0;
        }

        MetricasResponseDTO metricas = new MetricasResponseDTO(cancelamento, auxAvaliacoes, avaliacoes, valorArrecadado,
                ganhosPotenciais, salaoFesta, reservas);

        return ResponseEntity.ok().body(metricas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> getById(@PathVariable int id) {
        Proprietario proprietario = proprietarioService.findById(id);
        return ResponseEntity.ok().body(proprietario);
    }

    @PostMapping
    public ResponseEntity<Proprietario> create(@Valid @RequestBody ProprietarioDTO dto) throws URISyntaxException {
        try {
            Proprietario novoProprietario = mapper.mapProprietarioDtoToProprietario(dto);

            novoProprietario = proprietarioService.saveWithEncryptedPassword(novoProprietario);

            return ResponseEntity.created(new URI("/proprietarios/salva" + novoProprietario.getId()))
                    .body(novoProprietario);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException(e, "Erro");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        salaoService.deleteByProprietarioId(id);
        proprietarioService.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {
        Usuario usuario = proprietarioService.findByEmail(dto.getEmail());
        dto.setTipoUsuario("Proprietario");
        if (usuario != null && EncryptionUtils.matchPassword(dto.getSenha(), usuario.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido como " + dto.getTipoUsuario() + "!" + " id= " + usuario.getId()
                    + " nome= " + usuario.getNome() + " email= " + usuario.getEmail() + " senha= " + usuario.getSenha()
                    + " telefone= " + usuario.getTelefone());
        } else {
            return ResponseEntity.badRequest().body("Usuário ou senha informados são inválidos");
        }

    }

}
