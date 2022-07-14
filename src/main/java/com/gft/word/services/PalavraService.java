package com.gft.word.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.word.entities.Palavra;
import com.gft.word.repositories.PalavraRepository;

@Service
public class PalavraService {
	
	private final PalavraRepository palavraRepository;
	
	public PalavraService(PalavraRepository palavraRepository) {
		this.palavraRepository = palavraRepository;
	}
	
	public Palavra salvarPalavra(Palavra palavra) {
		
		return palavraRepository.save(palavra);
	}
	
	public Page<Palavra> listarTodosAsPalavras(Pageable pageable) {
		
		return palavraRepository.findAll(pageable);
	}
	
	public Palavra buscarPalavra(Long id) {
		
		Optional<Palavra> optional = palavraRepository.findById(id);
		return optional.orElseThrow(() -> new EntityNotFoundException("Palavra não encontrada"));
	}
	
	public Palavra atualizarPalavra(Palavra palavra, Long id) {
		
		Palavra palavraOriginal = this.buscarPalavra(id);
		
		palavra.setId(palavraOriginal.getId());
		
		return palavraRepository.save(palavra);
	}
	
	public void excluirPalavra(Long id) {
		
		Palavra palavraOriginal = this.buscarPalavra(id);
		
		palavraRepository.delete(palavraOriginal);
	}

}
