package br.com.ivanilsonjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

}
