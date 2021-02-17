package br.com.ivanilsonjr.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.DetalhesDaVendaDto;
import br.com.ivanilsonjr.controller.dto.VendaDto;
import br.com.ivanilsonjr.controller.form.VendaForm;
import br.com.ivanilsonjr.model.Anuncio;
import br.com.ivanilsonjr.model.Venda;
import br.com.ivanilsonjr.model.enums.EstadoAnuncio;
import br.com.ivanilsonjr.repository.AnuncioRepository;
import br.com.ivanilsonjr.repository.ClienteRepository;
import br.com.ivanilsonjr.repository.VendaRepository;
import br.com.ivanilsonjr.util.ApiVendasUtil;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vr;
	@Autowired
	private AnuncioRepository ar;
	@Autowired
	private ClienteRepository cr;
	
	public Page<VendaDto> listarVendasTodas(Pageable paginacao) {
		Page<Venda> listaVendas = vr.findAll(paginacao);
		return VendaDto.converter(listaVendas);
	}

	public DetalhesDaVendaDto mostrarVendaCodigo(Long codigo) {
		Optional<Venda> optional = vr.findById(codigo);

		ApiVendasUtil.verificarObjetoEhExistente(optional,"Venda");

		return new DetalhesDaVendaDto(optional.get());
	}
	
	@Transactional
	public VendaDto cadastrarVenda(VendaForm vendaForm) {
		//Verifica existência do anuncio
		Optional<Anuncio> anuncioVendido = ar.findById(vendaForm.getCodigoAnuncio());
		
		ApiVendasUtil.verificarObjetoEhExistente(anuncioVendido, "Produto");
		
		if(anuncioVendido.get().getStatus().equals(EstadoAnuncio.VENDIDO)) {
			throw new BadRequestException("Esse produto ja foi vendido, verifique o código digitado!");
		}
		
		//Se existir, registrar a venda muda o status do anuncio para vendido e atribui data da venda
		Venda venda = vendaForm.conveter(ar, cr);
		
		//Persiste a venda no banco de dados
		vr.save(venda);
		
		anuncioVendido.get().setStatus(EstadoAnuncio.VENDIDO);
		anuncioVendido.get().setDataVenda(venda.getDataCompra());
		
		return new VendaDto(venda);
	}

	@Transactional
	public void deletarVenda(Long codigo) {
		Optional<Venda> optional = vr.findById(codigo);

		ApiVendasUtil.verificarObjetoEhExistente(optional, "Venda");

		vr.delete(optional.get());

		Anuncio anuncioVenda = optional.get().getAnuncioVendido();
		anuncioVenda.setDataVenda(null);
		anuncioVenda.setStatus(EstadoAnuncio.ABERTO);
	}
}
