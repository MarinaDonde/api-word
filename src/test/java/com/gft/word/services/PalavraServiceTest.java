package com.gft.word.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gft.word.entities.Palavra;
import com.gft.word.repositories.PalavraRepository;

@ExtendWith(MockitoExtension.class)
public class PalavraServiceTest {
	
	private static final Long ID = 1L;
	private static final String PALAVRA = "teste";
	
	@Mock
	private PalavraRepository palavraRepository;
	
	@InjectMocks
	private PalavraService palavraService;
	
	private Palavra palavra;
	private Optional<Palavra> optionalPalavra;
	
	@BeforeEach
	private void setUp() {
		palavra = new Palavra(ID, PALAVRA);
		optionalPalavra = Optional.of(new Palavra(ID, PALAVRA));
	}

	
	@Test
	void deveSalvarUmaNovaPalavra() throws Exception{
		when(palavraRepository.save(any())).thenReturn(palavra);
		
		Palavra retorna = palavraService.salvarPalavra(palavra);
		
		assertAll(() -> assertNotNull(retorna)
				, () -> assertEquals(Palavra.class, retorna.getClass())
				, () -> assertEquals(ID, retorna.getId())
				, () -> assertEquals(PALAVRA, retorna.getPalavra()));
	}
	
	@Test
	void deveBuscarUmaPalavraPeloId() throws Exception{
		when(palavraRepository.findById(anyLong())).thenReturn(optionalPalavra);
		
		Palavra retorna = palavraService.buscarPalavra(ID);
		
		assertAll(() -> assertNotNull(retorna)
				, () -> assertEquals(Palavra.class, retorna.getClass())
				, () -> assertEquals(ID, retorna.getId())
				, () -> assertEquals(PALAVRA, retorna.getPalavra()));
	}
	
	@Test
	void deveAtualizarUmaPalavraExistente() throws Exception{
		when(palavraRepository.save(any())).thenReturn(palavra);
		when(palavraRepository.findById(anyLong())).thenReturn(optionalPalavra);
		
		Palavra retorna = palavraService.atualizarPalavra(palavra, ID);
		
		assertAll(() -> assertNotNull(retorna)
				, () -> assertEquals(Palavra.class, retorna.getClass())
				, () -> assertEquals(ID, retorna.getId())
				, () -> assertEquals(PALAVRA, retorna.getPalavra()));	
	}

}
