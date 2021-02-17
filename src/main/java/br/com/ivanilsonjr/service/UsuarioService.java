package br.com.ivanilsonjr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


	public void cadastrarUsuario(Usuario usuario) {
		if(usuarioRepository.findByLogin(usuario.getLogin()) != null) {
			throw new BadRequestException("Ja existe um usuario com esse login cadastrado!");
		}
		
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		
		usuarioRepository.save(usuario);
	}
}
