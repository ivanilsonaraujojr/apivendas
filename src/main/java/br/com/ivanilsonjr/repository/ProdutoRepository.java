package br.com.ivanilsonjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ivanilsonjr.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query(value="SELECT * FROM public.produtos WHERE codigo = :codigo AND dono_produto_id = :donoProdutoId", nativeQuery = true)
	Produto find(@Param("codigo") Long codigo,@Param("donoProdutoId") Long donoProdutoId);
}
