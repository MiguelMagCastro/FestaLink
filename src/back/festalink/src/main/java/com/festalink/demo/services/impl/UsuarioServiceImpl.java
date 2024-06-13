// package com.festalink.demo.services.impl;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.festalink.demo.entities.Usuario;
// import com.festalink.demo.repositories.UsuarioRepository;
// import com.festalink.demo.services.UsuarioService;

// @Service
// public class UsuarioServiceImpl implements UsuarioService {
// 	@Autowired
// 	private UsuarioRepository usuarioRepository;

// 	@Override
// 	public List<Usuario> getUsuario() {
// 		return usuarioRepository.findAll();
// 	}

// 	@Override
// 	public Usuario save(Usuario usuario) {
// 		return usuarioRepository.save(usuario);
// 	}

// 	@Override
// 	public Usuario findById(Integer id) {
// 		Optional<Usuario> usuario = usuarioRepository.findById(id);

// 		return usuario.orElse(null);
// 	}

 	// @Override
// public void update(Usuario usuario) {
 	// 	try {
 	// 		Usuario atual = this.findById(usuario.getId());

		
 	// 	atual.setNome(usuario.getNome());
 	// 	atual.setEmail(usuario.getEmail());
 	// 	atual.seTipoUsuario(usuario.getTipoUsuario());
 	// 	atual.setTelefone(usuario.getTelefone());

 	// 	usuarioRepository.save(atual);
 	// 	} catch (NullPointerException e) {
 	// 		throw new NullPointerException();
 	// 	}
	
 	// }

// 	@Override
// 	public void deleteById(Integer id) {
// 		usuarioRepository.deleteById(id);
// 	}

// 	@Override
// 	public boolean existsById(Integer id) {
//     return usuarioRepository.existsById(id);
// 	}

// 	@Override
// 	public Usuario findByEmail(String email) {
// 		return usuarioRepository.findByEmail(email);
// 	}


	

// }
