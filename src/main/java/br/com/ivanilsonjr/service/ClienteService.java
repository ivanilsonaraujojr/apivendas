package br.com.ivanilsonjr.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ivanilsonjr.config.exceptions.BadRequestException;
import br.com.ivanilsonjr.controller.dto.ClienteDto;
import br.com.ivanilsonjr.controller.form.AtualizacaoClienteForm;
import br.com.ivanilsonjr.controller.form.ClienteForm;
import br.com.ivanilsonjr.model.Cliente;
import br.com.ivanilsonjr.repository.ClienteRepository;
import br.com.ivanilsonjr.util.ApiVendasUtil;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;

	public Page<ClienteDto> listarTodos(Pageable paginacao) {
		Page<Cliente> listaProdutos = cr.findAll(paginacao);
		return ClienteDto.converter(listaProdutos);
	}
	
	public ClienteDto mostrarClienteCodigo(Long codigo) {
		Optional<Cliente> cliente = cr.findById(codigo);
		
		ApiVendasUtil.verificarObjetoEhExistente(cliente, "Cliente");
		
		return new ClienteDto(cliente.get());
	}

	@Transactional
	public ClienteDto atualizarCliente(Long id, AtualizacaoClienteForm atualizacaoClienteForm) {
		Optional<Cliente> cliente = cr.findById(id);
		ApiVendasUtil.verificarObjetoEhExistente(cliente, "Cliente");
		Cliente clienteAtualizado = cliente.get();
		clienteAtualizado.setNome(atualizacaoClienteForm.getNome());
		clienteAtualizado.setCpf(atualizacaoClienteForm.getCpf());
		return new ClienteDto(clienteAtualizado);
	}

	public ClienteDto cadastrarCliente(ClienteForm clienteForm) {
	Cliente cliente = clienteForm.converter();
	if(cliente.getCpf().contains(".") || cliente.getCpf().contains("-")) {
		cliente.setCpf(cliente.getCpf().replaceAll("[./-]", ""));
	}
	
	if(cr.findByCpf(cliente.getCpf()).isPresent()) {
		throw new BadRequestException("Ja existe um cadastro para esse CPF!");
	}
	
	cr.save(cliente);
	return new ClienteDto(cliente);
	}

	public void deletarCliente(Long id) {
		Optional<Cliente> cliente = cr.findById(id);
		ApiVendasUtil.verificarObjetoEhExistente(cliente, "Cliente");
		
		cr.delete(cliente.get());
	}
}
