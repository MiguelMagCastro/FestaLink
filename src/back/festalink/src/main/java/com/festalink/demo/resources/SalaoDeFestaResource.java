package com.festalink.demo.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.festalink.demo.entities.DataBloqueada;
import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.SalaoDeFesta;
import com.festalink.demo.entities.dto.DataDTO;
import com.festalink.demo.entities.dto.SalaoDeFestaDTO;
import com.festalink.demo.entities.dto.SalaoDeFestaResponseDTO;
import com.festalink.demo.entities.mapper.SalaoDeFestaMapper;
import com.festalink.demo.services.DataBloqueadaService;
import com.festalink.demo.services.impl.SalaoDeFestaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/salao-de-festa")
public class SalaoDeFestaResource {
	@Autowired
	private SalaoDeFestaServiceImpl salaoDeFestaService;

	@Autowired
	private SalaoDeFestaMapper mapper;

	@Autowired
	private DataBloqueadaService dataBloqueadaService;

	@GetMapping
	public ResponseEntity<List<SalaoDeFestaResponseDTO>> getSalaoDeFesta() {
		List<SalaoDeFesta> listaDeSalaoDeFesta = salaoDeFestaService.getSalaoDeFesta();

		List<SalaoDeFestaResponseDTO> listaDeSalaoDeFestaResponseDTO = new ArrayList<SalaoDeFestaResponseDTO>();

		for (SalaoDeFesta a : listaDeSalaoDeFesta) {
			listaDeSalaoDeFestaResponseDTO.add(mapper.mapSalaoDeFestaToSalaoDeFestaResponseDTO(a));
		}

		return ResponseEntity.ok().body(listaDeSalaoDeFestaResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SalaoDeFestaResponseDTO> findSalaoDeFestaById(@PathVariable Integer id) {
		SalaoDeFesta salaoDeFesta = salaoDeFestaService.findById(id);
		SalaoDeFestaResponseDTO salaoDeFestaResponseDTO = mapper.mapSalaoDeFestaToSalaoDeFestaResponseDTO(salaoDeFesta);
		
		return ResponseEntity.ok().body(salaoDeFestaResponseDTO);
	}

	@GetMapping("/nome")
	public ResponseEntity<List<SalaoDeFestaResponseDTO>> findSalaoDeFestaByNome(@RequestParam String nome) {
		List<SalaoDeFesta> salaoDeFesta = salaoDeFestaService.findByNome(nome);

		List<SalaoDeFestaResponseDTO> salaoDeFestaResponseDTO = new ArrayList<>();

		for(SalaoDeFesta a :salaoDeFesta ){
			salaoDeFestaResponseDTO.add(mapper.mapSalaoDeFestaToSalaoDeFestaResponseDTO(a));
		}


		return ResponseEntity.ok().body(salaoDeFestaResponseDTO);
	}

	@GetMapping("datas-bloqueadas/{id}")
	public ResponseEntity<Set<DataBloqueada>> getDatasBloqueadas(@PathVariable Integer id) throws URISyntaxException {
		try {
			SalaoDeFesta salao = salaoDeFestaService.findById(id);
			Set<DataBloqueada> dataBloqueada = salao.getDatasBloqueadas();
			return ResponseEntity.ok().body(dataBloqueada);
		} catch (NullPointerException e) {
			throw new URISyntaxException("Salão de Festa não cadastrado!", "Erro para encontrar Salão de Festa: ");
		}
	}

	@GetMapping("datas-restringidas/{id}")
	public ResponseEntity<Set<DataBloqueada>> getDatasRestringidas(@PathVariable Integer id) throws URISyntaxException {
		 try {
        SalaoDeFesta salao = salaoDeFestaService.findById(id);
        Set<DataBloqueada> datasBloqueadas = salao.getDatasBloqueadas();
        Set<LocalDate> datasReservadas = new HashSet<>();

        for (Reserva reserva : salao.getReserva()) {
            datasReservadas.add(reserva.getDataEvento());
        }

        datasBloqueadas.removeIf(dataBloqueada -> datasReservadas.contains(dataBloqueada.getData()));

        return ResponseEntity.ok().body(datasBloqueadas);
    } catch (NullPointerException e) {
        throw new URISyntaxException("Salão de Festa não cadastrado!", "Erro para encontrar Salão de Festa: ");
    }
	}


	@PostMapping("datas-bloqueadas/{id}")
	public ResponseEntity<String> setDataBloqueada(@PathVariable Integer id, @RequestBody DataDTO dto)
			throws URISyntaxException {
		try {
			SalaoDeFesta salao = salaoDeFestaService.findById(id);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataFormatada = LocalDate.parse(dto.getData(), formatter);

			DataBloqueada dataBloqueada = new DataBloqueada(dataFormatada, salao);
			if (salao.existeReservaNesteDia(dataFormatada)) {
				return ResponseEntity.ok()
						.body("Data " + dataFormatada + " não pode ser bloqueada: Existe uma reserva ativa!");
			}

			if (salao.registraDataBloqueada(dataBloqueada)) {
				dataBloqueadaService.saveDataBloqueada(dataBloqueada);
				return ResponseEntity.ok().body("Data " + dataFormatada + " bloqueada com sucesso!");
			} else {
				return ResponseEntity.ok().body("Data " + dataFormatada + " já estava bloqueada!");
			}

		} catch (NullPointerException e) {
			throw new URISyntaxException("Salão de Festa não encontrado!", "Erro na edição do Salão de Festa");
		} catch (DateTimeParseException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro de formatação:  O padrão de formatação é inválido.");
		}
	}

	@PostMapping("desbloquearData/{id}")
	public ResponseEntity<String> desbloquearDataRestringida(@PathVariable Integer id, @RequestBody DataDTO dto) throws URISyntaxException{
		try{
			SalaoDeFesta salao = salaoDeFestaService.findById(id);
			DataBloqueada dataParaDesbloquear = new DataBloqueada();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataFormatada = LocalDate.parse(dto.getData(), formatter);


			List<DataBloqueada> dataBloqueada = dataBloqueadaService.getDataBloqueadaByidSalaoDeFesta(id);

			for(DataBloqueada dat: dataBloqueada){
				if(dat.getData().equals(dataFormatada)){
					dataParaDesbloquear = dat;
					break;
				}
			}
			if(salao.existeReservaNesteDia(dataFormatada)){
				return ResponseEntity.ok().body("Existem Reservas neste dia!");
			}

			dataBloqueadaService.liberarData(dataParaDesbloquear);
			salao.removerDataBloqueada(dataParaDesbloquear);

			return ResponseEntity.ok().body("Data desbloqueada com sucesso!");
			

		}catch (NullPointerException e) {
			throw new URISyntaxException("Salão de Festa não encontrado!", "Erro na edição do Salão de Festa");
		} catch (DateTimeParseException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro de formatação:  O padrão de formatação é inválido.");
		}
	}

	@PostMapping("/filtrar-saloes")
	public List<SalaoDeFestaResponseDTO> filtrarSaloes(
			@RequestParam(required = false) String nomeSalao,

			@RequestParam(required = false) String localizacao,
			@RequestParam(required = false) Integer capacidadeMinima,
			@RequestParam(required = false) Integer capacidadeMaxima,
			@RequestParam(required = false) Double precoBaseMinimo,
			@RequestParam(required = false) Double precoBaseMaximo) {

		List<SalaoDeFesta> listaDeSalaoDeFesta = salaoDeFestaService.getSalaoDeFesta();
		
		List<SalaoDeFestaResponseDTO> salaoDeFestaResponseDTO = new ArrayList<>();

		for(SalaoDeFesta a :listaDeSalaoDeFesta){
			salaoDeFestaResponseDTO.add(mapper.mapSalaoDeFestaToSalaoDeFestaResponseDTO(a));
		}

		return salaoDeFestaResponseDTO.stream()
				.filter(salao -> nomeSalao == null || nomeSalao.isBlank()
						|| salao.getNome().toLowerCase().contains(nomeSalao.toLowerCase()))
				.filter(salao -> localizacao == null || localizacao.isBlank()
						|| salao.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase()))
				.filter(salao -> capacidadeMinima == null || salao.getCapacidade() >= capacidadeMinima)
				.filter(salao -> capacidadeMaxima == null || salao.getCapacidade() <= capacidadeMaxima)
				.filter(salao -> precoBaseMinimo == null || salao.getPrecoBase() >= precoBaseMinimo)
				.filter(salao -> precoBaseMaximo == null || salao.getPrecoBase() <= precoBaseMaximo)
				.collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<SalaoDeFesta> saveSalaoDeFesta(@Valid @RequestBody SalaoDeFestaDTO dto)
			throws URISyntaxException {
		try {
			SalaoDeFesta novoSalaoDeFesta = salaoDeFestaService.save(mapper.mapSalaoDeFestaDtoToSalaoDeFesta(dto));

			return ResponseEntity.created(new URI("/salao-de-festa/salva" + novoSalaoDeFesta.getId()))
					.body(novoSalaoDeFesta);
		} catch (DataIntegrityViolationException e) {
			throw new ObjectNotFoundException(e, "Usuario");
		}

	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<SalaoDeFesta> updateSalaoDeFesta(@Valid @RequestBody SalaoDeFestaDTO dto,
			@PathVariable Integer id) throws URISyntaxException {

		SalaoDeFesta novoSalaoDeFesta = mapper.mapSalaoDeFestaDtoToSalaoDeFesta(dto);
		novoSalaoDeFesta.setId(id);
		try {
			salaoDeFestaService.update(novoSalaoDeFesta);
		} catch (NullPointerException e) {
			throw new URISyntaxException("Salão de Festa não encontrado!", "Erro na edição do Salão de Festa");
		}
		return ResponseEntity.created(new URI("/salao-de-festa/editar" + novoSalaoDeFesta.getId()))
				.body(novoSalaoDeFesta);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> deleteSalaoDeFesta(@PathVariable Integer id) {

		boolean exists = salaoDeFestaService.existsById(id);
		if (!exists) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salão de Festa não encontrado com o ID: " + id);
		}

		salaoDeFestaService.deleteById(id);

		String message = "Salão de Festa com ID: " + id + " foi excluído com sucesso!";
		return ResponseEntity.ok(message);
	}

}
