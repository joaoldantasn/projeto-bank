package com.br.accenture.eBank.ebank.ddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.dtos.agencia.AgenciaDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;

class AgenciaDTOTest {

	@Test
    public void whenDefaultConstructorIsUsed_thenFieldsShouldBeInitializedWithDefaults() {
        AgenciaDTO dto = new AgenciaDTO();

        assertNull(dto.getIdAgencia(), "O ID da agência deve ser nulo.");
        assertEquals(0, dto.getCodAgencia(), "O código da agência deve ser 0.");
        assertNull(dto.getEndereco(), "O endereço deve ser nulo.");
        assertNull(dto.getTelefone(), "O telefone deve ser nulo.");
    }
	
	@Test
    public void whenAllArgumentsConstructorIsUsed_thenDTOIsInitializedCorrectly() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("123");

        AgenciaDTO dto = new AgenciaDTO(1L, 101, endereco, "123456789");

        assertEquals(1L, dto.getIdAgencia(), "O ID da agência deve ser 1L.");
        assertEquals(101, dto.getCodAgencia(), "O código da agência deve ser 101.");
        assertNotNull(dto.getEndereco(), "O endereço não deve ser nulo.");
        assertEquals("12345-678", dto.getEndereco().getCep(), "O CEP do endereço deve ser 12345-678.");
        assertEquals("Rua Teste", dto.getEndereco().getLogradouro(), "O logradouro do endereço deve ser Rua Teste.");
        assertEquals("Cidade Teste", dto.getEndereco().getCidade(), "A cidade do endereço deve ser Cidade Teste.");
        assertEquals("Bairro Teste", dto.getEndereco().getBairro(), "O bairro do endereço deve ser Bairro Teste.");
        assertEquals("123", dto.getEndereco().getNumero(), "O número do endereço deve ser 123.");
        assertEquals("123456789", dto.getTelefone(), "O telefone deve ser 123456789.");
    }
	
	 @Test
	    public void whenEntityConstructorIsUsed_thenDTOIsInitializedFromEntity() {
	        Endereco endereco = new Endereco();
	        endereco.setCep("12345-678");
	        endereco.setLogradouro("Rua Teste");
	        endereco.setCidade("Cidade Teste");
	        endereco.setBairro("Bairro Teste");
	        endereco.setNumero("123");

	        Agencia agencia = new Agencia();
	        agencia.setIdAgencia(1L);
	        agencia.setCodAgencia(101);
	        agencia.setEndereco(endereco);
	        agencia.setTelefone("123456789");

	        AgenciaDTO dto = new AgenciaDTO(agencia);

	        assertEquals(1L, dto.getIdAgencia(), "O ID da agência deve ser 1L.");
	        assertEquals(101, dto.getCodAgencia(), "O código da agência deve ser 101.");
	        assertNotNull(dto.getEndereco(), "O endereço não deve ser nulo.");
	        assertEquals("12345-678", dto.getEndereco().getCep(), "O CEP do endereço deve ser 12345-678.");
	        assertEquals("Rua Teste", dto.getEndereco().getLogradouro(), "O logradouro do endereço deve ser Rua Teste.");
	        assertEquals("Cidade Teste", dto.getEndereco().getCidade(), "A cidade do endereço deve ser Cidade Teste.");
	        assertEquals("Bairro Teste", dto.getEndereco().getBairro(), "O bairro do endereço deve ser Bairro Teste.");
	        assertEquals("123", dto.getEndereco().getNumero(), "O número do endereço deve ser 123.");
	        assertEquals("123456789", dto.getTelefone(), "O telefone deve ser 123456789.");
	    }
	 
	 @Test
	    public void whenSettersAreUsed_thenGettersReturnCorrectValues() {
	        Endereco endereco = new Endereco();
	        endereco.setCep("12345-678");
	        endereco.setLogradouro("Rua Teste");
	        endereco.setCidade("Cidade Teste");
	        endereco.setBairro("Bairro Teste");
	        endereco.setNumero("123");

	        AgenciaDTO dto = new AgenciaDTO();
	        dto.setIdAgencia(1L);
	        dto.setCodAgencia(101);
	        dto.setEndereco(endereco);
	        dto.setTelefone("123456789");

	        assertEquals(1L, dto.getIdAgencia(), "O ID da agência deve ser 1L.");
	        assertEquals(101, dto.getCodAgencia(), "O código da agência deve ser 101.");
	        assertNotNull(dto.getEndereco(), "O endereço não deve ser nulo.");
	        assertEquals("12345-678", dto.getEndereco().getCep(), "O CEP do endereço deve ser 12345-678.");
	        assertEquals("Rua Teste", dto.getEndereco().getLogradouro(), "O logradouro do endereço deve ser Rua Teste.");
	        assertEquals("Cidade Teste", dto.getEndereco().getCidade(), "A cidade do endereço deve ser Cidade Teste.");
	        assertEquals("Bairro Teste", dto.getEndereco().getBairro(), "O bairro do endereço deve ser Bairro Teste.");
	        assertEquals("123", dto.getEndereco().getNumero(), "O número do endereço deve ser 123.");
	        assertEquals("123456789", dto.getTelefone(), "O telefone deve ser 123456789.");
	    }

}
