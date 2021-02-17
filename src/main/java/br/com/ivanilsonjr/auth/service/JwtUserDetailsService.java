package br.com.ivanilsonjr.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(login);

		if (usuario.getLogin().equals(login)) {
			return new User(login, usuario.getSenha(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuario não encontrado com o login: " + login);
		}
	}
}