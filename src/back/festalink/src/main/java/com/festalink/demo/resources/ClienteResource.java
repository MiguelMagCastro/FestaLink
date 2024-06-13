package com.festalink.demo.resources;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.festalink.demo.entities.Cliente;
import com.festalink.demo.entities.Reserva;
import com.festalink.demo.entities.Usuario;
import com.festalink.demo.entities.dto.ClienteDTO;
import com.festalink.demo.entities.dto.LoginDTO;
import com.festalink.demo.entities.mapper.ClienteMapper;
import com.festalink.demo.services.impl.ClienteServiceImpl;
import com.festalink.demo.utils.EncryptionUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<Reserva>> getReservasByClienteId(@PathVariable int id) {
        List<Reserva> reservas = clienteService.findReservasByClienteId(id);
        return ResponseEntity.ok().body(reservas);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO dto) throws URISyntaxException {
        try {
            Cliente novoCliente = mapper.mapClienteDtoToCliente(dto);

            novoCliente = clienteService.saveWithEncryptedPassword(novoCliente);

            return ResponseEntity.created(new URI("/clientes/salva/" + novoCliente.getId())).body(novoCliente);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException(e, "Usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {
        Usuario usuario = clienteService.findByEmail(dto.getEmail());
        dto.setTipoUsuario("Cliente");
        if (usuario != null && EncryptionUtils.matchPassword(dto.getSenha(), usuario.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido como " + dto.getTipoUsuario() + "!" + " id= " + usuario.getId()
                    + " nome= " + usuario.getNome() + " email= " + usuario.getEmail() + " senha= " + usuario.getSenha()
                    + " telefone= " + usuario.getTelefone());
        } else {
            return ResponseEntity.badRequest().body("Usuário ou senha informados são inválidos");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        clienteService.deleteById(id);
    }

}
