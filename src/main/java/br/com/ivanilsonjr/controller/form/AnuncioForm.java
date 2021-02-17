package br.com.ivanilsonjr.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.repository.ClienteRepository;
import br.com.ivanilsonjr.repository.ProdutoRepository;

public class AnuncioForm {
	@Size(min = 7, message="Titulo do anuncio invalido")
	private String titulo;
	@Size(min = 20, message="Descrição do anuncio invalida")
	private String descricao;
	@NotNull
	private Long codigoProduto;
	@NotNull
	private Double preco;

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Anuncio converter(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
		return new Anuncio(titulo, descricao, produtoRepository.find(codigoProduto, Long.parseLong("1")), preco, 
				clienteRepository.findById(Long.parseLong("1")).get());
	}
	
}
