package br.com.ivanilsonjr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Usuario;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
	Page<Anuncio> findAllByStatus(EstadoAnuncio estadoAnuncio, Pageable paginacao);
	List<Anuncio> findAllByStatusAndAnunciante(EstadoAnuncio estadoAnuncio,Usuario Anunciante);
}
