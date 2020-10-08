package br.com.ivanilsonjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
	List<Anuncio> findAllByStatus(EstadoAnuncio estadoAnuncio);
	List<Anuncio> findAllByStatusAndAnunciante(EstadoAnuncio estadoAnuncio,Usuario Anunciante);
}
