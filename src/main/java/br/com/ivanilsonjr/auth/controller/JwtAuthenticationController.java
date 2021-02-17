package br.com.ivanilsonjr.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanilsonjr.auth.config.JwtTokenUtil;
import br.com.ivanilsonjr.auth.model.JwtResponse;
import br.com.ivanilsonjr.auth.service.JwtUserDetailsService;
import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.model.Usuario;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario usuario) throws Exception {
		authenticate(usuario.getLogin(), usuario.getSenha());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getLogin());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse("Bearer " + token));
	}

	private void authenticate(String username, String password){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException e) {
			throw new BadRequestException("Erro na autenticação, verifique o login e a senha!");
		}
	}
}