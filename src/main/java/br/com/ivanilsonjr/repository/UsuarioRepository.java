package br.com.ivanilsonjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
