package br.com.ivanilsonjr.repository;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ivanilsonjr.model.Cliente;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
	    if (setUpIsDone) {
	        return;
	    }
		clienteRepository.save(new Cliente("Teste", "79109877061"));
	    setUpIsDone = true;
	}
	
	@Test
	@DisplayName("Buscar cliente pelo cpf")
	public void findByCpf() {
		Optional<Cliente> optional = clienteRepository.findByCpf("79109877061");
		Assertions.assertTrue(optional.isPresent());
	}
	
	@Test
	@DisplayName("Exclui um cliente")
	public void deleteCliente() {
		Optional<Cliente> optional = clienteRepository.findByCpf("79109877061");
		clienteRepository.delete(optional.get());
		Assertions.assertTrue(clienteRepository.findByCpf("79109877061").isEmpty());
	}
}
